/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.events;

import gasstation.model.Car;
import java.util.EventObject;

/**
 *
 * @author Rodrigo Arze
 */
public class CarGeneratorEvent extends EventObject {

    private final Car car;

    public CarGeneratorEvent(Object source, Car nextInLine) {
        super(source);
        car = nextInLine;
    }

    public Car getCar() {
        return car;
    }
}
