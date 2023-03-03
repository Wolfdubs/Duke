
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    
    private Location location;
    private int maxDistance;
    private String name;
    
    public DistanceFilter(Location location, int maxDistance){
        this.location = location;
        this.maxDistance= maxDistance;
    }
    
    public DistanceFilter(Location location, int maxDistance, String name){
        this.location = location;
        this.maxDistance= maxDistance;
        this.name = name;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(location) < maxDistance;    
    }
    
    public String getName(){
        return this.name;
    }

}
