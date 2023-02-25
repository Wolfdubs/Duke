
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4 {

    private static void readURLS(){
        URLResource urlResource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s: urlResource.words()){
            String caseAgnostic = s.toLowerCase();
            if (caseAgnostic.contains("youtube")){
                int startIndex = s.indexOf("\"");
                int endIndex = s.lastIndexOf("\"");
                int secondIndex = s.indexOf("\"", startIndex+1);
                System.out.println(s.substring(startIndex+1, secondIndex));
            }
        }
        System.out.println("BREAK/////////////////");
        for (String item : urlResource.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
           	int beg = item.lastIndexOf("\"",pos);
           	int end = item.indexOf("\"", pos+1);
           	System.out.println(item.substring(beg+1,end));
               }
   	}
        
    }
    
    
    public static void main(String[] args) {
        readURLS();
    }
}
