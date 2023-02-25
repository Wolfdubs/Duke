
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> words;
    private ArrayList<Integer> wordFrequencies;
    
    public WordFrequencies(){
        words = new ArrayList<>();
        wordFrequencies = new ArrayList<>();
    }
    
    private void findUnique(){
        words.clear();
        wordFrequencies.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = words.indexOf(word);
            if (index == -1) {
                words.add(word);
                wordFrequencies.add(1);
            }
            else {
                int frequency = wordFrequencies.get(index);
                wordFrequencies.set(index,frequency+1);
            }
        }
    }
    
    private void findUniqueOld(){
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            int index = words.indexOf(word);
            if (index == -1) {
                words.add(word);
                wordFrequencies.add(1);
            }
            else {
                int frequency = wordFrequencies.get(index);
                wordFrequencies.set(index,frequency++);
            }
        }
    }
    
    
    public int findIndexOfMax(){
	int max = wordFrequencies.get(0);
	int maxIndex = 0;
	for(int k=0; k < wordFrequencies.size(); k++){
	    if (wordFrequencies.get(k) > max){
		max = wordFrequencies.get(k);
		maxIndex = k;
	    }
	}
	return maxIndex;
	}
    
    public void tester(){
        findUnique();
        System.out.println("# Unique Words = " + words.size());
        /*System.out.println("# Frequency Of Each Word:");
        for (int i = 0; i < words.size(); i++){
         //   System.out.println(words.get(i) + "\t" + wordFrequencies.get(i));
            System.out.println(wordFrequencies.get(i) + "\t" + words.get(i));
        }*/
        int maxIndex = findIndexOfMax();
        System.out.println("The most frequent word = " + words.get(maxIndex) + 
        ", which occurs " + wordFrequencies.get(maxIndex) + " times");
    }
    
    

}
