
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{

    private int keyLength;
	
    public MarkovModel(int keyLength) {
    	this.keyLength = keyLength;
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
    
    public String toString(){
        return "MarkovModel of order " + keyLength;
    }
}






















































