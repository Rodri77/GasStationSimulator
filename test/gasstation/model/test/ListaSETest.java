package gasstation.model.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gasstation.model.ListaSE;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rodrigo
 */
public class ListaSETest {
    
    @Test
    public void testInsertar() {
        Integer dato, dato1;
        dato = 5;
        dato1 = 3;
        ListaSE<Integer> lista;
        lista = new ListaSE<>();
        assertTrue(lista.vacia());
        lista.insertar(dato);
        assertEquals(dato, lista.get(dato));
        lista.insertar(dato1);
        assertEquals(dato1, lista.get(dato1));
        assertEquals(2, lista.size());
    }
    
    @Test
    public void testSize() {
        
        ListaSE<Integer> lista = new ListaSE<>();
        assertEquals(0, lista.size());
        lista.insertar(1);
        lista.insertar(2);
        lista.insertar(3);
        assertEquals(3, lista.size());
    }
    
    @Test
    public void testEliminar() {
        
        ListaSE<Integer> lista = new ListaSE<>();
        Integer dato = 1;
        Integer dato1 = 2;
        assertTrue(lista.vacia());
        lista.insertar(dato);
        assertEquals(1, lista.size());
        assertEquals(dato, lista.get(0));
        lista.eliminar(0);
        assertTrue(lista.vacia());
        lista.insertar(dato);
        lista.insertar(dato1);
        assertEquals(dato1, lista.eliminar(1));
    }
}
