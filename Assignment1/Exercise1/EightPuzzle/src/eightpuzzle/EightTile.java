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
import java.beans.PropertyVetoException;
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
    
    private final int position;
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
        // System.out.println(changes);
        // this.setLabel(label);
        //
    }
    
    public EightTile(int position) {
        this.position = position;
    }

    public void setLabel(int newLabel) {
        int oldLabel = label;
        System.out.println(oldLabel);
        System.out.println(newLabel);
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
    
    private void setColor(Color c) {
        this.setBackground(c);
        this.setText(label != 9 ? Integer.toString(label) : "");
    }
    
    
    public Color determineColor() {
        if (label == position && label != 9) {
            return Color.GREEN;
        } else if (label != 9) {
            return Color.YELLOW;
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
    
    public void addPropertyChangeListenerz(PropertyChangeListener l) {
        this.changes.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListenerz(PropertyChangeListener l) {
        this.changes.removePropertyChangeListener(l);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int oldValue = (int)evt.getOldValue();
                
        if (label == 9) {
            this.label = oldValue;
            setColor(determineColor());
            //this.setLabel(oldValue);
        }
    }

    @Override
    public void onRestart(RestartEvent evt) {
        List<Integer> perm = evt.permutation;
        int newLabel = perm.get(position-1);
        this.label = newLabel;
        setColor(determineColor());
    }
    
    
}