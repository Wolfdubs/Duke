
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String contents = fr.asString(); 
        OOCaesarCipherTwo cc2 = new OOCaesarCipherTwo(8,18);
        String encryptedContent = cc2.encryptTwoKeys(contents);
        System.out.println("encrypted file contents:\n" + encryptedContent);
        String decryptedContent = cc2.decryptTwoKeys(encryptedContent);
        System.out.println("decrypted file contents:\n" + decryptedContent);
        

        
        String brokenDecrypted = breakOOCaesarCipherTwoKeys(encryptedContent);
        System.out.println("broken decrypted file contents:\n" + brokenDecrypted);
    }
    
    private String breakOOCaesarCipherTwoKeys(String encrypted){
        String odds, evens;
        int mostFrequentIndex = maxIndex(encrypted);
        int dKey = mostFrequentIndex - 4;  //4 is index of 'e'
        if (mostFrequentIndex < 4) dKey = 26 - (4-mostFrequentIndex);
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String encrypted2 = encrypted.replaceAll(Character.toString(alphabet.charAt(dKey)),"");
        int secondMostFrequentIndex = maxIndex(encrypted2);
        
        int dKey2 = secondMostFrequentIndex - 4;  //4 is index of 'e'
        if (secondMostFrequentIndex < 4) dKey2 = 26 - (4-secondMostFrequentIndex);
        
        OOCaesarCipherTwo cc2 = new OOCaesarCipherTwo(dKey,dKey2);
        System.out.println("Most frequenct index = " + mostFrequentIndex);
        String decrypted = cc2.decryptTwoKeys(encrypted);
        return decrypted;
    }
    
    
    
    private static int[] charOccurrenceInString(String input){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] charCounter = new int[26];
       
        for (int i = 0; i < input.length(); i++){
            char currentChar = input.charAt(i);
            int charIndex = alphabet.indexOf(Character.toLowerCase(currentChar));
            if (charIndex != -1) charCounter[charIndex]++;
        }
        for (int i = 0; i < charCounter.length; i++){
            System.out.println("Occurrences of " + alphabet.charAt(i) + " = " + charCounter[i]);
        }
        return charCounter;
    }
    
    private int maxIndex(String encrypted){
        int[] charFrequencies = Arrays.charOccurrenceInString(encrypted);
        int mostFrequentCount = 0;
        int mostFrequentIndex = -1;
        for (int i = 0; i < charFrequencies.length; i++) {
            if (charFrequencies[i] > mostFrequentCount) {
                mostFrequentCount = charFrequencies[i]; 
                mostFrequentIndex = i;
            }
        }
        return mostFrequentIndex;
    }
    
    private String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i+=2){
            sb.append(message.charAt(i));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
