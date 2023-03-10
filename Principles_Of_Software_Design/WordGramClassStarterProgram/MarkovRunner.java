
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

    public void runMarkov() { 
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord mw = new MarkovWord(5);
        runModel(mw, st, 120, 844);
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    
    
    public void testMWGetFollowsSpecific() {
        String s = "this is just a test yes this is a simple test";
        MarkovWord mw2 = new MarkovWord(2); 
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        int size = 4;
        WordGram wg = new WordGram(words,0,size);

        mw2.setTraining(source); 
        System.out.print(mw2.getFollows(wg)+"\n");

        
        String string = "Womble is my pet pekingese as my pet pekingese Womble is very cute but Womble is also a naughty pet pekingese";
        mw2.setTraining(string);
    
    }
    
    
    public void testHashMap(){
        FileResource fr = new FileResource("data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        runModel(emw, st, 50, 65);
    }
    
    public void testHashMap2(){
        String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        runModel(emw, st, 50, 42);
    }
    
    public void compareMethods(){
        
        FileResource fr = new FileResource("data/hawthorne.txt");
        String st = fr.asString();
        st.replace("\n"," ");
        double preMW = System.nanoTime();
        MarkovWord mw = new MarkovWord(2);
        runModel(mw, st, 100, 42);
        double postMW = System.nanoTime();
        System.out.println("Markov Word took this many seconds: " + (postMW - preMW)/1000000000);
        
        double preEMW = System.nanoTime();
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        runModel(emw, st, 100, 42);
        long postEMW = System.nanoTime();
        System.out.println("Markov Word took this many seconds: " + (postEMW - preEMW)/1000000000);
    }
    

}





























