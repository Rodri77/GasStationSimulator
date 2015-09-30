/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.listeners;

import gasstation.events.CarGeneratorEvent;

/**
 *
 * @author Rodrigo Arze
 */
public interface CarGeneratorListener {
    
    void onCarCreated(CarGeneratorEvent event);
}
