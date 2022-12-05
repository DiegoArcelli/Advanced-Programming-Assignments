/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eightpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import javax.swing.JLabel;
import java.util.Arrays;

/**
 *
 * @author diego
 */
public class EightController extends JLabel implements VetoableChangeListener {
    
    private int[] configuration;

    public EightController() {
        this.setText("START");
        this.setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void setConfiguration(int[] configuration) {
        this.configuration = configuration.clone();
    }

        
    private int[] getCoordinates(int pos) {
        int row = pos/3;
        int col = pos % 3;
        int[] pair = {row, col}; 
        return pair;
    }
    
    private int getPosition(int value) {
        
        int pos = 0; 
        for (int i = 0; i < 9; i++) {
            if (this.configuration[i] == value) {
                pos = i;
                break;
            }
        }
        
        return pos;

    }
    
    private boolean checkAdmissibility(int[] currPos, int[] holePos) {
        
        return true;
        
    }
    
    
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                
        int oldValue = (int)evt.getOldValue();
        int newValue = (int)evt.getNewValue();
                
        /*if (oldValue == 9) {
            System.out.println("Cannote move the current hole");
            throw new PropertyVetoException("Cannot move the hole", evt);
        }*/
        
        int currPos = this.getPosition(oldValue);
        int holePos = this.getPosition(9);        
        
        if ((holePos != currPos - 3) && (holePos != currPos + 3) && (holePos != currPos - 1) && (holePos != currPos + 1)) {
            throw new PropertyVetoException("The operation is not admissible", evt);
        }
        
        this.configuration[currPos] = 9;
        this.configuration[holePos] = oldValue;
        

    }
    
    
  
}