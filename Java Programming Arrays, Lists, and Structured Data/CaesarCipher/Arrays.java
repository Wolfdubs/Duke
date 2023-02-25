
/**
 * Write a description of Arrays here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.text.DecimalFormat;


public class Arrays {
    
    public void printDiceRolls(int rolls){
        Random random = new Random();
        int[] rollValues = new int[13];
    
        
        for (int i = 0; i < rolls; i++){
            int dice1 = random.nextInt(6)+1;
            int dice2 = random.nextInt(6)+1;
            int rollValue = dice1 + dice2;
            rollValues[rollValue]++;
        }
        
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 2; i < rollValues.length; i++){
            System.out.println("The number of rolls of " + i + " = " + rollValues[i] + " -> " + df.format(((rollValues[i]/(double)rolls)*100)) + "%");
        }
    }
    
    public static int[] charOccurrenceInString(String input){
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

}





















