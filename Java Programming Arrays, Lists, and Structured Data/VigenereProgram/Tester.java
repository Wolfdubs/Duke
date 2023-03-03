import edu.duke.*;
import java.util.*;

/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(8);
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        String encrypted = cc.encrypt(fr.asString());
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        char encryptedChar = cc.encryptLetter('y');
        System.out.println(encryptedChar);
        char decryptedChar = cc.decryptLetter(encryptedChar);
        System.out.println(decryptedChar);
        char encryptedCharB = cc.encryptLetter('!');
        System.out.println(encryptedCharB);
        char decryptedCharB = cc.decryptLetter(encryptedCharB);
        System.out.println(decryptedCharB);
    }
    
    public void testCaesarCracker(){
        FileResource fr = new FileResource("VigenereTestData/titus-small_key5.txt");
        CaesarCracker ccr = new CaesarCracker();
        System.out.println(Arrays.toString(ccr.countLetters(fr.asString())));
        System.out.println(ccr.getKey(fr.asString()));
        System.out.println(ccr.decrypt(fr.asString()));
        
    }
    
    public void testVigenereCipher(){
        VigenereCipher vc = new VigenereCipher(new int[]{17,14,12,4});
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        String encrypted = vc.encrypt(fr.asString());
        String decrypted = vc.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
        
    }
    
    public void testVigenereBreakerSliceString(){
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    }
    
    public void testVigenereBreakerTryKeyLength(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("messages/secretmessage1.txt");
        int[] keys = vb.tryKeyLength(fr.asString(), 4, 'e');
        System.out.println(Arrays.toString(keys));
        
    }
    
    public void testMostCommonCharIn(){
        VigenereBreakerLanguages vbl = new VigenereBreakerLanguages();
        FileResource frE = new FileResource("English");
        FileResource frP = new FileResource("dictionaries/Portuguese");
        HashSet<String> dict = vbl.readDictionary(frP);
        vbl.mostCommonCharIn(dict);
    }
    
    public void testBreakForLanguageVBL(){
        VigenereBreakerLanguages vbl = new VigenereBreakerLanguages();
        FileResource frE = new FileResource("English");
        FileResource frP = new FileResource("dictionaries/Portuguese");
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashSet<String> dictE = vbl.readDictionary(frE);
        HashSet<String> dictP = vbl.readDictionary(frP);
        String decryptedE = vbl.breakForLanguage(encrypted, dictE);
        String decryptedP = vbl.breakForLanguage(encrypted, dictP);
        System.out.println(decryptedE);
        System.out.println(decryptedP);
    }
}








































