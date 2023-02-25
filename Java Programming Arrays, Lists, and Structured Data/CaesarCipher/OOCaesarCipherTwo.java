
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OOCaesarCipherTwo {
    String alphabet, alphabetLower, shiftedAlphabet1, shiftedAlphabetLower1, shiftedAlphabet2, shiftedAlphabetLower2;
    int mainKey1, mainKey2;
    
    
    public OOCaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        shiftedAlphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0,key2);
    }
    
    
    public String encryptTwoKeys(String message){
        StringBuilder sb = new StringBuilder(message);
        
        for (int i = 0; i < sb.length(); i+=2) {
            char currentChar = sb.charAt(i);
            int positionInAlphabet;
            if (Character.isUpperCase(currentChar)) {
                positionInAlphabet = alphabet.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabet1.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            } else if (Character.isLowerCase(currentChar)){
                positionInAlphabet = alphabetLower.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabetLower1.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            }  
        }
        for (int i = 1; i < sb.length(); i+=2) {
            char currentChar = sb.charAt(i);
            int positionInAlphabet;
            if (Character.isUpperCase(currentChar)) {
                positionInAlphabet = alphabet.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabet2.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            } else if (Character.isLowerCase(currentChar)){
                positionInAlphabet = alphabetLower.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabetLower2.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            }  
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public String decryptTwoKeys(String input){
        OOCaesarCipherTwo cc2 = new OOCaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return cc2.encryptTwoKeys(input);
    }
    
    
}
