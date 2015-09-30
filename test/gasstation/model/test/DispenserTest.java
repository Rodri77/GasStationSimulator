/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model.test;

import gasstation.model.Car;
import gasstation.model.InvalidCombustibleTypeException;
import gasstation.model.Dispenser;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class DispenserTest {

    @Test
    public void testCargarGas() throws InvalidCombustibleTypeException {
        Dispenser dispenser = new Dispenser(1000, 1, "gas");
        dispenser.chargeCombustibleWithCashAmount(20, "gas");
        double expected = 987.95;
        assertEquals(expected, dispenser.getCombustibleQuantity(), 0.01);
    }
    @Test
    public void testCargarGasolina() throws InvalidCombustibleTypeException {
        Dispenser dispenser = new Dispenser(1000, 1, "gasolina");
        dispenser.chargeCombustibleWithCashAmount(20, "gasolina");
        double expected = 994.65;
        System.out.println(expected);
        assertEquals(expected, dispenser.getCombustibleQuantity(), 0.01);
    }
    @Test
    public void testCargarDiesel() throws InvalidCombustibleTypeException {
        Dispenser dispenser = new Dispenser(1000, 1, "diesel");
        dispenser.chargeCombustibleWithCashAmount(20, "diesel");
        double expected = 994.62;
        System.out.println(expected);
        assertEquals(expected, dispenser.getCombustibleQuantity(), 0.01);
    }

    @Test(expected = InvalidCombustibleTypeException.class)
    public void testTipoDeCombustibleInvalido() throws InvalidCombustibleTypeException, InterruptedException {
        Dispenser dispenser = new Dispenser(1000, 1, "gas");
        Car automovil = new Car(20, "gasolina");
        dispenser.cars.encolar(automovil);
        dispenser.chargeCombustibleWithCashAmount(50, "gaso");
        fail();
    }
}
