package gasstation.model.test;

import gasstation.model.Bicola;
import static org.junit.Assert.*;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rodrigo
 * @param <E>
 */
public class BicolaTest<E> {

    @Test
    public void testVacia() {
        
        Bicola<Integer> cola = new Bicola<>();
        assertTrue(cola.vacia());
    }
    
    @Test
    public void testEncolar() {
        
        Bicola<Integer> cola = new Bicola<>();
        Integer dato = 5;
        Integer dato1 = 3;
        cola.encolar(dato);
        assertFalse(cola.vacia());
        cola.encolar(dato);
    }
}
