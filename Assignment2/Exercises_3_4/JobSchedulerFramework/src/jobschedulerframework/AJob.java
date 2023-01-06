/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jobschedulerframework;


import java.util.stream.Stream;

public abstract class AJob<K,V>{

    public abstract Stream<Pair<K,V>> execute();

}
