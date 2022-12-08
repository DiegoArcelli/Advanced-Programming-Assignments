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
public class Flip extends JButton {
    
    private FlipEvent flip;

    public Flip() {
        
        flip = new FlipEvent(1,2);
        this.setText("FLIP");
        this.setHorizontalAlignment(SwingConstants.CENTER);
        
    }
    
    public void addFlipListener(FlipListener l) {
        this.flip.addFlipListener(l);
    }
    
    public void update(EightTile tile1, EightTile tile2) {
        try {
            this.flip.update();
            int label1 = tile1.getTileLabel();
            int label2 = tile2.getTileLabel();
            tile1.flip(label2);
            tile2.flip(label1);
        } catch (FlipForbiddenException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
