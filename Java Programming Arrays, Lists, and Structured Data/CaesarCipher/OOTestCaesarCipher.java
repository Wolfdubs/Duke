
/**
 * Write a description of OOTestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class OOTestCaesarCipher {
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String contents = fr.asString(); 
        OOCaesarCipher cc = new OOCaesarCipher(18);
        String encryptedContent = cc.encrypt(contents);
        System.out.println("encrypted file contents:\n" + encryptedContent);
        String decryptedContent = cc.decrypt(encryptedContent);
        System.out.println("decrypted file contents:\n" + decryptedContent);
        
        String brokenDecrypted = breakOOCaesarCipherOneKey(encryptedContent);
        System.out.println("broken decrypted file contents:\n" + brokenDecrypted);
        
        
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
        int mostFrequent = 0;
        int mostFrequentIndex = -1;
        for (int i = 0; i < charFrequencies.length; i++) {
            if (charFrequencies[i] > mostFrequent) {
                mostFrequent = charFrequencies[i]; 
                mostFrequentIndex = i;
            }
        }
        return mostFrequentIndex;
    }
    
    private String breakOOCaesarCipherOneKey(String encrypted){
        int mostFrequentIndex = maxIndex(encrypted);
        int dKey = mostFrequentIndex - 4;  //4 is index of 'e'
        if (mostFrequentIndex < 4) dKey = 26 - (4-mostFrequentIndex);
        OOCaesarCipher cc = new OOCaesarCipher(dKey);
        System.out.println("Most frequenct index = " + mostFrequentIndex);
        String decrypted = cc.decrypt(encrypted);
        return decrypted;
    }
    
    

}
