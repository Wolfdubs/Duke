
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    
    private List<String> characterNames;
    private List<Integer> characterWordCounts;
    
    public CharactersInPlay(){
        characterNames = new ArrayList<>();
        characterWordCounts = new ArrayList<>();
    }
    
    private void update(String person){
        int index = characterNames.indexOf(person);
        if (index == -1) {
            characterNames.add(person);
            characterWordCounts.add(1);
        } else {
            int frequency = characterWordCounts.get(index);
            characterWordCounts.set(index, frequency+1);
        }
    }
    
    private void findAllCharacters(){
        characterNames.clear();
        characterWordCounts.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            if (line.indexOf(".") != -1) {
                update(line.substring(0, line.indexOf(".")));
            }
        }
        
    }
    
    public int maxIndex(){
        int max = characterWordCounts.get(0);
        int maxIndex = 0;
        for (int i = 0; i < characterWordCounts.size(); i++){
            if (characterWordCounts.get(i) > max) {
                max = characterWordCounts.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
        int mainCharacterMinimumLines = 2;
        findAllCharacters();
        for (int i = 0; i < characterNames.size(); i++){
            if (characterWordCounts.get(i) >= mainCharacterMinimumLines) {
                System.out.println(characterNames.get(i) + " : " + characterWordCounts.get(i));
            }
        }
        System.out.println("The character with most lines = " + characterNames.get(maxIndex())
            + ", with this many lines = " + characterWordCounts.get(maxIndex()));
    }
    
    public void charactersWithNumParts(int minLines, int maxLines){
        findAllCharacters();
        for (int i = 0; i < characterNames.size(); i++){
            if (characterWordCounts.get(i) >= minLines && characterWordCounts.get(i) <= maxLines) {
                System.out.println(characterNames.get(i) + " : " + characterWordCounts.get(i));
            }
        }
    }
}



































