
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       int count = 0;
       for (int i=0; i< in.size(); i++) {
           if(checkInSortedOrder(in)) break;
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            count++;
        }
       System.out.println(count + " passes were required");
    }
    
    private int getLargestDepth(ArrayList<QuakeEntry> quakeData, int fromIndex){
        int maxIndex = fromIndex;
        for (int i = fromIndex + 1; i < quakeData.size(); i++){
            if (quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()){
                maxIndex = i;
            } 
        }
        return maxIndex;
    }
    
    private void sortByLargestDepth(ArrayList<QuakeEntry> quakeData){
        for (int i = 0; i < quakeData.size(); i++){
            int largestIndex = getLargestDepth(quakeData, i);
            QuakeEntry current = quakeData.get(i);
            QuakeEntry max = quakeData.get(largestIndex);
            quakeData.set(i,max);
            quakeData.set(largestIndex,current);
        }
    }

    public void testSelectionSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        sortByMagnitudeWithCheck(list);
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } */
    }
    
    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i = 0; i < quakeData.size()-numSorted-1; i++){
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()){
                QuakeEntry current = quakeData.get(i);
                QuakeEntry smaller = quakeData.get(i+1);
                quakeData.set(i, smaller);
                quakeData.set(i+1,current);
            }
        }
    }
    
    private void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData){
        for (int i = 0; i < quakeData.size()-1; i++){
            onePassBubbleSort(quakeData, i);
        }
    }
    
    private void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData){
        int count = 0;
        for (int i = 0; i < quakeData.size()-1; i++){
            if(checkInSortedOrder(quakeData)) break;
            onePassBubbleSort(quakeData, i);
            count++;
        }
        System.out.println(count + " passes were required");
    }
        
    private boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData){
        for (int i = 0; i < quakeData.size()-1; i++){
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude())
                return false;
        }
        return true;
    }
    
    public void testBubbleSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } */
    }
    
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    
    
    private void sortByLargestDepthQuizQ1(ArrayList<QuakeEntry> quakeData){
        for (int i = 0; i < 70; i++){
            int largestIndex = getLargestDepth(quakeData, i);
            QuakeEntry current = quakeData.get(i);
            QuakeEntry max = quakeData.get(largestIndex);
            quakeData.set(i,max);
            quakeData.set(largestIndex,current);
        }
    }
    
    public void testSelectionSortQuizQ1() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        sortByLargestDepthQuizQ1(list);
        for (QuakeEntry qe: list) 
            System.out.println(qe);
        
    }
}











