/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlserializer;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class XMLSerializer {
    
    
    public XMLSerializer() {
        
    }
    
    public void serialize(Object arr[], String fileName) {
        
        // array list used to check if there are duplicated classes
        ArrayList<String> serialized_classes = new ArrayList<String>();
        
        
        String xml_file_text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";        
        
        // iterating over the array
        for (Object obj : arr) {
            
            // getting the class corresponding to the current object
            Class obj_class = obj.getClass();
            
            // getting the class name with also the packages
            String class_name_pkg = obj_class.getName();
            
            // check if an object of this class has already been serialized
            if (serialized_classes.contains(class_name_pkg)) {
                continue;
            } else {
                serialized_classes.add(class_name_pkg);
            }
            
            
            
            // getting the class name without the package
            String class_name = obj_class.getSimpleName();
            
            
            // check for the presence of the XMLable annotation in the current object
            if (obj_class.isAnnotationPresent(XMLable.class)) {
                
                xml_file_text += "<" + class_name + ">\n";
                Field[] fields = obj_class.getDeclaredFields();
                
                // iterate over all the fields of the class
                for (Field field : fields) {
                    
                    // check for the presence of the XMLfield annotation on the field
                    if (field.isAnnotationPresent(XMLfield.class)) {
                        
                        String type, name, value = null;
                        
                        field.setAccessible(true);
                        
                        // getting the value of the field
                        try {
                            value = field.get(obj).toString();
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(XMLSerializer.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(XMLSerializer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        XMLfield field_annotation = (XMLfield)field.getDeclaredAnnotation(XMLfield.class);
                        type = field_annotation.type();
                        
                        // checking if the optional argument "type" of the annotation has been set or not
                        name = field_annotation.name().equals("") ? field.getName() : field_annotation.name();
                        
                        xml_file_text += "\t<" + name + " type=\"" + type + "\">" + value  + "</" + name + ">\n";

                    }
                }
                
                xml_file_text += "</" + class_name + ">\n";
            } else {
                xml_file_text += "<" + class_name + ">\n";
                xml_file_text += "\t<notXMLable/>\n";
                xml_file_text += "</" + class_name + ">\n";
            }
        }
        
        
        try {
            FileWriter writer = new FileWriter(fileName + ".xml");
            writer.write(xml_file_text);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
    
    
}
