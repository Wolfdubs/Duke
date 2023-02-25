
/**
 * Write a description of CountCommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CountCommonWords {

    public void countCommonWordsAcrossFiles(){
        String[] files = {"romeo.txt"};//, "hamlet.txt", "caesar.txt", "macbeth.txt"};
      //  String[] files = {"small.txt"};
        String[] commonWords = getCommonWords();
        int[] wordOccurrenceCounts = new int[commonWords.length];
        for (int i = 0; i < files.length; i++){
            FileResource fr = new FileResource(files[i]);
            countWords(fr, commonWords, wordOccurrenceCounts);
            System.out.println("Completed processing of " + files[i]);
        }
        for (int i = 0; i < wordOccurrenceCounts.length; i++){
            System.out.println(commonWords[i] + "  -> " + wordOccurrenceCounts[i]);
        }
        
    }
    
    public String[] getCommonWords(){
        FileResource resource = new FileResource("commonWords.txt");
        String[] commonWords = new String[20];
        int index = 0;
        for (String s : resource.words()) {
            commonWords[index] = s;
            index++;
        }
        return commonWords;
    }
    
    public void countWords(FileResource resource, String[] commonWords, int[] wordOccurrenceCounts) {
        for (String word : resource.words()){
            word = word.toLowerCase();
            int index = indexOf(commonWords, word);
            if (index != -1) wordOccurrenceCounts[index]++;
        }
    }
    
    public int indexOf(String[] commonWords, String word){
        for (int i = 0; i < commonWords.length; i++){
            if (commonWords[i].equals(word)){
                return i;
            }
              
        }
        return -1;
    }
}





















