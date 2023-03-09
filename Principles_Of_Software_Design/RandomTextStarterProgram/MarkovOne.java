
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
	
    public MarkovOne() {
    	myRandom = new Random();
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
    
    protected ArrayList<String> getFollowsOld(String key){
        ArrayList<String> subsequentChars = new ArrayList<>();
        int indexOfKey = 0;
        //while (indexOfKey != -1)
        for (int i = indexOfKey; i < key.length()-1; i++){   //-1 to not get the next character after ast letter in string
            indexOfKey = myText.indexOf(key, indexOfKey);
            if (indexOfKey == -1) break;
            char subsequentsChar = myText.charAt(indexOfKey+1);
            //String subsequentChar = myText.substring(indexOfKey, indexOfKey+1);           
            String subsequentCharString = Character.toString(subsequentsChar);
            subsequentChars.add(subsequentCharString); 
            indexOfKey = indexOfKey++;
        }
        return subsequentChars;
    }
    
    
    
    protected ArrayList<String> getFollowsFirstChar(String key){
        ArrayList<String> subsequentChars = new ArrayList<>();
        String finalCharOfKey = Character.toString(key.charAt(key.length()-1));
        int firstInstanceOfKey = myText.indexOf(finalCharOfKey);
        if (firstInstanceOfKey == myText.length()-1) return subsequentChars;
        char firstSubsequentChar = myText.charAt(firstInstanceOfKey+1);
        String firstSubsequentCharAsString = Character.toString(firstSubsequentChar);
        subsequentChars.add(firstSubsequentCharAsString);
        return subsequentChars;
    }
    
    
    protected ArrayList<String> getFollowsForLoop(String key){
        ArrayList<String> subsequentChars = new ArrayList<>();
        String finalCharOfKey = Character.toString(key.charAt(key.length()-1));
        int indexOfKey = 0;
        for (int i = indexOfKey; i < myText.length(); i++){
            indexOfKey = myText.indexOf(key,i);
            if (indexOfKey + (key.length()-1)  == myText.length()-1) return subsequentChars;
            char firstSubsequentChar = myText.charAt((key.length()-1)+indexOfKey+1);
            String firstSubsequentCharAsString = Character.toString(firstSubsequentChar);
            subsequentChars.add(firstSubsequentCharAsString);
            indexOfKey++;
        }
        return subsequentChars;
    }
    
    protected ArrayList<String> getFollowsHeapOverflow(String key){
        ArrayList<String> subsequentChars = new ArrayList<>();
        String finalCharOfKey = Character.toString(key.charAt(key.length()-1));
        int pos = 0;
        int indexOfKey = 0;
        while(pos < myText.length()){
            indexOfKey = myText.indexOf(key,pos);
            if (indexOfKey == -1) break;
            if (indexOfKey + (key.length()-1)  == myText.length()-1) break;
            char firstSubsequentChar = myText.charAt((key.length()-1)+indexOfKey+1);
            String firstSubsequentCharAsString = Character.toString(firstSubsequentChar);
            subsequentChars.add(firstSubsequentCharAsString);
            pos = indexOfKey++;
        }
        return subsequentChars;
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



































































