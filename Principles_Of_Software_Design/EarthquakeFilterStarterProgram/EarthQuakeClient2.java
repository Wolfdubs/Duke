import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        /*
        Filter minMagFilter = new MinMagFilter(4.0);
        ArrayList<QuakeEntry> m7  = filter(list, minMagFilter); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("There are " + m7.size() + " such earthquakes");
        */
        
        Filter magnitudeFilter = new MagnitudeFilter(3.5,4.5);
        Filter depthFilter = new DepthFilter(-55000,-20000);
        ArrayList<QuakeEntry> magFiltered = filter(list, magnitudeFilter);
        ArrayList<QuakeEntry> depthAndMagFiltered = filter(magFiltered, depthFilter);
        for (QuakeEntry qe : depthAndMagFiltered)
            System.out.println(qe);
        System.out.println("There are " + depthAndMagFiltered.size() + " such earthquakes");
        
        /*
        Location denver = new Location(39.7392, -104.9903); 
        Filter distanceFilter = new DistanceFilter(denver, 1000*1000);
        Filter phraseFilter = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> distanceFiltered = filter(list, distanceFilter);
        ArrayList<QuakeEntry> phraseAndDistanceFiltered = filter(distanceFiltered, phraseFilter);
        for (QuakeEntry qe : phraseAndDistanceFiltered)
            System.out.println(qe);
        System.out.println("There are " + phraseAndDistanceFiltered.size() + " such earthquakes");
        */
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter magnitudeFilter = new MagnitudeFilter(1.0,4.0, "Magnitude");
        Filter depthFilter = new DepthFilter(-180000.0,-30000.0, "Depth");
        Filter phraseFilter = new PhraseFilter("any", "o", "Phrase");
        
        maf.addFilter(magnitudeFilter); maf.addFilter(depthFilter); maf.addFilter(phraseFilter);
        int counter = 0;
        for (QuakeEntry qe : list){
            if (maf.satisfies(qe)){
                System.out.println(qe);
                counter++;
            }
        }
        System.out.println(counter + " earthquakes met the filter criteria"); 
        System.out.println(maf.getName());
        
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Location billund = new Location(55.7308, 9.1153); 
        Filter distanceFilter = new DistanceFilter(billund, 3000*3000, "Distance");
        Filter magnitudeFilter = new MagnitudeFilter(0.0,5.0, "Magnitude");
        Filter phraseFilter = new PhraseFilter("any", "e", "Phrase");
        maf.addFilter(magnitudeFilter); maf.addFilter(distanceFilter); maf.addFilter(phraseFilter);
        int counter = 0;
        for (QuakeEntry qe : list){
            if (maf.satisfies(qe)){
                System.out.println(qe);
                counter++;
            }
        }
        System.out.println(counter + " earthquakes met the filter criteria");
        System.out.println(maf.getName());
    }
    
    public void quizFilters(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Filter magnitudeFilter = new MagnitudeFilter(4.0,5.0);
        Filter depthFilter = new DepthFilter(-3999.9,-1999.9);
        Filter phraseFilter = new PhraseFilter("any", "Can");
       // Filter minMagFilter = new MinMagFilter(
        //ArrayList<QuakeEntry> depthFiltered = filter(list, depthFilter);
        ArrayList<QuakeEntry> phraseFiltered = filter(list, phraseFilter);
        for (QuakeEntry qe : phraseFiltered)
            System.out.println(qe);
        System.out.println("There are " + phraseFiltered.size() + " such earthquakes");
        
        /*
        MatchAllFilter maf = new MatchAllFilter();
        Location tulsa = new Location(36.1314, -95.9372); 
        Filter distanceFilter = new DistanceFilter(tulsa, 10000*10000, "Distance");
        Filter magnitudeFilter = new MagnitudeFilter(0.0,3.0, "Magnitude");
        Filter phraseFilter = new PhraseFilter("any", "Ca", "Phrase");
        Filter depthFilter = new DepthFilter(-12000,-10000, "Depth");
        maf.addFilter(magnitudeFilter); maf.addFilter(distanceFilter); maf.addFilter(phraseFilter);
        int counter = 0;
        for (QuakeEntry qe : list){
            if (maf.satisfies(qe)){
                System.out.println(qe);
                counter++;
            }
        }
        System.out.println(counter + " earthquakes met the filter criteria");
        System.out.println(maf.getName());
        */
    }
    
    
}


















