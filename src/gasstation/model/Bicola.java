/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model;

/**
 *
 * @author Rodrigo
 * @param <E>
 */
public class Bicola<E> {

    ListaSE<E> cola;

    public Bicola() {
        cola = new ListaSE<>();
    }

    public boolean vacia() {
        return cola.vacia();
    }

    public void encolar(E dato) {
        cola.insertar(dato);
    }

    public synchronized E decolarInicio() throws InterruptedException {
        if (size() == 0) {
            wait();
        }
        return cola.eliminar(0);
    }

    public E decolarFin() {
        return cola.eliminar(cola.size() - 1);
    }

    public E verInicio() {
        return cola.get(0);
    }

    public E verFin() {
        return cola.get(cola.size() - 1);
    }

    public int size() {
        return cola.size();
    }
}
