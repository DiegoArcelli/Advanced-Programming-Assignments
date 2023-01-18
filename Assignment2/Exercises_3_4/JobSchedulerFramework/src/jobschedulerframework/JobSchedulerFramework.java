/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jobschedulerframework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author diego
 */
public class JobSchedulerFramework {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AnagramsCounterJobScheduler job = new AnagramsCounterJobScheduler();
        job.schedule();

    }
    
}
