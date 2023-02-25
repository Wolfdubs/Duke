
/**
 * Write a description of WordFreqneciesArray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordFrequenciesArray {
    
    private StorageResource words;
    
    public WordFrequenciesArray(){
        words = new StorageResource();
    }
    
    public void readWords(){
        words.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            words.add(word.toLowerCase());
        }
    }
    
    private boolean contains(String[] list, String word, int elementCount){
        for (int i = 0; i < elementCount; i++){
            if (list[i].equals(word)) return true;
        }
        return false;
    }
    
    public int numberofUniqueWords(){
        int uniqueWordsCount = 0;
        String[] wordsArray = new String[words.size()];
        for (String s : words.data()){
            if (! contains(wordsArray, s, uniqueWordsCount)) {
                wordsArray[uniqueWordsCount] = s;
                uniqueWordsCount++;
            }
        }
        return uniqueWordsCount;
    }
    
    public void tester(){
        readWords();
        System.out.println("Total number of words read = " + words.size());
        System.out.println("Number of unique words = " + numberofUniqueWords());
        
    }
    
    
}
