
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.util.Map.*;
import edu.duke.*;

public class CodonCount {
    
    private Map<String, Integer> mapCodons;
    private Set<String> uniqueCodons;
    
    public CodonCount(){
        mapCodons = new HashMap<>();
        uniqueCodons = new HashSet<>();
    }
    
    private void buildCodonMap(int start, String dna){
        mapCodons.clear();
        for (int i = start; i < dna.length()-2; i+=3){
            String currentCodon = dna.substring(i, i+3);
            if (mapCodons.containsKey(currentCodon)){
                mapCodons.put(currentCodon, mapCodons.get(currentCodon)+1);
            } else {
                mapCodons.put(currentCodon, 1);
            }
        }
    }
    
    private String getMostCommonCodon(){
        int frequency = 0;
        String mostFrequent = "";
        for (Entry<String,Integer> entry : mapCodons.entrySet()){
            if (entry.getValue() > frequency) {
                frequency = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }
    
    private void printCodonCounts(int start, int end){
        for (String s : mapCodons.keySet()){
            int occurrences = mapCodons.get(s);
            if (occurrences >= start && occurrences <= end) {
                System.out.println(s + "\t" + occurrences);
            }
        }
    }
    
    private void makeSetOfUniqueCodons(){
        uniqueCodons.clear();
        for (String codon : mapCodons.keySet()){
            uniqueCodons.add(codon);
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String contents = fr.asString().toUpperCase().trim();
        int start = 7, end = 7;
        
        buildCodonMap(0,contents);
        System.out.println("Reading frame starting from index 0 contains " + mapCodons.size() + " codons");
        System.out.println("Most common codon = " + getMostCommonCodon() + ", which occurs " + mapCodons.get(getMostCommonCodon()) + " times");
        System.out.println("Codons occurring between " + start + " and " + end + " times: "); 
        printCodonCounts(start,end);
        makeSetOfUniqueCodons();
        System.out.println("unique codons from index 0 = " +  uniqueCodons.size());
        
        buildCodonMap(1,contents);
        System.out.println("Reading frame starting from index 1 contains " + mapCodons.size() + " codons");
        System.out.println("Most common codon = " + getMostCommonCodon() + ", which occurs " + mapCodons.get(getMostCommonCodon()) + " times");
        System.out.println("Codons occurring between " + start + " and " + end + " times: "); 
        printCodonCounts(start,end);
        makeSetOfUniqueCodons();
        System.out.println("unique codons from index 1 = " +  uniqueCodons.size());
        
        buildCodonMap(2,contents);
        System.out.println("Reading frame starting from index 2 contains " + mapCodons.size() + " codons");
        System.out.println("Most common codon = " + getMostCommonCodon() + ", which occurs " + mapCodons.get(getMostCommonCodon()) + " times");
        System.out.println("Codons occurring between " + start + " and " + end + " times: "); 
        printCodonCounts(start,end);
        makeSetOfUniqueCodons();
        System.out.println("unique codons from index 2 = " +  uniqueCodons.size());
        
        
        
    }
    
    

}










































