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
        
        Object[] arr = new Object[5];
        arr[0] = new Student("Marco", "Polo", 23);
        arr[1] = new Student("Mario", "Rossi", 21);
        arr[2] = new Empty();
        arr[3] = new Student("Gianfranco", "Fini", 17);
        arr[4] = new Empty();

        
        XMLSerializer xml_ser = new XMLSerializer();
        xml_ser.serialize(arr, "prova");
        
        
    }
    
}
