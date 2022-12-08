/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eightpuzzle;

/**
 *
 * @author diego
 */
public interface FlipListener {
    
    public void onFlip(FlipEvent evt) throws FlipForbiddenException;
    
}
