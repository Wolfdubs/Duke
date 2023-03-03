import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> inputCopy = new ArrayList<>(quakeData);
        for (QuakeEntry qe : inputCopy){
            if (qe.getMagnitude() > magMin)
                answer.add(qe);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> inputCopy = new ArrayList<>(quakeData);
        for (QuakeEntry qe : inputCopy){
            if (qe.getLocation().distanceTo(from) < distMax)
                answer.add(qe);
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";  //data/nov20quakedata.atom
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filteredQuakes = filterByMagnitude(list, 5);
        for (QuakeEntry qe : filteredQuakes){
            System.out.println(qe);
        }
        System.out.printf("Found %d quakes that match that criteria", filteredQuakes.size());

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String sourceUrl = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        //This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        //This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> filteredQuakes = filterByDistanceFrom(list, 1000*1000, city);
       // System.out.println("close to me size = " + filteredQuakes.size());
        for (QuakeEntry qe : filteredQuakes){
           // System.out.println("hello");
            float distance = qe.getLocation().distanceTo(city);
            System.out.println(distance + " " + qe.getInfo());
        }
        System.out.printf("Found %d quakes that match that criteria", filteredQuakes.size());
        /*
        for (QuakeEntry qe : list){
            float distance = qe.getLocation().distanceTo(city);
            if (distance < 1000){
                System.out.println(distance + qe.getInfo());
            }
        }
        */
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> inputCopy = new ArrayList<>(quakeData);
        for (QuakeEntry qe : inputCopy){
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth)
                answer.add(qe);
        }
        return answer;
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakes = parser.read(source);
        System.out.println("read data for " + quakes.size() + " quakes");
        int minDepth = -4000, maxDepth = -2000;
        System.out.printf("Find quakes with depth between %d and %d\n",minDepth,maxDepth);
        ArrayList<QuakeEntry> filteredQuakes = filterByDepth(quakes, minDepth, maxDepth);
        for (QuakeEntry qe : filteredQuakes)
            System.out.println(qe);
        System.out.printf("Found %d quakes that match that criteria", filteredQuakes.size());
    }
    
    public ArrayList<QuakeEntry> filterByTitle(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> inputCopy = new ArrayList<>(quakeData);
        for (QuakeEntry qe : inputCopy){
            String info = qe.getInfo();
           // System.out.println(info.substring(6,phrase.length()+6));
           // System.out.println(info.substring(0,phrase.length()-1));
            if (where.equals("start") && info.startsWith(phrase))  //info.substring(0,phrase.length()-1).equals(phrase))
                answer.add(qe);
            else if (where.equals("end") && info.endsWith(phrase))
                answer.add(qe);
            else if (where.equals("any") && info.contains(phrase))
                answer.add(qe);
        }
        return answer;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakes = parser.read(source);
        System.out.println("read data for " + quakes.size() + " quakes");
        //ArrayList<QuakeEntry> filteredQuakes = filterByTitle(quakes, "end", "California");
        ArrayList<QuakeEntry> filteredQuakes = filterByTitle(quakes, "any", "Creek");
        //ArrayList<QuakeEntry> filteredQuakes= filterByTitle(quakes, "start", "Explosion");
        for (QuakeEntry qe : filteredQuakes)
            System.out.println(qe);
        System.out.printf("Found %d quakes that match that criteria\n", filteredQuakes.size());
    }
    
}




























