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
Class which represent the Restart event which is fired whenever the Restart button is clicked
*/

public class RestartEvent {
    
    // list which represent the new randomly generatated configuration of the board
    public List<Integer> permutation;
    
    // list of all the listeners of the event
    private List<RestartListener> listeners;
    
    public RestartEvent() {
        this.permutation = Arrays.asList(1,2,3,4,5,6,7,8,9);
        listeners = new ArrayList<RestartListener>();
    }
    
    // function to register a new listener
    public void addRestartListener(RestartListener l) {
        this.listeners.add(l);
    }
    
    /*
    Function to randomly generate a new configuration of the
    board and fire the restart event, calling the onRestart method
    of all the listeners
    */
    public void update() {
        Collections.shuffle(permutation);
        for (RestartListener listener : listeners) {
            listener.onRestart(this);
        }
    }
    
}
