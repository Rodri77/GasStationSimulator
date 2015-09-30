/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model.test;

import gasstation.model.Surtidor;
import gasstation.model.InvalidCombustibleTypeException;
import gasstation.model.Dispenser;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Rodrigo
 */
public class SurtidorTest {

    @Test
    public void testNuevoSurtidor() throws InvalidCombustibleTypeException {
        Surtidor surtidor = new Surtidor(2, 2, 2);
        int expected = 6;
        List<Dispenser> result = surtidor.getAllDispensers();
        assertEquals(expected, result.size());
    }

}
