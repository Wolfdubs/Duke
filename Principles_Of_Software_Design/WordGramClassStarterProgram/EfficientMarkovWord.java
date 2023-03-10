
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private Random random;
    private String[] myText;
    private int myOrder;
    private Map<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order){
        random = new Random();
        myOrder = order;
        map = new HashMap<>();
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public void setRandom(int seed){
        random = new Random(seed);
    }
    
    private void buildMap(){
        for (int i = 0; i <= myText.length - myOrder; i++){
            WordGram wg = new WordGram(myText,i,myOrder);
            String follows = "";
            if (i + myOrder < myText.length)
                follows = myText[i + myOrder];
            if (map.containsKey(wg))
                map.get(wg).add(follows);
            else {
                ArrayList<String> followsList = new ArrayList<>();
                followsList.add(follows);
                map.put(wg,followsList);
            }
        }
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for(int i=start; i < words.length - myOrder; i++) {
                WordGram wg = new WordGram(words,i,myOrder);
                if (wg.equals(target)) 
                    return i;
        }
        return -1;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        if (map.get(kGram)!=null)
            return map.get(kGram);
        else return new ArrayList<>();
        
    }
    
    
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = random.nextInt(myText.length-myOrder);  // random word to start with
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg.toString() + " ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0)
                break;
            index = random.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next + " ");
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    public void printHashMapInfo(){
        if (map.size() < 30){
            System.out.println("Printing the HashMap");
            for (WordGram key : map.keySet()){
                System.out.println(key + " " + map.get(key));
            }
        }
        System.out.println("HashMap size = " + map.size());
        
        int longestArrayList = 0;
        for (WordGram key : map.keySet())
            longestArrayList = Math.max(longestArrayList, map.get(key).size());
        System.out.println("The longest follows length is size " + longestArrayList);
        
        ArrayList<WordGram> wgsWithMaxFollows = new ArrayList<>();
        for (Map.Entry<WordGram, ArrayList<String>> entry : map.entrySet()){
            if (entry.getValue().size() == longestArrayList){
                wgsWithMaxFollows.add(entry.getKey());
            }
        }
        System.out.println("WordGrams with size " + longestArrayList + " follows are : " + wgsWithMaxFollows);
    }
}




























