/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eightpuzzle;

/**
 *
 * @author diego
 */


/*
Interface to implement if an object wants to register as a listener of the flip event
*/
public interface FlipListener {
    
    
    /*
    Abstract method that the listeners have to implement to set their behavior
    after the flip event is fired
    */
    public abstract void onFlip(FlipEvent evt) throws FlipForbiddenException;
    
}
