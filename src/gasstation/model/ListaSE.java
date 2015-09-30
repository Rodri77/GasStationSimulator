/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.model;

/**
 *
 * @author Rodrigo
 */
public class ListaSE<E> {
    private E ini;
    private ListaSE<E> sig;
    
    public ListaSE() {
        
    }
    
    public boolean vacia() {
        return ini == null;
    }
    
    public int size() {
        int res = 0;
        
        if(vacia()) {
            res = 0;
        }
        else{
            res = 1 + sig.size();
        }
        return res;
    }
    
    public boolean insertar(E dato) {
        if (vacia()) {
           ini = dato;
           sig = new ListaSE<E>();
        }
        else {
            sig.insertar(dato);
        }
        return true;
    }
    
    public E get (E d) {
        E dato = d;
        if (vacia()) {
            if (dato.equals(ini)) {
                dato = ini;
            }
            else {
                dato = sig.get(d);
            }
        }
        return dato;
    }
    
    public E get(int pos){
        E dato = null;
        
        if (!vacia()) {
            if (pos == 0) {
                dato = ini;
            }
            else {
                sig.get(pos-1);
            }
        }
        return dato;
    }
    public E eliminar(int pos) {
        E dato = null;
        if(!vacia()) {
            if(pos == 0){
                dato = ini;
                ini = sig.ini;
                sig = sig.sig;
            }
            else {
               dato = sig.eliminar(pos-1);
            }
        }
        return dato;
    }
}
