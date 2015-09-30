/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gasstation.listeners;

import gasstation.events.DispenserEvent;

/**
 *
 * @author Rodrigo
 */
public interface DispenserListener {

    public void onCarAttended(DispenserEvent event);
}
