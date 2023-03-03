
/**
 * Write a description of VigenereBreakerLanguages here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.util.Map.*;
import java.io.*;

public class VigenereBreakerLanguages {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++){
            String sliced = sliceString(encrypted, i, klength);
            int currentKey = cc.getKey(sliced);
            key[i] = currentKey;
        }
        return key;
    }

    
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionaryWordsSet = new HashSet<>();
        for (String line : fr.lines()){
            dictionaryWordsSet.add(line.toLowerCase());
        }
        return dictionaryWordsSet;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
       // String[] stringArray = message.split("\\W");
        int counter = 0;
        for (String word : message.split("\\W")){
            if (dictionary.contains(word))
                counter++;
        }
        return counter;
    }
    
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        //char mostCommonChar = 'e';
        String mostCommonChar = mostCommonCharIn(dictionary);
        int maxValidWords = 0;
        String maxDecrypted = "";
        int keyLengthUsed = -1;
        for (int i = 1; i < 100; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar.charAt(0));
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int validWords = countWords(decrypted, dictionary);
            if (validWords > maxValidWords) {
                maxValidWords = validWords;
                maxDecrypted = decrypted;
                keyLengthUsed = i;
            }
        }
        System.out.println("KeyLengthUsed = " + keyLengthUsed);
        return maxDecrypted;
    }
    
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();  
        DirectoryResource dr = new DirectoryResource();
        HashMap<String,HashSet<String>> dictionaries = new HashMap<>();
        for (File f : dr.selectedFiles()){
            FileResource fileResource = new FileResource(f);
            HashSet<String> dictionarySet = readDictionary(fr);
            dictionaries.put(f.getName(),dictionarySet);
        }
        HashMap<String,String> decryptedByLanguage = breakForAllLangs(encrypted, dictionaries);
        System.out.println(decryptedByLanguage.get("French"));
    }
    
    public char mostCommonCharInOld(HashSet<String> dictionary){
        HashMap<Character, Integer> map = new HashMap<>();
        for (String word : dictionary){
            for (Character c : word.toCharArray()){
                if (!map.containsKey(c)){
                    map.put(c,1);
                } else{
                    map.put(c,map.get(c)+1);
                }
            }
        }
        char mostCommonChar = ' ';
        int mostCommonFrequency = 0;
        for (Entry<Character, Integer> entry : map.entrySet()){
            if (entry.getValue() > mostCommonFrequency){
                mostCommonFrequency = entry.getValue();
                mostCommonChar = entry.getKey();
            }
            
        }
        System.out.println("most common char = " + mostCommonChar);
        return mostCommonChar;
    }
    
    public String mostCommonCharIn(HashSet<String> dictionary){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for (char ch = 'a'; ch <= 'z'; ++ch) 
              map.put(String.valueOf(ch), 0); 
        
        for(String word: dictionary) {
            word = word.toLowerCase();
            String[] letters = word.split("");
            for (String letter: map.keySet()) {
                for (String s: letters) {
                    if (s.equals(letter)) 
                        map.put(letter, map.get(letter)+1);
                }
            }
        }
        
        int max = 0;
        String result ="";
         
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (max < e.getValue()) {
                max = e.getValue();
                result = e.getKey();
            }
        }
      
        return result;
    }
    
    public HashMap<String,String> breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        HashMap<String, String> decryptedMessages = new HashMap<>();
        int maxValidWords = 0;
        String language = "";
       // String decrypted = "";
        for (String currentLanguage : languages.keySet()){
            System.out.println("Currently working on " + currentLanguage);
            String currentDecrypted = breakForLanguage(encrypted, languages.get(currentLanguage));
            int validWords = countWords(currentDecrypted, languages.get(currentLanguage));
            if (validWords > maxValidWords) {
                maxValidWords = validWords;
                language = currentLanguage;
            //    decrypted = currentDecrypted;
            }
            decryptedMessages.put(currentLanguage, currentDecrypted);
        }
        System.out.println("Language = " + language);
        System.out.println(maxValidWords + " validwords\n");
        return decryptedMessages;
    }
    

}


































