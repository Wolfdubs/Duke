
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int keyLength;
	
    public MarkovModel(int keyLength) {
    	myRandom = new Random();
    	this.keyLength = keyLength;
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
    }
    
    public String getRandomText(int numChars){
    	if (myText == null){
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length()-keyLength);
    	String key = myText.substring(index, index + keyLength);
    	sb.append(key);
    	for(int k=0; k < numChars-keyLength; k++){
    	    ArrayList<String> followingChars = getFollows(key);
    	    if (followingChars.size() == 0) break;
    	    index = myRandom.nextInt(followingChars.size());
    	    String next = followingChars.get(index);
    	    sb.append(next);
    	    key = key.substring(1) + next;
    	}
    	
    	return sb.toString();
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
}






















































