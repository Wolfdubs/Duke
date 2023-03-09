
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

    public int compare(QuakeEntry q1, QuakeEntry q2){
        String[] q1Words = q1.getInfo().split(" ");
        String[] q2Words = q2.getInfo().split(" ");
        String q1LastWord = q1Words[q1Words.length-1];
        String q2LastWord = q2Words[q2Words.length-1];
        int lastWordComparison = q1LastWord.compareTo(q2LastWord);
        if (lastWordComparison == 0)
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        else return lastWordComparison;
    }
}
