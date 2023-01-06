/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jobschedulerframework;


public class Pair<K,V>{
    K key;
    V value;
    public Pair(K key , V value){
	this.key = key;
	this.value = value;
    }
    public K getKey(){
	return key;
    }

    public V getValue(){
	return value;
    }
    public String toString(){
        return "Key: " + key + "\nValue: " + value;
    }
}

