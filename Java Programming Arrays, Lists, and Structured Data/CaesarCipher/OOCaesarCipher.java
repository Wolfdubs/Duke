
/**
 * Write a description of OOCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OOCaesarCipher {
    
    private String alphabet;
    private String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
    private String shiftedAlphabet;
    private String shiftedAlphabetLower;
    private int mainKey;
        
    public OOCaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);
        mainKey = key;
    }
    
    
    public String encrypt(String message){
        StringBuilder sb = new StringBuilder(message);
        for (int i = 0; i < sb.length(); i++) {
            char currentChar = sb.charAt(i);
            int positionInAlphabet;
            if (Character.isUpperCase(currentChar)) {
                positionInAlphabet = alphabet.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabet.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            } else if (Character.isLowerCase(currentChar)){
                positionInAlphabet = alphabetLower.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabetLower.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            }  
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public String decrypt(String input){
        OOCaesarCipher cc = new OOCaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }

}







































