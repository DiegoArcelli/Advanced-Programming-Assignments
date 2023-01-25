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
public class EightController extends JLabel implements VetoableChangeListener, RestartListener, FlipListener {
    
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
        
        // get row and column of the tile to move
        int currCol = currPos % 3;
        int currRow = currPos / 3;
        
        // get row and column of the tile of the hole
        int holeCol = holePos % 3;
        int holeRow = holePos / 3;
        
        
        
        /*System.out.println("Hole pos: " + holePos);
        System.out.println("Curr pos : " + currPos);
        System.out.println("Hole col: "  + holeCol);
        System.out.println("Hole row: "  + holeRow);
        System.out.println("Curr col: "  + currCol);
        System.out.println("Curr row: "  + currRow);*/
        
        // check if the tile to move is in the same row of the hole and they are adjacent
        boolean cond1 = (((holeCol == currCol - 1 ) || (holeCol == currCol + 1)) && holeRow == currRow);
        
        // check if the tile to move is in the same column of the hole and they are adjacent
        boolean cond2 = (((holeRow == currRow - 1 ) || (holeRow == currRow + 1)) && holeCol == currCol);
        
        if (cond1 || cond2) {
            this.setText("OK");
            this.configuration.set(currPos, newValue);
            this.configuration.set(holePos, oldValue);
        } else {
            setText("KO");
            throw new PropertyVetoException("The operation is not admissible", evt);
        }

    }

    @Override
    public void onRestart(RestartEvent evt) {
        List<Integer> permutation = evt.permutation;
        this.setConfiguration(permutation);
    }

    @Override
    public void onFlip(FlipEvent evt) throws FlipForbiddenException {
        
        int first = evt.getFirstPos()-1;
        int second = evt.getSecondPos()-1;
        
        
        int ninePos = this.configuration.get(8);
        
        if (ninePos != 9) {
            throw new FlipForbiddenException("Cannot flip");
        }
        
        int label1 = this.configuration.get(first);
        int label2 = this.configuration.get(second);
        
        this.configuration.set(first, label2);
        this.configuration.set(second, label1);
    }
    
    
  
}