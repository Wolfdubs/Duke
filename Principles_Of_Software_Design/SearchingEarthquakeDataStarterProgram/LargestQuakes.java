
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakes = parser.read(source);
        System.out.println("read data for "+quakes.size());
        // for (QuakeEntry qe : quakes)
         //   System.out.println(qe);
       // System.out.println(indexOfLargest(quakes) + "\t" +  quakes.get(indexOfLargest(quakes)));
        ArrayList<QuakeEntry> largestQuakes = (getLargest(quakes,50));
        for (QuakeEntry qe : largestQuakes)
            System.out.println(qe);
    }
    
    private int indexOfLargest(ArrayList<QuakeEntry> quakes){
        int largestIndex = 0;
        double largestQuakeSize = -1;
        for (QuakeEntry qe : quakes){
            if (qe.getMagnitude() > largestQuakeSize){
                largestIndex = quakes.indexOf(qe);
                largestQuakeSize = qe.getMagnitude();
            }
        }
        return largestIndex;
    }
    
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<>(quakeData.size());
        ArrayList<QuakeEntry> inputCopy = new ArrayList<>(quakeData);
        for (int i = 0; i < howMany; i++) {
            int largestIndex = 0;
            QuakeEntry largestQuake = null;
            for (int j = 0; j < inputCopy.size(); j++){
                largestIndex = indexOfLargest(inputCopy);
                largestQuake = inputCopy.get(largestIndex);
            }
            answer.add(largestQuake);
            inputCopy.remove(largestQuake);
        }
        return answer;
        
        
    }
    
    
    
}













