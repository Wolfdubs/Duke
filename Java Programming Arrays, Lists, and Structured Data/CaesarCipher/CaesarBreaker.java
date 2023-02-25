
/**
 * Write a description of CaersarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarBreaker {
    
    public String decryptTwoKeys(String encrypted){
        
        StringBuilder decrypted = new StringBuilder();
        StringBuilder evenChars = new StringBuilder(halfOfString(encrypted, 0));
        StringBuilder oddChars = new StringBuilder(halfOfString(encrypted, 1));
        
       
        CaesarCipher cc = new CaesarCipher();
        String decryptedEvens = cc.decryptByCharCount(evenChars.toString());
        String decryptedOdds = cc.decryptByCharCount(oddChars.toString());
        System.out.println("decryptedEvens = " + decryptedEvens);
        System.out.println("decryptedOdds = " + decryptedOdds);
        
        int j = 0;
        for (int i = 0; i < decryptedEvens.length(); i++) {
            decrypted.append(decryptedEvens.charAt(i));
            if (j < decryptedOdds.length()) {
               decrypted.append(decryptedOdds.charAt(j));
               j++;
            }
        }
        if (j < decryptedOdds.length()) {
            decrypted.append(decryptedEvens.substring(j));
        }
        System.out.println(decrypted.toString());
        return decrypted.toString();
    }
    
    public String decryptTwoKeys(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        StringBuilder decrypted = new StringBuilder();
        StringBuilder evenChars = new StringBuilder(halfOfString(encrypted, 0));
        StringBuilder oddChars = new StringBuilder(halfOfString(encrypted, 1));
        
       
        CaesarCipher cc = new CaesarCipher();
        String decryptedEvens = cc.decryptByCharCount(evenChars.toString());
        String decryptedOdds = cc.decryptByCharCount(oddChars.toString());
        System.out.println("decryptedEvens = " + decryptedEvens);
        System.out.println("decryptedOdds = " + decryptedOdds);
        
        int j = 0;
        for (int i = 0; i < decryptedEvens.length(); i++) {
            decrypted.append(decryptedEvens.charAt(i));
            if (j < decryptedOdds.length()) {
               decrypted.append(decryptedOdds.charAt(j));
               j++;
            }
        }
        if (j < decryptedOdds.length()) {
            decrypted.append(decryptedEvens.substring(j));
        }
        System.out.println(decrypted.toString());
        return decrypted.toString();
    }
    
    
    
    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i+=2){
            sb.append(message.charAt(i));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    
}




































