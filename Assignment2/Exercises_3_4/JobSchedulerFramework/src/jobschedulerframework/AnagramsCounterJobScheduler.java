/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jobschedulerframework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author diego
 */
public class AnagramsCounterJobScheduler extends JobSchedulerTemplate<String, String> {

    @Override
    protected Stream<AJob<String, String>> emit() {
        
        
        String path = null;
        Stream<AJob<String, String>> jobs = null;
        
        try {
            
            System.out.print("Insert directory path (es. Books): ");
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            path = br.readLine();
            
            if (!path.endsWith("/")) {
                path = path + "/";
            }
            
            // needed since path is not effectively final
            String finalPath = path;
            
            File files = new File(path);
            
            Stream<String> files_stream = Arrays.stream(files.list());
            
            /*
            Stream to read all the file in the passed directory, filter those that end with .txt
            and create an AnagramJob for each file
            */
            jobs = files_stream.filter(fileName -> fileName.endsWith(".txt"))
                        .map(fileName -> new AnagramJob(finalPath + fileName));
            
            
        } catch (IOException ex) {
            Logger.getLogger(AnagramsCounterJobScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return jobs;
        
    }

    @Override
    protected void output(Stream<Pair<String, List<String>>> outputs) {
        
        try {
            File f = new File ("count_anagrams.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
            
            FileWriter fw = null;
            fw = new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            // create a stream of pairs where each string contains the lenght of its list
            Stream<Pair<String, Integer>> counts = outputs.map(pair -> new Pair(pair.getKey(), pair.getValue().size()));
            
            // use mutable reduction to concatenate all 
            StringBuilder builder = counts.collect(
                    StringBuilder::new,
                    (col, pair) -> col.append(String.format("<%s> - <%d>\n", pair.getKey(), pair.getValue())),
                    (col1, col2) -> col1.append(col2)
            );
            
            bw.write(builder.toString());
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(AnagramsCounterJobScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }

        


    }
    
}
