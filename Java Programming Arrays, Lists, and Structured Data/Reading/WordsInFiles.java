
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Map.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordsInFiles {

    private Map<String, ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            if (!map.containsKey(word)){
                ArrayList<String> filesWithWord = new ArrayList<>(Arrays.asList(f.getName()));
                map.put(word, filesWithWord);
            } else {
                ArrayList filesWithWord = map.get(word);
                filesWithWord.add(f.getName());
                map.put(word, filesWithWord);
            }
        }
    }
    
    private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int maxNumber = 0;
        for (String s : map.keySet()){
            if (map.get(s).size() > maxNumber){
                maxNumber = map.get(s).size();
            }
        }
        return maxNumber;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        buildWordFileMap();
        ArrayList<String> words = new ArrayList<>();
        for (Entry<String, ArrayList<String>> entry : map.entrySet()){
            if (entry.getValue().size() == number && (!words.contains(entry.getKey()))) {
                words.add(entry.getKey());
            }
        }
        System.out.println("words in " + number + " files = " + words);
        return words;
    }
    
    public int countWordsInNumFiles(int number){
        System.out.println("There are " + wordsInNumFiles(number).size() + " in " + number + " files");
        return wordsInNumFiles(number).size();
    }
    
    public ArrayList<String> wordsInNumFiles2(int number){
        buildWordFileMap();
        ArrayList<String> ReturnList = new ArrayList<String>();
        for(String word:map.keySet()){
            ArrayList<String> currentFileList = map.get(word);
            int currentNum = currentFileList.size();
            if (currentNum == number) {
                ReturnList.add(word);
            }
        
        }
        System.out.println("words in " + number + " files = " + ReturnList);
        return ReturnList;
    
    }
    
    public void printFilesIn(String word){
        System.out.println("running printFilesIn");
        buildWordFileMap();
        for (Entry<String, ArrayList<String>> entry : map.entrySet()){
            if (entry.getKey().equals(word)) {
                for (String fileName : entry.getValue()) {
                    System.out.println(fileName);
                }
            }
        }
    }
    
    public void numberOfWordsOccurringAcrossFiles(int numberOfFiles){
        Set<String> wordsAcrossFiles = new HashSet<>();
        for (ArrayList<String> valueList : map.values()){
            int counter = 0;
        }
        
    }
    
    private String extractWordFromArrayList(ArrayList<String> valueList){
        String currentWord = "";
            for (String word : valueList){
                currentWord = word;
            }
        return currentWord;    
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNumber = maxNumber();
        System.out.println("maxNumber = " + maxNumber);
        ArrayList<String> mostFrequentWords = wordsInNumFiles(maxNumber);
        System.out.println("most frequent word = " + mostFrequentWords);
     //   System.out.println("the most frequent word is in:\n");  printFilesIn(mostFrequentWords.get(0));
      //  System.out.println("the full map:");
      //  for (Entry<String, ArrayList<String>> entry : map.entrySet()){
      //      System.out.println(entry.getKey() + "\t" + entry.getValue());
      //  }
        
    }
    
}



















































