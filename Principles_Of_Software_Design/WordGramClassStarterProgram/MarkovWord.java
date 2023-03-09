
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private Random random;
    private String[] myText;
    private int myOrder;
    
    public MarkovWord(int order){
        random = new Random();
        myOrder = order;
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public void setRandom(int seed){
        random = new Random(seed);
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
	for(int i=start; i < words.length - myOrder; i++) {
            WordGram wg = new WordGram(words,i,myOrder);
            if(wg.equals(target)) 
                return i;
	}
	return -1;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
	ArrayList<String> follows = new ArrayList<>();
	int pos = 0;
	while(pos < myText.length) {
	    int start = indexOf(myText, kGram, pos);
	    if(start == -1 || start >= myText.length - 1) 
	        break;
	    String next = myText[start + myOrder];
	    follows.add(next);
	    pos = start + 1;
	}
	return follows;
    }
    
    public String getRandomTextOld(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = random.nextInt(myText.length-myOrder);  // random word to start with
        int finalIndex = index+myOrder;
        StringBuilder temp = new StringBuilder();
        for (int i = index; i < finalIndex; i++){
            temp.append(myText[i]);
        }
        String[] temps = temp.toString().split("\\s+");
        WordGram wgZ = new WordGram(temps,0, myOrder);
        for (String s : temps) sb.append(s + " ");
        
        WordGram wg = new WordGram(myText, 0, myOrder);
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = random.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next + " ");
            wg = new WordGram(myText, k, myOrder);
          //  System.out.println("Key = " + key + " -> following words = " + follows);
        }
        
        return sb.toString().trim();
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
}




























