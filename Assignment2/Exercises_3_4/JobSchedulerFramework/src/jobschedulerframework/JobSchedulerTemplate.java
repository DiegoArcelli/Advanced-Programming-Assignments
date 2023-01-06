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
    
    public void main() {
        Stream<AJob<K,V>> jobs = emit();
        Stream<Pair<K,V>> outputs_list = compute(jobs);
        Stream<Pair<K,List<V>>> outputs = collect(outputs_list);
        output(outputs);
    }
    
    
    protected abstract Stream<AJob<K,V>> emit();
    
    private Stream<Pair<K,V>> compute(Stream<AJob<K,V>> jobs) {
        Stream<Pair<K,V>> outputs = jobs.map(job -> job.execute()).flatMap(x -> x);
        return outputs;
    }
    
    private Stream<Pair<K,List<V>>> collect(Stream<Pair<K,V>> outputs) {
        
        Map<K, List<V>> map = outputs.collect(
                Collectors.groupingBy(Pair<K, V>::getKey,
                            Collectors.mapping(Pair<K, V>::getValue, Collectors.toList())
                        )
        );
        
        return map.entrySet().stream().map(entry -> new Pair(entry.getKey(), entry.getValue()));
    }
    
    protected abstract void output(Stream<Pair<K,List<V>>>  outputs);
        
    
}
