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
Exeception which is thown if the flip operation is forbidden
beacause the hole is not in position 9
*/
public class FlipForbiddenException extends Exception {
    
    public FlipForbiddenException(String errorMessage) {
        super(errorMessage);
    }
    
}
