/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model;

import gasstation.listeners.CarGeneratorListener;
import gasstation.events.CarGeneratorEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo Arze
 */
public final class Surtidor implements CarGeneratorListener {

    private static final double CANTIDAD_DE_COMBUSTIBLE = 1000;

    private final List<Dispenser> dispensersGas;
    private final List<Dispenser> dispensersGasolina;
    private final List<Dispenser> dispensersDiesel;
    private static int dispenserCode = 1;
    private int hoursToSimulate;
    private CarGenerator generator;

    public Surtidor(int numberOfGasDispensers, int numberOfGasolineDispensers, int numberOfDieselDispensers) throws InvalidCombustibleTypeException {
        dispensersGas = new ArrayList<>();
        dispensersGasolina = new ArrayList<>();
        dispensersDiesel = new ArrayList<>();
        createGasDispensers(numberOfGasDispensers);
        createGasolineDispensers(numberOfGasolineDispensers);
        createDieselDispensers(numberOfDieselDispensers);
    }

    public CarGenerator getGenerator() {
        return generator;
    }

    public void initiateGenerator() {
        generator = new CarGenerator(hoursToSimulate);
        generator.addCarGeneratorListener(this);

    }

    public void setHoursToSimulate(int hours) {
        hoursToSimulate = hours;
    }

    private void createGasDispensers(int number) {
        for (int i = 0; i < number; i++) {
            dispensersGas.add(new Dispenser(CANTIDAD_DE_COMBUSTIBLE, dispenserCode++, "gas"));
        }
    }

    private void createGasolineDispensers(int number) {
        for (int i = 0; i < number; i++) {
            dispensersGasolina.add(new Dispenser(CANTIDAD_DE_COMBUSTIBLE, dispenserCode++, "gasolina"));
        }
    }

    private void createDieselDispensers(int number) {
        for (int i = 0; i < number; i++) {
            dispensersDiesel.add(new Dispenser(CANTIDAD_DE_COMBUSTIBLE, dispenserCode++, "diesel"));
        }
    }

    public void distribuirAuto(Car auto) {
        Dispenser emptierDispenser = getEmptierDispenser(auto.getCombustibleType());
        emptierDispenser.queueCar(auto);
    }

    private Dispenser getEmptierDispenser(String tipoDeCombustible) {
        Dispenser masVacio = null;
        if (tipoDeCombustible.equals("gas")) {
            masVacio = getDispenserMasVacio(dispensersGas);
        }
        if (tipoDeCombustible.equals("gasolina")) {
            masVacio = getDispenserMasVacio(dispensersGasolina);
        }
        if (tipoDeCombustible.equals("diesel")) {
            masVacio = getDispenserMasVacio(dispensersDiesel);
        }
        return masVacio;
    }

    private Dispenser getDispenserMasVacio(List<Dispenser> dispensers) {
        Dispenser res;
        res = dispensers.get(0);
        for (Dispenser disp : dispensers) {
            if (disp.cars.size() < res.cars.size()) {
                res = disp;
            }
        }
        return res;
    }

    public void start() {
        for (Dispenser dispenser : dispensersGas) {
            dispenser.start();
        }
        for (Dispenser dispenser : dispensersGasolina) {
            dispenser.start();
        }
        for (Dispenser dispenser : dispensersDiesel) {
            dispenser.start();
        }
    }

    public List<Dispenser> getAllDispensers() {
        List<Dispenser> dispensers = new ArrayList<>();
        for (Dispenser dispenser : dispensersGas) {
            dispensers.add(dispenser);
        }
        for (Dispenser dispenser : dispensersGasolina) {
            dispensers.add(dispenser);
        }
        for (Dispenser dispenser : dispensersDiesel) {
            dispensers.add(dispenser);
        }
        return dispensers;
    }

    @Override
    public void onCarCreated(CarGeneratorEvent event) {
        distribuirAuto(event.getCar());
    }

}
