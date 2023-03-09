
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
          //  System.out.println("Key = " + key + " -> following words = " + follows);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target, int start){
        for (int i = start; i < words.length; i++){
            if (words[i].equals(target))
                return i;   
        }
        return -1;
    }
    //There is a mistake in Owen's getFollows method! Think about where you want to 
    //start searching after the follow word for each key word is found.
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length){
            int index = indexOf(myText, key, pos);
            if (index == -1) break;
            if (index >= myText.length-1) break;
            String next = myText[index+1];
            follows.add(next);
            pos = index+1;
        }
        return follows;
    }
    
    public void testIndexOf(){
       // String[] testWords = {"this","is","just","a","test","yes",
       //     "this","is","a","simple","test"};
        String testWordsString = "this is just a test yes this is a simple test";
        String[] testWords = testWordsString.split("\\s+");
       
        int thisIndex0 = indexOf(testWords, "this",0);
        System.out.println("index of \"this\" starting at index 0 = " + thisIndex0);
        int thisIndex3 = indexOf(testWords, "this",3);
        System.out.println("index of \"this\" starting at index 3 = " + thisIndex3);
        int frogIndex0 = indexOf(testWords, "frog",0);
        System.out.println("index of \"frog\" starting at index 0 = " + frogIndex0);
        int frogIndex5 = indexOf(testWords, "frog",5);
        System.out.println("index of \"frog\" starting at index 5 = " + frogIndex5);
        int simpleIndex2 = indexOf(testWords, "simple",2);
        System.out.println("index of \"simple\" starting at index 2 = " + simpleIndex2);
        int testIndex0 = indexOf(testWords, "test",5);
        System.out.println("index of \"test\" starting at index 5 = " + testIndex0);
    }

}


































