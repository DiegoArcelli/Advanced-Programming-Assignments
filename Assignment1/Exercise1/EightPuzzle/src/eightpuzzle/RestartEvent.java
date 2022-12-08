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
public class RestartEvent {
    
    public List<Integer> permutation;
    private List<RestartListener> listeners;
    
    public RestartEvent() {
        this.permutation = Arrays.asList(1,2,3,4,5,6,7,8,9);
        listeners = new ArrayList<RestartListener>();
    }
    
    public void addRestartListener(RestartListener l) {
        this.listeners.add(l);
    }
    
    public void update() {
        Collections.shuffle(permutation);
        for (RestartListener listener : listeners) {
            listener.onRestart(this);
        }
    }
    
}
