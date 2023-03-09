
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1 + " ");
        sb.append(key2 + " ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next + " ");
            key1 = key2;
            key2 = next;
          //  System.out.println("Key = " + key + " -> following words = " + follows);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target1, String target2, int start){
        for (int i = start; i < words.length-1; i++){
            if (words[i].equals(target1) && words[i+1].equals(target2))
                return i;   
        }
        return -1;
    }
    //There is a mistake in Owen's getFollows method! Think about where you want to 
    //start searching after the follow word for each key word is found.
    public ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length){
            int index = indexOf(myText, key1, key2, pos);
            if (index == -1) break;
            if (index >= myText.length-2) break;
            String next = myText[index+2];
            follows.add(next);
            pos = index+2;
        }
        return follows;
    }
    
    public void testIndexOf(){
       // String[] testWords = {"this","is","just","a","test","yes",
       //     "this","is","a","simple","test"};
        String testWordsString = "this is just a test yes this is a simple test";
        String[] testWords = testWordsString.split("\\s+");
       
        int thisIndex0 = indexOf(testWords, "this","is",0);
        System.out.println("index of \"this is\" starting at index 0 = " + thisIndex0);
        int thisIndex3 = indexOf(testWords, "this","is",3);
        System.out.println("index of \"this is\" starting at index 3 = " + thisIndex3);
        int frogIndex0 = indexOf(testWords, "frog","dog",0);
        System.out.println("index of \"frog dog\" starting at index 0 = " + frogIndex0);
        int frogIndex5 = indexOf(testWords, "frog","dog",5);
        System.out.println("index of \"frog dog\" starting at index 5 = " + frogIndex5);
        int simpleIndex2 = indexOf(testWords, "simple","test",2);
        System.out.println("index of \"simple test\" starting at index 2 = " + simpleIndex2);
        int testIndex0 = indexOf(testWords, "test","void",5);
        System.out.println("index of \"test void\" starting at index 5 = " + testIndex0);
    }

}


































