/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlserializer;

/**
 *
 * @author diego
 */
public class XMLSerializerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Object[] arr = new Object[1];
        arr[0] = new Student("Marco", "Polo", 23);
        XMLSerializer.serialize(arr, "prova");
        
        
    }
    
}
