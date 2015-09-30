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
import java.util.Random;

/**
 *
 * @author Rodrigo Arze
 */
public class CarGenerator {

    private static final int MIN_CASH_FOR_GAS = 10;
    private static final int MIN_CASH_FOR_GASOLINE = 20;
    private static final int MIN_CASH_FOR_DIESEL = 30;
    private static final int MAX_CASH_FOR_GASOLINE = 150;
    private static final int MAX_CASH_FOR_GAS = 20;
    private static final int MAX_CASH_FOR_DIESEL = 150;
    private static final int MIN_NUMBER_OF_CARS = 5;
    private static final int MAX_NUMBER_OF_CARS = 10;

    private final List<CarGeneratorListener> listeners;
    private final int hoursToSimulate;

    public CarGenerator(int hoursToSimulate) {
        this.hoursToSimulate = hoursToSimulate;
        listeners = new ArrayList<>();
    }

    private int calculateCashByCombustibleType(String type) throws InvalidCombustibleTypeException {
        Random combustibleWantedInCash = new Random();
        switch (type) {
            case "gasolina": {
                return MIN_CASH_FOR_GASOLINE + combustibleWantedInCash.nextInt(MAX_CASH_FOR_GASOLINE);
            }
            case "gas": {
                return MIN_CASH_FOR_GAS + combustibleWantedInCash.nextInt(MAX_CASH_FOR_GAS);
            }
            case "diesel": {
                return MIN_CASH_FOR_DIESEL + combustibleWantedInCash.nextInt(MAX_CASH_FOR_DIESEL);
            }
            default: {
                throw new InvalidCombustibleTypeException();
            }
        }
    }

    public void generateCars(String type) throws InvalidCombustibleTypeException {
        int minimunCash = calculateCashByCombustibleType(type);
        Random numberOfCars = new Random();
        int carsToCreate = (numberOfCars.nextInt(MIN_NUMBER_OF_CARS) + numberOfCars.nextInt(MAX_NUMBER_OF_CARS)) * hoursToSimulate;
        for (int iterator = 0; iterator < carsToCreate; iterator++) {
            Car actual = new Car(minimunCash, type);
            dispatchEvent(actual);
        }
    }

    public void addCarGeneratorListener(CarGeneratorListener listener) {
        listeners.add(listener);
    }

    private void dispatchEvent(Car car) {
        CarGeneratorEvent event = new CarGeneratorEvent(this, car);
        listeners.stream().forEach((CarGeneratorListener current) -> {
            current.onCarCreated(event);
        });
    }
}
