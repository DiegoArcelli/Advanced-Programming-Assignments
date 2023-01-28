/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eightpuzzle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeListener;
import javax.swing.JButton;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.swing.Timer;

/**
 *
 * @author diego
 */
public class EightTile extends JButton implements Serializable, PropertyChangeListener, RestartListener {
    
    // position of the tile in the board
    private final int position; 
    
    // number of the tile in the board
    private int label;
    private VetoableChangeSupport vetos; 
    private PropertyChangeSupport changes;
    
    public EightTile() {
        this.position = 0;
        vetos = new VetoableChangeSupport(this);
        changes = new PropertyChangeSupport(this);
    }
    
    public EightTile(int position, int label) {
        vetos = new VetoableChangeSupport(this);
        changes = new PropertyChangeSupport(this);
        this.position = position;
        this.label = label;
        super.setText(Integer.toString(label));
        setColor(determineColor());
    }
    
    public EightTile(int position) {
        this.position = position;
    }

    
    
    public void setLabel(int newLabel) {
        int oldLabel = label;

        /* Tries to change the value of the label  */
        try {
            vetos.fireVetoableChange("label", oldLabel, newLabel);
            label = newLabel;
            this.setText(Integer.toString(label));
            setColor(determineColor());
            changes.firePropertyChange("label", oldLabel, newLabel);
        } catch (Exception e) {
            System.out.println("Cannot change " + oldLabel + " in " + newLabel);
            flash();
        }
    }
    
    
    public int getTileLabel() {
        return label;
    }
    
    // function to set the background propery of the tile
    private void setColor(Color c) {
        this.setBackground(c);
        // the text of the tile is changed according to the value of the label
        this.setText(label != 9 ? Integer.toString(label) : "");
    }
    
    
    // function used to deterine the color of the tile based on the value of label
    public Color determineColor() {
        
        // if the label is in its position and it is not the hole then it is green
        if (label == position && label != 9) {
            return Color.GREEN;
            
        // if the tile is not in its positiona nd it is no the hole then it is yellow
        } else if (label != 9) {
            return Color.YELLOW;
            
        // if the tile is the hole then it is gray
        } else {
            return Color.GRAY;
        }
    }
    
    
    public void flip(int newLabel) {
        this.label = newLabel;
        setColor(determineColor());
    }
    
    
    // function to execute the flash effect if the change of label is vetoed
    public void flash() {
        this.setColor(Color.RED);
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setColor(determineColor());
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    
    public void addVetoableChangeListener(VetoableChangeListener l) {
        this.vetos.addVetoableChangeListener(l);
    }
    
    public void removeVetoableChangeListener(VetoableChangeListener l) {
        this.vetos.removeVetoableChangeListener(l);
    }
    
    public void addPropertyChangeListeners(PropertyChangeListener l) {
        this.changes.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListeners(PropertyChangeListener l) {
        this.changes.removePropertyChangeListener(l);
    }

    
    /*
    Function which is executed when one of the tiles adjacent to the tile
    change its label value
    */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int oldValue = (int)evt.getOldValue();
                
        /*
        If the tile is currently the hole, then it sets as value of its label 
        property the the old value of the tile which has been moved.
        If th tile is currently not the hold, the value of its label property
        remains unchanged
        */
        if (label == 9) {
            this.label = oldValue;
            setColor(determineColor());
        }
    }

    
    // Function which is executed when the restart button is pressed
    @Override
    public void onRestart(RestartEvent evt) {
        // extract fromthe RestarEvent object the new configuraiton of the board
        List<Integer> perm = evt.permutation;
        
        // The tile extract its new value for the label property
        int newLabel = perm.get(position-1);
        this.label = newLabel;
        setColor(determineColor());
    }
    
    
}