
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;



public class WordLengths {
    
    private int[] countWordLengths(FileResource resource, int[] counts){
        for (String word : resource.words()){
           // System.out.println("current word = " + word);
            int charCounter = 0;
            if (Character.isLetter(word.charAt(0))){
                charCounter++;
            }
            if (Character.isLetter(word.charAt(word.length()-1))){
                charCounter++;
            }
            for (int i = 1; i < word.length()-1; i++){
                charCounter++;
            }
   
            if (charCounter >= counts.length-1) counts[counts.length-1]++;
            else counts[charCounter]++;
        }
        return counts;
    }
    
    private int[] countWordLengthsPure(FileResource resource, int[] counts){
        for (String word : resource.words()){
           // System.out.println("current word = " + word);
            int charCounter = 0;
            for (Character ch : word.toCharArray()){
             //   System.out.println("current character in word = " + ch);
                if (Character.isLetter(ch)) charCounter++;
             //   System.out.println("current charCount for the char " + ch + " = " + charCounter);
            }
            counts[charCounter]++;
            for (int i : counts) {
            //    System.out.print(i + ", ");
            }
           // System.out.println();
        }
        return counts;
    }
    
    private int indexOfMax(int[] values){
        int maxOccurrence = 0;
        int maxIndex = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxOccurrence){ 
                maxOccurrence = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        int[] wordLengthCounts = countWordLengths(fr, counts);
        for (int i = 0; i < wordLengthCounts.length; i++) {
            System.out.println("The count of words of length " + i + " = " + wordLengthCounts[i]);
        }
        int mostCommonWordLength = indexOfMax(wordLengthCounts);
        System.out.println("The most common word length = " + mostCommonWordLength + ", with " + 
            wordLengthCounts[mostCommonWordLength] + " occurrences");
    }
    
    
    private HashMap hm = new HashMap();
    
    public void countWordLengthsGit(FileResource resource, int[] counts){
//		for (String line: resource.lines()) {
//			System.out.println(line);
//		}

		for(String word : resource.words()){
			int wordlength=0;
			StringBuilder sb = new StringBuilder(word);
			//System.out.println(word);
			for (int k=0;k<sb.length();k++){
				if (k==0 && !Character.isLetter(sb.charAt(k))){
					sb.deleteCharAt(k);					
				}
				else if (k==sb.length()-1 && !Character.isLetter(sb.charAt(k))){
					sb.deleteCharAt(k);
				}
				else{
					wordlength++;
				}
			}
			String resultString = sb.toString();
			counts[wordlength]++;
			hm.put(resultString, wordlength);
		}

		for(int index=0;index<counts.length;index++) {
			if (counts[index] != 0) {
				System.out.print(counts[index] + " words of length " + index + ": ");
				Set set = hm.entrySet();
				// Get an iterator
				Iterator i = set.iterator();
				// Display elements
				while(i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					if (me.getValue().equals(index))
						System.out.print(me.getKey() + " ");
				}
				System.out.println();
			}
		}
	}
	
	public void testCountWordLengthsGit(){
		FileResource resource = new FileResource();
		int[] counts = new int[31];
		countWordLengths(resource, counts);
		System.out.println(indexOfMax(counts));
	}


}
