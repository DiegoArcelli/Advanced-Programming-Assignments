/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jobschedulerframework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author diego
 */
public class AnagramJob extends AJob {
    
    private String path;
    
    public AnagramJob(String path) {
        this.path = path;
    }
    
    private String getCiao(String text) {
        char[] chars = text.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Override
    public Stream execute() {
                
        Stream<Pair<String, String>> lines = null;
        
        try {
            
            Stream<String> files = Files.lines(Paths.get(this.path));
            
            lines = files.map(line -> line.split(" "))
                    .flatMap(words -> Arrays.stream(words))
                    .filter(word -> !word.equals(" ") && !word.equals(""))
                    .filter(word -> word.matches("[a-zA-Z]+"))
                    .filter(word -> word.length() >= 4)
                    .map(word -> word.toLowerCase())
                    .map(word -> new Pair<String, String>(getCiao(word), word));
                        
        } catch (IOException ex) {
            Logger.getLogger(AnagramJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lines;
        
    }
    
}
