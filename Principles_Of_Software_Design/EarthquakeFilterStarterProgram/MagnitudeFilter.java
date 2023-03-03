
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {

    private double minMag, maxMag;
    private String name;
    
    public MagnitudeFilter(double minMag, double maxMag){
        this.minMag = minMag;
        this.maxMag = maxMag;
    }
    
    public MagnitudeFilter(double minMag, double maxMag, String name){
        this.minMag = minMag;
        this.maxMag = maxMag;
        this.name = name;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag);
    }
    
    public String getName(){
        return this.name;
    }
}
