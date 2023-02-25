
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    private int howMany(String stringa, String stringb){
        int startIndex = 0;
        int counter = 0;
        while(true){
            String currentOccurrence = findString(stringa, stringb, startIndex);
            if (currentOccurrence.isEmpty()) break;
            counter++;
            startIndex = startIndex + stringa.length();
        //    int stringIndex = stringb.indexOf(stringa, startIndex);
        //    System.out.println("stringIndex = " + stringIndex);
        //    if (stringIndex == -1) break;
        //    String currentOccurrence1 = stringb.substring(stringIndex, stringIndex + stringa.length());
        //    System.out.println("currentOccurrence1 = " + currentOccurrence1);
         //   String currentOccurrence = findString(stringa, stringb, startIndex);
        //    if (currentOccurrence.isEmpty()) break;
          //  counter++;
         //   System.out.println("counter = " + counter);
         //   startIndex = startIndex + stringIndex + stringa.length();
        //    System.out.println("startIndex = " + startIndex);
//            startIndex = startIndex +  stringa.length();
        }
        return counter;
    }
    
    private String findString(String stringa, String stringb, int startIndex){
        int stringIndex = stringb.indexOf(stringa, startIndex);
        if (stringIndex == -1) return "";
        return stringb.substring(stringIndex, stringIndex + stringa.length());
    }
    //start at index 0
    //check for incidence of stringa
    //if no incidence, return ""
    //if incidence, increment counter & move startIndex to just after that incidence
    //do this until no incidence, 
    
    
    private int countOccurrence(String stringa, String stringb){
        int startIndex = 0;
        int counter = 0;
        if (stringa.isEmpty() || stringb.isEmpty()) return 0;
        while(true){
            int stringaIndex = stringb.indexOf(stringa, startIndex);
            if (stringaIndex == -1) break;
            counter++;
            startIndex = stringaIndex + stringa.length();
        }
        return counter;
    }
    
    public void testHowMany(){
        String stringA = "GAA";
        String stringB = "ATGAACGAATTGAATC";  //3
        int howMany1 = howMany(stringA, stringB);
        System.out.println("howMany1 = " + howMany1);
        String stringC = "AA";
        String stringD = "ATAAAA";            //2
        int howMany2 = howMany(stringC, stringC);
        System.out.println("howMany2 = " + howMany2);
        String stringE = "ASH";
        String stringF = "CASHFLASHDASHTRASHSLASHCRASH";   //6
        int howMany3 = howMany(stringE, stringF);
        System.out.println("howMany3 = " + howMany3);
        String stringG = "ABC";
        String stringH = "LMNOPQRS";            //0
        int howMany4 = howMany(stringG, stringH);
        System.out.println("howMany4 = " + howMany4); 
        String stringI = "";
        String stringJ = "ATAAAA";           //0
        int howMany5 = howMany(stringI, stringJ);
        System.out.println("howMany5 = " + howMany5);
        String stringK = "AA";
        String stringL = "";                 //0
        int howMany6 = howMany(stringK, stringL);
        System.out.println("howMany6 = " + howMany6);  
    }
    
    public void testCounter(){
        String stringA = "GAA";
        String stringB = "ATGAACGAATTGAATC";  //3
        int howMany1 = countOccurrence(stringA, stringB);
        System.out.println("howMany1 = " + howMany1);
        String stringC = "AA";
        String stringD = "ATAAAA";            //2
        int howMany2 = countOccurrence(stringC, stringC);
        System.out.println("howMany2 = " + howMany2);
        String stringE = "ASH";
        String stringF = "CASHFLASHDASHTRASHSLASHCRASH";   //6
        int howMany3 = countOccurrence(stringE, stringF);
        System.out.println("howMany3 = " + howMany3);
        String stringG = "ABC";
        String stringH = "LMNOPQRS";            //0
        int howMany4 = countOccurrence(stringG, stringH);
        System.out.println("howMany4 = " + howMany4); 
        String stringI = "";
        String stringJ = "ATAAAA";           //0
        int howMany5 = countOccurrence(stringI, stringJ);
        System.out.println("howMany5 = " + howMany5);
        String stringK = "AA";
        String stringL = "";                 //0
        int howMany6 = countOccurrence(stringK, stringL);
        System.out.println("howMany6 = " + howMany6);
    }

}



















