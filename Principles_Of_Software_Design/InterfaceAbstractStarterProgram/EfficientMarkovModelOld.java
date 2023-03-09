
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.util.Map.Entry;

public class EfficientMarkovModelOld extends AbstractMarkovModel{
    
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModelOld(int keyLength) {
        super(keyLength);
        map = new HashMap<>();
    }
    
    private void buildMap(String key){
        if (!map.containsKey(key)) {
            ArrayList<String> followingChars = new ArrayList<>();
            int pos = 0;
            while(pos < myText.length()){
                int indexOfKey = myText.indexOf(key,pos);
                if (indexOfKey == -1) break;
                if (indexOfKey + (key.length()-1)  == myText.length()-1) break;
                char firstSubsequentChar = myText.charAt(indexOfKey + 1 + (key.length()-1));
                String firstSubsequentCharAsString = Character.toString(firstSubsequentChar);
                followingChars.add(firstSubsequentCharAsString);
                pos = indexOfKey;
                pos++;
            }
            map.put(key, followingChars);
        }     
    }
    
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
            map.clear();
            buildMap(key);
            ArrayList<String> follows = getFollows(key);
            if (map.get(key).size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public String toString(){
        return "EfficientMarkovModel of order " + keyLength;
    }
    
    
    protected void printHashMapInfo(){
        if (map.size() < 20) {
            for (Entry<String, ArrayList<String>> entry : map.entrySet()){
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
        System.out.println("HashMap size = " + map.size());
        
        int largestSize = 0;      
        ArrayList<String> keysWithLongestArrayLists = new ArrayList<>();
        for (String key : map.keySet()){
            if (map.get(key).size() > largestSize){
                largestSize = map.get(key).size();
                keysWithLongestArrayLists.add(key);
            }
        }
        System.out.println("largest arraylist size = " + largestSize);
        System.out.println("Keys with this largest arraylist size  = " + keysWithLongestArrayLists);
    }

}





































