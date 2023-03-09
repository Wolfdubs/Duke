
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.util.Map.Entry;

public class EMM extends AbstractMarkovModel{
    private HashMap<String, ArrayList<String>> map;
    
    public EMM(int keyLength) {
        super(keyLength);
        map = new HashMap<>();
    }
    
    @Override
    public void setTraining(String s) {
        super.setTraining(s);
        buildMap();
    }
    
    private void buildMap(){
        for (int i = 0; i < myText.length() - (keyLength-1); i++){
            String current = myText.substring(i, i + keyLength);
            String follows = "";
            if (i + keyLength < myText.length())
                follows = myText.substring(i+keyLength, i + keyLength+1);
            if (map.containsKey(current))
                map.get(current).add(follows);
            else {
                ArrayList<String> followsList = new ArrayList<>();
                followsList.add(follows);
                map.put(current,followsList);
            }
        }
    }
    
    @Override
    protected ArrayList<String> getFollows(String key){
        return map.get(key);
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
            ArrayList<String> follows = getFollows(key);
            if (follows == null) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public String toString(){
        return String.format("EfficientMarkovModel of order %d",keyLength);
    }
    
    
    public void printHashMapInfo() {
        System.out.println("It has " +  map.size() + " keys in the HashMap");
        int maxSize = 0;
        for (String key : map.keySet()) {
            maxSize = Math.max(maxSize, map.get(key).size());
//          System.out.printf("Key:\t[%s]\tvalues: ", key);
//          System.out.println(myMap.get(key));
        }
        System.out.println("The maximum number of keys following a key is " + maxSize);
        ArrayList<String> keys = new ArrayList<String>();
        for (String key : map.keySet()) {
            if(map.get(key).size() == maxSize){
                keys.add(key);
            }
        }
        System.out.println("Keys that have the largest ArrayList are: " + keys);
    }


}





































