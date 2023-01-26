/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eightpuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author diego
 */

/*
Function which represent the flip event, which is fired to invert the positon
of the tiles in position firstPos and secondPos
*/
public class FlipEvent {
    
    // list of all the listeners of the event
    private List<FlipListener> listeners;
    
    
    public FlipEvent() {
        listeners = new ArrayList<FlipListener>();
    }
    
    
    // method to register new listeners
    public void addFlipListener(FlipListener l) {
        this.listeners.add(l);
    }
    
    // method to fire the event calling the onFlip method of each listener
    public void update() throws FlipForbiddenException {
        for (FlipListener listener : listeners) {
            listener.onFlip(this);
        }
    }
    
}
