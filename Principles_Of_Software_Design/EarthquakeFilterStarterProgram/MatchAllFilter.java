
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filterList;
    
    public MatchAllFilter(){
        filterList = new ArrayList<>();
    }
    
    public void addFilter(Filter f){
        filterList.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for (Filter filter : filterList){
            if (!filter.satisfies(qe)) return false;
        }
        return true;
    }
    
    public String getName(){
        StringBuilder allFilterNames = new StringBuilder();
        for (Filter filter : filterList){
            allFilterNames.append(filter.getName() + " ");
        }
        return "Filters used are " + allFilterNames.toString();
    }
    

}
