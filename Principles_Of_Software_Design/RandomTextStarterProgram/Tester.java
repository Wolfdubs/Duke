
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    
    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        mo.setTraining("this is a test yes this is a test.");
        System.out.println(mo.getFollows("t"));   //'h','e',' ','h','e','.'
        System.out.println(mo.getFollows("e"));   //'s','s','s'
        System.out.println(mo.getFollows("s"));   //' ',' ','t',' ',' ',' ','t'
        System.out.println(mo.getFollows("es"));  //'t',' ','t'
        System.out.println(mo.getFollows("."));   //[]
        System.out.println(mo.getFollows("t."));  //[]
        System.out.println("------------------");
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne mo = new MarkovOne();
        //markov.setRandom(42);
        mo.setTraining(st);
        ArrayList<String> followingChars = mo.getFollows("o");
        System.out.println(followingChars.size());        
    }
    
    public void testGetFollowsWithFile2(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne mo = new MarkovOne();
        //markov.setRandom(42);
        mo.setTraining(st);
        ArrayList<String> followingChars = mo.getFollows("th");
        System.out.println(followingChars.size());        
    }

}
