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
    
    
    /*
    The controller stores the state of the board as list of nine element, where each element of the
    list represent a position in the board and the value of the element is the value of the label of 
    the tile in that position
    */
    
    private List<Integer> configuration;

    public EightController() {
        this.configuration = Arrays.asList(1,2,3,4,5,6,7,8,9);
        this.setText("START");
        this.setHorizontalAlignment(JLabel.CENTER);
    }
    
    // function use to set the list which represent the configuration of the tiles in the board
    public void setConfiguration(List<Integer> configuration) {
        Collections.copy(this.configuration, configuration);
    }
    
    // function which return the position of a tile in the board given its label value
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
        
        // check if the tile to move is in the same row of the hole and they are adjacent
        boolean cond1 = (((holeCol == currCol - 1 ) || (holeCol == currCol + 1)) && holeRow == currRow);
        
        // check if the tile to move is in the same column of the hole and they are adjacent
        boolean cond2 = (((holeRow == currRow - 1 ) || (holeRow == currRow + 1)) && holeCol == currCol);
        
        // if the move is legal then the controller updates its internal state of the board
        if (cond1 || cond2) {
            this.setText("OK");
            this.configuration.set(currPos, newValue);
            this.configuration.set(holePos, oldValue);
        /*
        if the move is not legal then the controller throws the PropertyVetoException
        exception in order to forbid the property change    
        */
        } else {
            setText("KO");
            throw new PropertyVetoException("The operation is not admissible", evt);
        }

    }

    
    /*
    if the restart event is fired the controller updates
    its internal representation of the board
    */
    @Override
    public void onRestart(RestartEvent evt) {
        List<Integer> permutation = evt.permutation;
        this.setConfiguration(permutation);
    }

    
    /*
    if the flip event is fired the controller check if the empty tile is in position 9
    */
    @Override
    public void onFlip(FlipEvent evt) throws FlipForbiddenException {
        
        int ninePos = this.configuration.get(8);
        
        // if the empty tile is not in position 9 then the controller forbid the flip
        if (ninePos != 9) {
            throw new FlipForbiddenException("Cannot flip");
        }
        
        
        /*
        if the empy tile is in position 9 then the controller updates
        its internal representation representation of the board
        */
        int label1 = this.configuration.get(0);
        int label2 = this.configuration.get(1);
                
        this.configuration.set(0, label2);
        this.configuration.set(1, label1);
        
    }
    
    
  
}