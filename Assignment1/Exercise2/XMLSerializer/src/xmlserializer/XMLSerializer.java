/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlserializer;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class XMLSerializer {
    
    
    public XMLSerializer() {
        
    }
    
    public static void serialize(Object arr[], String fileName) {
        
        String xml_file_text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        
        for (Object obj : arr) {
            Class obj_class = obj.getClass();
            
            if (obj_class.isAnnotationPresent(XMLable.class)) {
                
                String class_name = obj_class.getName();
                
                xml_file_text += "<" + class_name + ">\n";
                
                Field[] fields = obj_class.getDeclaredFields();
                for (Field field : fields) {
                    
                    if (field.isAnnotationPresent(XMLfield.class)) {
                        
                        String type, name, value = null;
                        
                        field.setAccessible(true);
                        
                        try {
                            value = field.get(obj).toString();
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(XMLSerializer.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(XMLSerializer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        XMLfield field_annotation = (XMLfield)field.getDeclaredAnnotation(XMLfield.class);
                        type = field_annotation.type();
                        name = field_annotation.name().equals("[undefined]") ? field.getName() : field_annotation.name();
                        
                        xml_file_text += "\t<" + name + " type=\"" + type + "\">" + value  + "</" + name + ">\n";

                    }
                }
                
                xml_file_text += "</" + class_name + ">\n";
                
            } else {
                xml_file_text += "<notXMLable/>\n";
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
