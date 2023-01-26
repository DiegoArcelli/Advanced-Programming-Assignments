/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eightpuzzle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author diego
 */


/*
Bean which represent the flip button
*/
public class Flip extends JButton {
    
    /* 
    object that represent the flip event which is used to maintain
    the list of all the listeners of the event
    */
    private FlipEvent flip;

    public Flip() {
        
        flip = new FlipEvent();
        this.setText("FLIP");
        this.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    // method to register a new listener
    public void addFlipListener(FlipListener l) {
        this.flip.addFlipListener(l);
    }
    
    
    /*
    it fires the flip event to notify the flip. If the controller doesn't throw the 
    FlipForbiddenException then the position labels of the tiles in position firstPos
    and seconPos are flipped
    */
    public void update(EightTile tile1, EightTile tile2) {
        try {
            // firest the flip event
            this.flip.update();
            /*
            if after the event is fired the controller doesn't throw a
            FlipForbiddenException then the flip button flips tile 1 and tile 2
            */
            int label1 = tile1.getTileLabel();
            int label2 = tile2.getTileLabel();
            tile1.flip(label2);
            tile2.flip(label1);
        } catch (FlipForbiddenException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
