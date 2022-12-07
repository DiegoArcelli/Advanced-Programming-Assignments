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
import java.util.Collections;
import java.util.List;

/**
 *
 * @author diego
 */
public class EightController extends JLabel implements VetoableChangeListener, RestartListener {
    
    private List<Integer> configuration;

    public EightController() {
        this.configuration = Arrays.asList(1,2,3,4,5,6,7,8,9);
        this.setText("START");
        this.setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void setConfiguration(List<Integer> configuration) {
        Collections.copy(this.configuration, configuration);
    }
    
    private int getPosition(int value) {
        
        int pos = 0; 
        for (int i = 0; i < 9; i++) {
            if (this.configuration.get(i) == value) {
                pos = i;
                break;
            }
        }
        
        return pos;

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
        int holePos = this.getPosition(newValue);

        System.out.println(this.configuration);
        
        if ((holePos != currPos - 3) && (holePos != currPos + 3) && (holePos != currPos - 1) && (holePos != currPos + 1)) {
            setText("KO");
            throw new PropertyVetoException("The operation is not admissible", evt);
        }
        
        this.setText("OK");
        this.configuration.set(currPos, newValue);
        this.configuration.set(holePos, oldValue);
        

    }

    @Override
    public void onRestartListener(RestartEvent evt) {
        List<Integer> permutation = evt.permutation;
        this.setConfiguration(permutation);
    }
    
    
  
}