
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    
    private boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            return true;
        else return false;
    }
    
    public void testIsVowel(){
        System.out.println("A isVowel = " + isVowel('A'));
        System.out.println("B isVowel = " + isVowel('B'));
        System.out.println("Z isVowel = " + isVowel('Z'));
        System.out.println("i isVowel = " + isVowel('i'));
        System.out.println("q isVowel = " + isVowel('q'));
        System.out.println("z isVowel = " + isVowel('z'));
    }
    
    private String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++){
            if (isVowel(sb.charAt(i))){
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public void testReplaceVowels(){
        String replaced1 = replaceVowels("Hello World", '*');
        System.out.println(replaced1);
        String replaced2 = replaceVowels("Hello World", ' ');
        System.out.println(replaced2);
        String replaced3 = replaceVowels("aeiouzzz", '$');
        System.out.println(replaced3);
    }
    
    private String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++){
            if (Character.toLowerCase(sb.charAt(i)) == ch && i % 2 == 0 )
                sb.setCharAt(i, '*');
            else if (Character.toLowerCase(sb.charAt(i)) == ch && i % 2 == 1 )
                sb.setCharAt(i, '+');
            if (Character.toLowerCase(sb.charAt(i)) == ch && i == 0)
                sb.setCharAt(i, '*');
        }
        return sb.toString();
    }
    
    public void testEmphasize(){
        System.out.println("emphasized " + emphasize("dna ctgaaactga", 'a'));
        System.out.println("emphasized " + emphasize("Mary Bella Abracadabra", 'a'));
        System.out.println("emphasized " + emphasize("dna ctgaaactga", 'g'));
        System.out.println("emphasized " + emphasize("Mary Bella Abracadabra", 'm'));
    }

}



















