import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
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
    
    //just for my own use - not from specs
    public int breakForLanguageValidWordCount(String encrypted, HashSet<String> dictionary){
        char mostCommonChar = 'e';
        int maxValidWords = 0;
        String maxDecrypted = "";
        int keyLengthUsed = -1;
        for (int i = 1; i < 100; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
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
        return maxValidWords;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        char mostCommonChar = 'e';
        int maxValidWords = 0;
        String maxDecrypted = "";
        int keyLengthUsed = -1;
        for (int i = 1; i < 100; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
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
    
    public void breakVigenereKnowingKeyLength () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();  
        int keyLength = 5;
        int[] encryptionKeys = tryKeyLength(encrypted, keyLength, 'e');
        VigenereCipher vc = new VigenereCipher(encryptionKeys);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();  
        FileResource dictionary = new FileResource();
        HashSet<String> dictionaryWordsSet = readDictionary(dictionary);
        String decrypted = breakForLanguage(encrypted, dictionaryWordsSet);
        System.out.println(decrypted);
    }
    
    public void validWordCounter(){   //for practice quiz q.4
        FileResource fr = new FileResource();
        String encrypted = fr.asString();  
        FileResource dictionary = new FileResource();
        HashSet<String> dictionaryWordsSet = readDictionary(dictionary);        
        String decrypted = breakForLanguage(encrypted, dictionaryWordsSet);
        int validWords = countWords(decrypted, dictionaryWordsSet);
        
        System.out.println("validWords = " + validWords);
    }
    
    
    
}










































