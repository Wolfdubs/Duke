
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int keyLength;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public AbstractMarkovModel(int keyLength) {
        myRandom = new Random();
        this.keyLength = keyLength;
        
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> subsequentChars = new ArrayList<>();
        int pos = 0;
        while(pos < myText.length()){
            int indexOfKey = myText.indexOf(key,pos);
            if (indexOfKey == -1) break;
            if (indexOfKey + (key.length()-1)  == myText.length()-1) break;
            char firstSubsequentChar = myText.charAt(indexOfKey + 1 + (key.length()-1));
            String firstSubsequentCharAsString = Character.toString(firstSubsequentChar);
            subsequentChars.add(firstSubsequentCharAsString);
            pos = indexOfKey;
            pos++;
        }
        return subsequentChars;
    }
 
    abstract public String getRandomText(int numChars);

}
