
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    private boolean twoOccurrences(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        int index2 = stringb.indexOf(stringa, index + stringa.length());
        return (index2 != -1);
    }
    
    private String lastPart(String stringa, String stringb){
        if (!stringb.contains(stringa)){
            return stringb;
        } else {
            return stringb.substring(stringb.indexOf(stringa) + stringa.length());
        }
    }
    
    private void testing(){
        String string1= "by";
        String string2 = "a story by Abby Kane";
        System.out.println(string1 + ", " + string2);
        System.out.println(twoOccurrences(string1, string2));
        String string3= "a";
        String string4 = "banana";
        System.out.println(string3 + ", " + string4);
        System.out.println(twoOccurrences(string3, string4));
        String string5= "car";
        String string6 = "driving my car";
        System.out.println(string5 + ", " + string6);
        System.out.println(twoOccurrences(string5, string6));
        String string7= "inept";
        String string8 = "reputation";
        System.out.println(string7 + ", " + string8);
        System.out.println(twoOccurrences(string7, string8));
        String string9= "by";
        String string10 = "a story by Abby Kane";
        System.out.println(string9 + ", " + string10);
        System.out.println(lastPart(string9, string10));
        String string11= "an";
        String string12 = "banana";
        System.out.println(string11 + ", " + string12);
        System.out.println(lastPart(string11, string12));
        String string13= "zoo";
        String string14 = "forest";
        System.out.println(string13 + ", " + string14);
        System.out.println(lastPart(string13, string14));
        String string15= "wond";
        String string16 = "the wonder of you";
        System.out.println(string15 + ", " + string16);
        System.out.println(lastPart(string15, string16));
        
    }
    
    public static void main(String[] agrs) {
        Part3 part3 = new Part3();
        part3.testing();
    }

}





















