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
        List<AJob<String, String>> jobs = new ArrayList();
        
        try {
            
            System.out.print("Insert directory path: ");
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            path = br.readLine();
            
            if (!path.endsWith("/")) {
                path = path + "/";
            }
            
            File files = new File(path);
            for (String fileName : files.list()) {
                if (fileName.endsWith(".txt")) {
                    jobs.add(new AnagramJob(path + fileName));
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AnagramsCounterJobScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jobs.stream();
        
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
            
            Stream<Pair<String, Integer>> counts = outputs.map(pair -> new Pair(pair.getKey(), pair.getValue().size()));
            counts.forEach(pair -> {
                try {
                    bw.write("<" + pair.getKey() + "> - <" + pair.getValue() + ">\n");
                } catch (IOException ex) {
                    Logger.getLogger(AnagramsCounterJobScheduler.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(AnagramsCounterJobScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }

        


    }
    
}
