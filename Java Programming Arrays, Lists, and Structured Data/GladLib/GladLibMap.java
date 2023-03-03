
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private Map<String, ArrayList<String>> map;
    private ArrayList<String> wordsUsed;
    private Set<String> categoriesUsed;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        map = new HashMap<>();
      //  wordsUsed = new ArrayList<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        map = new HashMap<>();
       // wordsUsed = new ArrayList<>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", 
            "animal", "timeframe", "verb", "fruit" };
        for (int i = 0; i < categories.length; i++){
            String categoryFilePath = source + "/" + categories[i] + ".txt";
            ArrayList<String> wordsForCategory = readIt(categoryFilePath);
            map.put(categories[i],wordsForCategory);   
        }
        wordsUsed = new ArrayList<>();
        categoriesUsed = new HashSet<>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
      //  System.out.println("label = " + label);
        if (!label.equals("number")){
            categoriesUsed.add(label);
        }
       // System.out.println("categoriesUsed = " + categoriesUsed.toString());
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(map.get(label));
        //return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub;
        do {
            sub = getSubstitute(w.substring(first+1,last));
        }
        while(wordsUsed.contains(sub));
        wordsUsed.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int totalWords = 0;
        for (ArrayList<String> words : map.values()){
            totalWords += words.size();
        }
        return totalWords;
    }
    
    private int totalWordsConsidered(){
        int total = 0;
        for (String s : categoriesUsed){
            ArrayList<String> associatedWords = map.get(s);
            System.out.println(associatedWords.toString());
            total += map.get(s).size();
        }
        return total;
    }
    
    public void makeStory(){
        wordsUsed.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal number of words replaced = " + wordsUsed.size());
        System.out.println("Total words in the source files = " + totalWordsInMap());
        System.out.println("Total words considered for categories in the template = " + totalWordsConsidered());
    }
    


}

