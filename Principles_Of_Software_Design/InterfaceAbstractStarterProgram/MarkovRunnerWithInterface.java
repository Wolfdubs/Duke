
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            markov.setRandom(seed);
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 30;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
    
    public void testHashMap(){
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        int seed = 42;
        EMM emm = new EMM(2);
        runModel(emm,st,size,seed);
        emm.printHashMapInfo();
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource("data/hawthorne.txt");
	String st = fr.asString();
	st = st.replace('\n', ' ');
	int seed = 42;
	int size = 1000;
	
	double startTime = System.nanoTime();
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size, seed);
        double endTime = System.nanoTime();
        System.out.println("The running time of MarkovModel is " + (endTime-startTime)/ 1000000000.0 + " seconds");
		
        startTime = System.nanoTime();
	EMM emm = new EMM(2);
        runModel(emm, st, size, seed);
        endTime = System.nanoTime();
        System.out.println("The running time of EfficientMarkovModel is " + (endTime-startTime)/ 1000000000.0 + " seconds");
    }
    
    public void Quiz3(){
        FileResource fr = new FileResource("data/romeo.txt");
	String st = fr.asString();
	st = st.replace('\n', ' ');
	int seed = 615;
	int size = 1000;
        EMM emm = new EMM(5);
        runModel(emm,st,size,seed);
        emm.printHashMapInfo();
    }
    
}
























