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
public class AnagramJob extends AJob<String, String> {
    
    private String path;
    
    public AnagramJob(String path) {
        this.path = path;
    }
    
    
    // function that compute the CIAO of a string passed as argument
    private String getCiao(String text) {
        char[] chars = text.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Override
    public Stream<Pair<String, String>> execute() {
                
        Stream<Pair<String, String>> lines = null;
        
        try {
            
            Stream<String> files = Files.lines(Paths.get(this.path));
            
            lines = files.map(line -> line.split(" "))
                    .flatMap(words -> Arrays.stream(words))
                    // remove punctuation and other special characters
                    .map(word -> word.replace(",", ""))
                    .map(word -> word.replace(".", ""))
                    .map(word -> word.replace(";", ""))
                    .map(word -> word.replace("-", ""))
                    .map(word -> word.replace(":", ""))
                    .map(word -> word.replace("?", ""))
                    .map(word -> word.replace("!", ""))
                    .map(word -> word.replace("\"", ""))
                    .map(word -> word.replace("'", ""))
                    .map(word -> word.replace("/", ""))
                    .map(word -> word.replace("(", ""))
                    .map(word -> word.replace(")", ""))
                    //.map(word -> word.replaceAll(pattern, ""))
                    // remove empty words
                    .filter(word -> !word.equals(" ") && !word.equals(""))
                    // remove words that do not contain alphabetical characters
                    .filter(word -> word.matches("[a-zA-Z]+"))
                    // remove words with less then 4 characters
                    .filter(word -> word.length() >= 4)
                    // convert the words to lowercase
                    .map(word -> word.toLowerCase())
                    // create the pairs (ciao(word), word)
                    .map(word -> new Pair<String, String>(getCiao(word), word));
                        
        } catch (IOException ex) {
            Logger.getLogger(AnagramJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lines;
        
    }
    
}
