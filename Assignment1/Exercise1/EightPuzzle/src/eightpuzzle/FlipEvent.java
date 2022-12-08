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
public class FlipEvent {
    
    private List<FlipListener> listeners;
    private int firstPos;
    private int secondPos;

    public FlipEvent() {
        listeners = new ArrayList<FlipListener>();
        firstPos = 1;
        secondPos = 2;
    }
    
    public FlipEvent(int first, int second) {
        listeners = new ArrayList<FlipListener>();
        this.firstPos = first;
        this.secondPos = second;
    }
    
    public void addFlipListener(FlipListener l) {
        this.listeners.add(l);
    }
    
    
    public void update() throws FlipForbiddenException {
        for (FlipListener listener : listeners) {
            listener.onFlip(this);
        }
    }

    public int getFirstPos() {
        return firstPos;
    }

    public int getSecondPos() {
        return secondPos;
    }
    
    
}
