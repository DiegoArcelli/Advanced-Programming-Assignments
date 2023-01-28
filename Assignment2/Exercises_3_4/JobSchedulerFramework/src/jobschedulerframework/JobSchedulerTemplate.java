/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jobschedulerframework;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author diego
 */
abstract public class JobSchedulerTemplate <K, V> {
    
    // template method
    public final void schedule() {
        Stream<AJob<K,V>> jobs = emit();
        Stream<Pair<K,V>> outputs_list = compute(jobs);
        Stream<Pair<K,List<V>>> outputs = collect(outputs_list);
        output(outputs);
    }
    
    
    // hot spot method which must be redefined by subclasses
    protected abstract Stream<AJob<K,V>> emit();
    
    
    // frozen spot method
    private Stream<Pair<K,V>> compute(Stream<AJob<K,V>> jobs) {
        
        /* 
        For each object of type AJob<K,V> in the stream the execute method is called, returing
        a stream of Pair<K, V>.
        
        flatMap is used since normal map would have returned an
        object of type Stream<Stream<Pair<K, V>>>
        */
        
        Stream<Pair<K,V>> outputs = jobs.flatMap(job -> job.execute());
        return outputs;
    }
    
    
    // frozen spot method
    private Stream<Pair<K,List<V>>> collect(Stream<Pair<K,V>> outputs) {
        
        /* 
        From the stream of type Stream<Pair<K,V>> we create a map that associate
        each unique key in the stream the list of values corresponding to that key
        */
        
        Map<K, List<V>> map = outputs.collect(
                Collectors.groupingBy(Pair<K, V>::getKey,
                            Collectors.mapping(Pair<K, V>::getValue, Collectors.toList())
                        )
        );
        
        /*
        The object of type Map<K, List<V>> is tansformed into an object of type Stream<Pair<K,List<V>>>
        */
        return map.entrySet().stream().map(entry -> new Pair(entry.getKey(), entry.getValue()));
    }
    
    // hot spot method which must be redefined by subclasses
    protected abstract void output(Stream<Pair<K,List<V>>>  outputs);
        
    
}
