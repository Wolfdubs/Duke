
/**
 * Write a description of VigenereBreakerGit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class VigenereBreakerGit {
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
      String[] stringArray = message.split("\\W");
        int counter = 0;
        for (String s : stringArray){
            if (dictionary.contains(s))
                counter++;
        }
        return counter;
    }
    
  public String breakForLanguageGit1(String encrypted, HashSet<String> dict) {
        int max = 0;
        int keyReturn[] = new int[100];
        int KeyLength = 0;
        String aMessage = new String();
        String largestDecryption = new String();
        String[] decrypted = new String[100];
        for(int klength =1; klength < 100 ; klength++) {
        keyReturn = tryKeyLength(encrypted,klength,'e');
        VigenereCipher VCipher  = new VigenereCipher(keyReturn) ;
        aMessage = VCipher.decrypt(encrypted);
        //counts is a value returned, no use starting from 0
        int counts = countWords(aMessage, dict);
        if(counts > max){
            max = counts;
            largestDecryption = aMessage;
            KeyLength = klength;
        } 
                }
        System.out.println("Max counts:" + max);        
        System.out.println("The proper Key Length is :"+ KeyLength);         
        return largestDecryption;
    }
    
    public void breakForLanguageQuiz(String encrypted, HashSet<String> dict) {
        int max = 0;
        int keyReturn[] = new int[100];
        String aMessage = new String();
        String largestDecryption = new String();
        String[] decrypted = new String[100];
        keyReturn = tryKeyLength(encrypted,38,'e');
        VigenereCipher VCipher  = new VigenereCipher(keyReturn) ;
        aMessage = VCipher.decrypt(encrypted);
        //counts is a value returned, no use starting from 0
        int counts = countWords(aMessage, dict);
        if(counts > max){
            max = counts;
            largestDecryption = aMessage;
        }        
        System.out.println("Max counts:" + max);                
        //return largestDecryption;
   }
   
   public void breakVigenereTestGit () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();  
        FileResource dictionary = new FileResource();
        HashSet<String> dictionaryWordsSet = readDictionary(dictionary);
        breakForLanguageGit2(encrypted, dictionaryWordsSet);
       // System.out.println(decrypted);
    }
    
    
    public void breakForLanguageGit2(String encrypted, HashSet<String> dictionary)
    {
        char     mostCommonChar;
        int[]    keys;
        String   decrypted;
        int      count;
        int      max              = 0;
        int      theKeyLength;
        int[]    theKeys          = {0};
        String[] allWords         = null;
        int      validWords       = 0;
        String   decryptedMessage = null;

        mostCommonChar = mostCommonCharIn(dictionary);

        for(int keyLength = 1; keyLength <= 100; keyLength++)
        {
            keys              = tryKeyLength(encrypted, keyLength, mostCommonChar); 
            VigenereCipher vc = new VigenereCipher(keys);
            decrypted         = vc.decrypt(encrypted);
            count             = countWords(decrypted, dictionary);

            if(count > max)
            {
                max              = count;
                theKeyLength     = keyLength;
                theKeys          = keys;
                allWords         = encrypted.split("\\W+");
                validWords       = count;
                decryptedMessage = decrypted;
            }
        }

        // For the second part of week 4
        //System.out.println("\n- All words is: "       + allWords.length);
        System.out.println("\n- All valid words is: " + validWords);
        //System.out.println("\n- The key length is: "  + theKeys.length);
        //System.out.println("\n- The keys are: ");

        //for(int i = 0; i < theKeys.length; i++)
        //{
        //    System.out.println(theKeys[i]);
        //}

       // return decryptedMessage;
    }

    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[]  counter  = new int[26];

        for(String word: dictionary)
        {
            for(int i = 0; i < word.length(); i++)
            {
                char c               = word.charAt(i);
                int  indexOfChar     = alphabet.indexOf(c);

                if(indexOfChar != -1)
                {
                    counter[indexOfChar] = counter[indexOfChar] + 1;
                }
            }
        }

        int  maxVal     = 0;
        char commonChar = '\u0000';

        for(int i = 0; i < counter.length; i++)
        {
            if(counter[i] > maxVal)
            {
                maxVal     = counter[i];
                commonChar = alphabet.charAt(i);;
            }
        }

        return commonChar;        
    }
    
    public String breakForLanguageGit3(String encrypted, HashSet<String> dictionary) {
    	int[] wordcount = new int[100];
    	for (int i = 0; i < 100; i++) {
            int[] key = tryKeyLength(encrypted, i+1, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String result = vc.decrypt(encrypted);
            wordcount[i] = countWords(result, dictionary);
        }
    	
    	int largest = 0;
    	int index = 0;
    	for (int i = 0; i < 100; i++) {
            if (wordcount[i] > largest) {
                largest = wordcount[i];
                index = i;
            }
        }
        System.out.println("The largest count of valid words = " + largest);
    	
    	 int truekey = index + 1;
//         int[] key = tryKeyLength(encrypted, truekey, 'e'); // for English
    	 char mostCommonChar = mostCommonCharInGit3(dictionary).charAt(0);
    	 int[] key = tryKeyLength(encrypted, truekey, mostCommonChar);
         System.out.print("The keys are ");
         for (int i = 0; i < key.length; i++) {
             System.out.print(key[i] + " ");
         }
         System.out.println("\nThe key length is "+key.length);
         VigenereCipher vc = new VigenereCipher(key);
         return vc.decrypt(encrypted);
    }
    
    public void breakVigenereGit3 () {
    	FileResource fr = new FileResource();
    	String message = fr.asString();
    	FileResource fr2 = new FileResource();
    	HashSet<String> dictionary = readDictionary(fr2);
    	String decrypt = breakForLanguageGit3(message, dictionary);
    	//System.out.println(decrypt);
    }
    
    /** The method finds out which character, of the letters in the English alphabet, 
     *  appears most often in the words in dictionary 
     */
    public String mostCommonCharInGit3(HashSet<String> dictionary){
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
}
