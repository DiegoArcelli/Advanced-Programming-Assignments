/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xmlserializer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *
 * @author diego
 */
public class XMLSerializer {
    
    private Object objects[];
    
    public XMLSerializer() {
        this.objects = null;
    }
    
    public XMLSerializer(Object objects[]) {
        this.objects = objects;
    }
    
    public void serialize(Object objects[]) {
        for (Object obj : objects) {
            Class obj_class = obj.getClass();
            
            Method[] methods = obj_class.getMethods();
            for (Method method : methods) {
                String return_type = method.getReturnType().getName();
                String method_name = method.getName();
                
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    String param_type = parameter.getType().getName();
                    String param_name = parameter.getName();
                    
                }
                
            }
            
            Field[] fields = obj_class.getFields();
            for (Field field : fields) {
                String field_type = field.getType().getName();
                String field_name = field.getName();
            }
            
            Constructor[] constructors = obj_class.getConstructors();
            for (Constructor constructor : constructors) {
                String constructor_name = constructor.getName();
                Parameter[] parameters = constructor.getParameters();
                for (Parameter parameter : parameters) {
                    String param_type = parameter.getType().getName();
                    String param_name = parameter.getName();
                }
            }
            
            
            
        }
    }
    
    
    
    
}
