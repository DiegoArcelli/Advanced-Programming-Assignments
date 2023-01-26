/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eightpuzzle;

/**
 *
 * @author diego
 */

/*
Interface to implement if an object wants to register as a listener of the restart event
*/
public interface RestartListener {
    
    /*
    Abstract method that the listeners have to implement to set their behavior
    after the restart event is fired
    */
    public abstract void onRestart(RestartEvent evt);
    
}
