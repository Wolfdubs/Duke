
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkovOne() { 
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 120, 139); 
    }
    
    public void runMarkovTwo() { 
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo mw2 = new MarkovWordTwo(); 
        runModel(mw2, st, 120, 832); 
    } 
    
    
    
    

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("-----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n-----------------------------------");
    } 
    
    public void testMW1GetFollows(){
        String st = "this is just a test yes this is a simple test";
        MarkovWordOne mw1 = new MarkovWordOne();
        runModel(mw1, st, 15);
    }
    
    public void testMW1GetFollowsSpecific() {
        String s = "this is just a test yes this is a simple test";
        MarkovWordOne markovWord = new MarkovWordOne(); 
        markovWord.setTraining(s); 
        System.out.print(markovWord.getFollows("test")+"\n");
        System.out.print(markovWord.getFollows("is")+"\n");
        System.out.print(markovWord.getFollows("this")+"\n");
        System.out.print(markovWord.getFollows("simple")+"\n");
        System.out.print(markovWord.getFollows("a")+"\n");
    }
    
    public void testMW2GetFollowsSpecific() {
        String s = "this is just a test yes this is a simple test";
        MarkovWordTwo mw2 = new MarkovWordTwo(); 
        mw2.setTraining(s); 
        System.out.print(mw2.getFollows("this","is")+"\n");
        System.out.print(mw2.getFollows("just","a")+"\n");
        System.out.print(mw2.getFollows("this","frog")+"\n");
        System.out.print(mw2.getFollows("simple","test")+"\n");
        System.out.print(mw2.getFollows("a","yes")+"\n");
        
        String string = "Womble is my pet pekingese as my pet pekingese Womble is very cute but Womble is also a naughty pet pekingese";
        mw2.setTraining(string);
        System.out.print(mw2.getFollows("Womble","is")+"\n");
        System.out.print(mw2.getFollows("pet","pekingese")+"\n");
        System.out.print(mw2.getFollows("as","my")+"\n");
        System.out.print(mw2.getFollows("very","cute")+"\n");
        System.out.print(mw2.getFollows("Mungo","not")+"\n");
                
    }
    
    

}










































