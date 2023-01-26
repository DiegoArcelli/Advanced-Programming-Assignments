/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eightpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author diego
 */

/*
Bean which represent the restart button
*/

public class Restart extends JButton implements Serializable {
    
    /* 
    object that represent the restart event which is used to maintain
    the list of all the listeners of the event
    */
    private RestartEvent event;
    
    public Restart() {
        this.setText("RESTART");
        this.event = new RestartEvent();
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    
    // function to register a new event listener
    public void addRestartListener(RestartListener l) {
        this.event.addRestartListener(l);
    }
    
    
    // function to call in order to fire the restart event
    public void permute() {
        this.event.update();
    }
    
}

