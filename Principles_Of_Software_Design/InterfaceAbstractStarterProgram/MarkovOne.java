
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel {

    public String getRandomText(int numChars){
    	if (myText == null){
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length()-1);
    	String key = myText.substring(index, index+1);
    	sb.append(key);
    	for(int k=0; k < numChars-1; k++){
    	    ArrayList<String> followingChars = getFollows(key);
    	    if (followingChars.size() == 0) break;
    	    index = myRandom.nextInt(followingChars.size());
    	    String next = followingChars.get(index);
    	    sb.append(next);
    	    key = next;
    	}
    	
    	return sb.toString();
    }
    
    public String toString(){
        return "MarkovModel of order 1";
    }
    
}



































































