/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PQExamples;

//import static java.lang.System.out;
import Priority_queue.HeapPriorityQueue;
import Priority_queue.Entry;

import java.util.ArrayList;

/**
 *
 * @author antoniosilva
 */
public class AirTrafficCC {
    
    private HeapPriorityQueue<Integer,String> cc;
    int timeslot = 5;  // time slot allocated to land each plane
    
    public AirTrafficCC(Integer[] p, String[] f) {
        this.cc = new HeapPriorityQueue(p,f);
    }
    
    public String nextPlaneLanding(){
       return cc.min().getValue();
    }
    
    public void addPlane2Queue(String id, Integer pr) {
        cc.insert(pr,id);
    }
    
    public Entry<Integer,String> clearPlane4Landing() {
        return cc.removeMin();
    }
    
    public Integer nrPlanesWaiting() {
        return cc.size();
    }
    
    public Integer time2land(String id) {
       HeapPriorityQueue<Integer,String> clone = cc.clone();
       Integer result = 0;

       for(int i = 0; i < clone.size(); i++){
           if(clone.removeMin().getValue() == id) break;
           result += timeslot;
       }
       return result;
    }
    
    public void changePriority2(String id, Integer newp){
        ArrayList<Entry<Integer,String>> removed = new ArrayList<>();
        
        for(int i = 0; i < cc.size(); i++){
            Entry<Integer,String> entry = cc.removeMin();
            if(entry.getValue() == id) break;
            removed.add(entry);
        }
        for(Entry<Integer,String> e : removed){
            cc.insert(e.getKey(),e.getValue());
        }
        cc.insert(newp,id);
    }
}