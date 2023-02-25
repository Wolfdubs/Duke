
/**
 * Write a description of Births here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class Births {

    public void totalBirths(){
        FileResource fr = new FileResource();
        int totalBirths = 0, totalNames = 0, girlBirths = 0, 
        girlNames = 0, boyBirths = 0, boyNames = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            totalBirths += Integer.parseInt(record.get(2));
            totalNames++;
            if (record.get(1).equals("F")) {
                girlBirths += Integer.parseInt(record.get(2));
                girlNames++;
            } else if (record.get(1).equals("M")){
                boyBirths += Integer.parseInt(record.get(2));
                boyNames++;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Girl births = " + girlBirths);
        System.out.println("Boy births = " + boyBirths);
        System.out.println("Total names = " + totalNames);
        System.out.println("Girl names = " + girlNames);
        System.out.println("Boy names = " + boyNames);
    }
    
    private int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        int rank = 0;
        boolean namePresent = false;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank++;
                if (record.get(0).equals(name)) {
                    namePresent = true;
                    break;
                }
                
            } 
        }
        if (!namePresent) return -1;
        return rank;
    }
    
    public void testGetRank(){
        int rank1 = getRank(2012,"Mason","M");
        System.out.println("Rank Mason = " + rank1);  //2
        int rank2 = getRank(2012,"William","M");
        System.out.println("Rank William= " + rank2); //5
        int rank3 = getRank(2012,"Ava","F");
        System.out.println("Rank Ava = " + rank3);  //5
        int rank4 = getRank(2012,"Sophia","F");
        System.out.println("Rank Sophia= " + rank4);  //1
        int rank5 = getRank(2012,"Jacob","M");
        System.out.println("Rank Jacob = " + rank5);  //1
        int rank6 = getRank(2012,"Harry","M");
        System.out.println("Rank Harry = " + rank6);  //1
        int rankQuiz = getRank(1960,"Emily","F");
        System.out.println("Rank Emily for Quiz = " + rankQuiz);  //1
        int rankQuizFrank = getRank(1971,"Frank","M");
        System.out.println("Rank Frank for Quiz = " + rankQuizFrank);  //1
    }
    
    private String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        int counter = 0;
        String name = "NO NAME";
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)){
                counter++;
                if (counter == rank) {
                    name = record.get(0);
                }
            }
        }
        return name;
    }
    
    public void testGetName(){
        String name1 = getName(2014, 4, "F");
        System.out.println("Name1 = " + name1);
        String name2 = getName(2014, 5, "M");
        System.out.println("Name2 = " + name2);
        String name3 = getName(2014, 1, "F");
        System.out.println("Name3 = " + name3);
        String nameQuiz350 = getName(1980, 350, "F");
        System.out.println("Name350 for 1980 = " + nameQuiz350);
        String nameQuiz450 = getName(1982, 450, "M");
        System.out.println("Name450 = " + nameQuiz450);
    }
    
    private void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        FileResource fr = new FileResource("us_babynames_by_year/yob"+newYear+".csv");
        int counter = 0;
        String newName = "";
        for (CSVRecord record : fr.getCSVParser(false)){
            if (record.get(1).equals(gender)){
                counter++;
                if (counter == rank) {
                    newName = record.get(0);
                }
            }
        }
        System.out.println("The version of the old name " + name + " in year " + year + " is " + newName + " in year " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella", 2012, 2014, "F");  //Sophia
        whatIsNameInYear("Jacob", 2012, 2014, "M");  //Noah
        whatIsNameInYear("Susan", 1972, 2014, "F");  
        whatIsNameInYear("Owen", 1974, 2014, "M");  
    }
    
    private int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestOccurrence = 0;
        int highestRankingYear = -1;
        for (File file : dr.selectedFiles()){
            System.out.println("highestOccurrence = " + highestOccurrence);
            System.out.println("File = " + file);
            System.out.println("File string = " + file.toString());
            System.out.println("File name = " + file.getName());
            System.out.println("File index1 = " + file.getName().indexOf("yob"));
            System.out.println("File index2 = " + file.getName().indexOf("yob")+3);
            System.out.println("File index3 = " + file.getName().indexOf("yob")+3);
            String fileYear = file.getName().substring(file.getName().indexOf("yob")+3,file.getName().indexOf("yob")+7);
            System.out.println("file year = " + fileYear);
            int rank = getRank(Integer.parseInt(fileYear), name, gender);
            System.out.println("rank = " + rank);
            if (highestOccurrence == 0) {
                highestOccurrence = rank;
                highestRankingYear = Integer.parseInt(fileYear);
            }
            else if (rank < highestOccurrence) {
                highestOccurrence = rank;
                highestRankingYear = Integer.parseInt(fileYear);
                System.out.println("highestOccurrence = " + highestOccurrence);
                System.out.println("highestRankingYear = " + highestRankingYear);
            }
        }
        return highestRankingYear;
    }
    
    public void testYearOfHighestRank(){
        int highestRankAcrossYears = yearOfHighestRank("Genevieve", "F");
        System.out.println("The year with the highest rank for Genevieve was " + highestRankAcrossYears);
        int highestRankAcrossYearsMich = yearOfHighestRank("Mich", "M");
        System.out.println("The year with the highest rank for Mich was " + highestRankAcrossYears);
    }
    
    private double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rankAggregator = 0;
        int occurrenceCounter = 0;
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            int fileYear = Integer.parseInt(file.getName().substring(file.getName().indexOf("yob")+3,file.getName().indexOf("yob")+7));
            int rank = getRank(fileYear, name, gender);
            if (rank==-1)
                continue;
            occurrenceCounter++;
            rankAggregator += rank;
        }
        if (occurrenceCounter == 0) return -1;
        return (double) rankAggregator / occurrenceCounter;
    }
    
    public void testGetAverageRank(){
        double averageRank1 = getAverageRank("Mason", "M");
        System.out.println("getAverageRank for Mason = " + averageRank1);
        double averageRank2 = getAverageRank("Jacob", "M");
        System.out.println("getAverageRank for Jacob = " + averageRank2);
        double averageRank3 = getAverageRank("Emma", "F");
        System.out.println("getAverageRank for Emma = " + averageRank3);
        double averageRank = getAverageRank("Harry", "M");
        System.out.println("getAverageRank for Harry = " + averageRank);
        double averageRankSusanQuiz = getAverageRank("Susan", "F");
        System.out.println("getAverageRank for Susan Quiz = " + averageRankSusanQuiz);
        double averageRankRobertQuiz = getAverageRank("Robert", "M");
        System.out.println("getAverageRank for Robert Quiz = " + averageRankRobertQuiz);
        
    }
    
    private int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        int totalBirthsRankedHigher = 0;
        boolean isNamePresent = false;
        for (CSVRecord record : fr.getCSVParser(false)){
            if (!record.get(0).equals(name) && record.get(1).equals(gender)){
                totalBirthsRankedHigher += Integer.parseInt(record.get(2));
            } else if (record.get(0).equals(name)) {
                isNamePresent = true;
                break;
            }
        }
        if (!isNamePresent) return -1;
        return totalBirthsRankedHigher;
    }
    
    public void testGetTotalBirthsRankedHigher(){
            System.out.println("Total births higher than Ethan = " + getTotalBirthsRankedHigher(2012,"Ethan","M"));
            System.out.println("Total births higher than Sophia = " + getTotalBirthsRankedHigher(2012,"Sophia","F"));
            System.out.println("Total births higher than Ava = " + getTotalBirthsRankedHigher(2012,"Ava","F"));
            System.out.println("Total births higher than Harry = " + getTotalBirthsRankedHigher(2012,"Harry","M"));
            System.out.println("Total births higher than Emily Quiz = " + getTotalBirthsRankedHigher(1990,"Emily","F"));
            System.out.println("Total births higher than Drew Quiz = " + getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
    
    
}
































