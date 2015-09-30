/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model;

import gasstation.events.DispenserEvent;
import gasstation.listeners.DispenserListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Arze
 */
public final class Dispenser extends Thread {

    private static final Logger LOG = Logger.getLogger(Dispenser.class.getName());
    private static final double LITRO_DE_GASOLINA_AUTOMOVIL_BOLIVIANO = 3.74;
    private static final double METRO_CUBICO_GAS_AUTOMOVIL_BOLIVIANO = 1.66;
    private static final double LITRO_DE_DIESEL_AUTOMOVIL_BOLIVIANO = 3.72;
    public static final int CANTIDAD_MAXIMA_DE_AUTOS = 5;

    private final Random attentionTime;
    public final Bicola<Car> cars;
    private final StringBuilder name;
    private final String type;
    private final List<DispenserListener> listeners;

    private int carsAttended;
    private int code;
    public int queuedCars;
    private double combustibleQuantity;
    private boolean isRunning = true;
    private Object lock;

    public Dispenser(double combustible, int code, String type) {
        attentionTime = new Random();
        listeners = new ArrayList<>();
        combustibleQuantity = combustible;
        cars = new Bicola<>();
        queuedCars = 0;
        carsAttended = 0;
        this.type = type;
        name = new StringBuilder();
        this.code = code;
        setDispenserName();
        lock = new Object();
    }

    public int getCarsAttended() {
        return carsAttended;
    }

    public String getType() {
        return type;
    }

    public void setDispenserName() {
        name.append("Dispenser");
        name.append(" ");
        name.append(code);
    }

    public String getDispenserName() {
        return name.toString();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public double getCombustibleQuantity() {
        return combustibleQuantity;
    }

    public synchronized void queueCar(Car auto) {
        cars.encolar(auto);
        queuedCars++;
        notify();
    }

    private CombustibleType getCombustibleType(String combustible) throws InvalidCombustibleTypeException {
        CombustibleType result;
        switch (combustible) {
            case "gasolina": {
                result = CombustibleType.GASOLINE;
                return result;
            }
            case "gas": {
                result = CombustibleType.GAS;
                return result;
            }
            case "diesel": {
                result = CombustibleType.DIESEL;
                return result;
            }
            default: {
                throw new InvalidCombustibleTypeException();
            }
        }
    }

    private double getAmountOfCombustibleToChargeByType(CombustibleType type, double cash) throws InvalidCombustibleTypeException {
        double result;
        switch (type) {
            case GASOLINE: {
                result = cash / LITRO_DE_GASOLINA_AUTOMOVIL_BOLIVIANO;
                return result;
            }
            case GAS: {
                result = cash / METRO_CUBICO_GAS_AUTOMOVIL_BOLIVIANO;
                return result;
            }
            case DIESEL: {
                result = cash / LITRO_DE_DIESEL_AUTOMOVIL_BOLIVIANO;
                return result;
            }
            default: {
                throw new InvalidCombustibleTypeException();
            }
        }
    }

    public void chargeCombustible(double gasToCharge) {
        if (combustibleQuantity >= gasToCharge) {
            combustibleQuantity -= gasToCharge;
            carsAttended++;
        }
    }

    public void chargeCombustibleWithCashAmount(double cash, String combustible) throws InvalidCombustibleTypeException {
        double gasToCharge = getAmountOfCombustibleToChargeByType(getCombustibleType(combustible), cash);
        chargeCombustible(gasToCharge);
    }

    public void startAttention() throws InterruptedException, InvalidCombustibleTypeException {
        synchronized (lock) {
            if (cars.size() > 0) {
                Car attended = cars.decolarInicio();
                chargeCombustibleWithCashAmount(attended.getCombustibleWantedInCash(), attended.getCombustibleType());
                int time = 5 + attentionTime.nextInt(5);
                sleep(Time.MINUTE * time);
                dispatchEvent();
            } else {
                finish();
            }
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                startAttention();
            } catch (InterruptedException ex) {
                LOG.info("The attention has been interrupted");
            } catch (InvalidCombustibleTypeException ex) {
                LOG.info(String.format("The combustible type you want is invalid%n"));
            }
        }
    }

    public void finish() {
        isRunning = false;
    }

    public void addDispenserListener(DispenserListener listener) {
        listeners.add(listener);
    }

    private void dispatchEvent() {
        DispenserEvent event = new DispenserEvent(this);
        listeners.stream().forEach((DispenserListener current) -> {
            current.onCarAttended(event);
        });
    }
}
