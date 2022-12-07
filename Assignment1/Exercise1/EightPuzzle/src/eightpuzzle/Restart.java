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
public class Restart extends JButton implements Serializable {
    
    private RestartEvent event;
    
    public Restart() {
        this.setText("RESTART");
        this.event = new RestartEvent();
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public void addRestartListener(RestartListener l) {
        this.event.addRestartListener(l);
    }
    
    public void permute() {
        this.event.update();
    }
    
}

