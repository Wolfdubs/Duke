
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double minDepth, maxDepth;
    private String name;
    
    public DepthFilter(double minDepth, double maxDepth){
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
    }
    
    public DepthFilter(double minDepth, double maxDepth, String name){
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        this.name = name;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return (qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth);
    }
    
    public String getName(){
        return this.name;
    }
}
