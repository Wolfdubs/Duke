
/**
 * Write a description of CSVWeather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.util.Optional;

public class CSVWeather {
    private CSVRecord coldestHourInFile(CSVParser parser){
     //   System.out.println("coldestHourInFile started");
        CSVRecord coldest = null;
        for (CSVRecord record : parser){
      //      System.out.println("running for loop of coldestHourInFile");
           // if (record.get("TemperatureF").equals("-9999")) continue;
            coldest = getColdestOfTwoRecords(record, coldest);
        }
    //    System.out.println("coldestRecord: " + coldest.toString());
        return coldest;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was = " + coldestHour.get("TemperatureF") + " at " + coldestHour.get("DateUTC"));
    }
    
    
    public void testColdestHourInFile(File f){
     //   System.out.println("testColdestHourInFile Started with file as string: " + f);
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
    //    System.out.println("testColdestHourInFile about to hit coldestHourInFile call");
        CSVRecord coldestHour = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was = " + coldestHour.get("TemperatureF") + " at " + coldestHour.get("TimeEST"));
    }
    /*
    private String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldestRecordInCurrentFile = coldestHourInFile(parser);
            if (coldestRecordInCurrentFile.get("TemperatureF").equals("-9999")) continue;
            if (coldestOfAllFiles == null) coldestOfAllFiles = coldestRecordInCurrentFile;
            if (coldestFile == null) coldestFile = f;
            else {
                if (Double.parseDouble(coldestRecordInCurrentFile.get("TemperatureF")) < Double.parseDouble(coldestOfAllFiles.get("TemperatureF")))
                    coldestOfAllFiles = coldestRecordInCurrentFile;
            }
        }
        System.out.println("Coldest day was in file " + coldestOfAllFiles.toString());
        System.out.println("Coldest temperature on that day was" + coldestOfAllFiles.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: " );
   
        return coldestFile.getName(); 
    }*/
    
   
    private CSVRecord coldestRecordAcrossDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestCSVRecordOverall = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord coldestRecordInCurrentFile = coldestHourInFile(fr.getCSVParser());
            coldestCSVRecordOverall = getColdestOfTwoRecords(coldestRecordInCurrentFile, coldestCSVRecordOverall);
        }
        return coldestCSVRecordOverall; 
    }
    
    public void testColdestRecordAcrossDays(){
        CSVRecord coldestRecord = coldestRecordAcrossDays();
        System.out.println("Coldest temperature was " + coldestRecord.get("TemperatureF") + " at " + coldestRecord.get("DateUTC"));

    }
    
    
    private CSVRecord getColdestOfTwoRecords(CSVRecord current, CSVRecord coldestSoFar){
        if (current.get("TemperatureF").equals("-9999")) return null;
        if (coldestSoFar == null) coldestSoFar = current;
            else {
                if (Double.parseDouble(coldestSoFar.get("TemperatureF")) > (Double.parseDouble(current.get("TemperatureF"))))
                    coldestSoFar = current;
            }
        return coldestSoFar;
    }
    
    private File fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        CSVRecord coldestCSVRecordOverall = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord coldestRecordInCurrentFile = coldestHourInFile(fr.getCSVParser());
            coldestCSVRecordOverall = getColdestOfTwoRecords(coldestRecordInCurrentFile, coldestCSVRecordOverall);
            if (coldestCSVRecordOverall.equals(coldestRecordInCurrentFile)) coldestFile = f;
        }
        return coldestFile;         
    }
    
    private void printAllTemperaturesOfFile(File f){
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }
    
    public void testFileWithColdestTemperature(){
        File coldestFile = fileWithColdestTemperature();
        String coldestFileName = coldestFile.getName();
        System.out.println("Coldest day was in file: " + coldestFileName);
        testColdestHourInFile(coldestFile);
        System.out.println("All the temperatures on the coldest day were");
        printAllTemperaturesOfFile(coldestFile);
    }
    
    private CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        for (CSVRecord record : parser) {
            lowestHumidity = getLowestHumidityOf2Records(record, lowestHumidity);
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was: " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }
    
    private CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityOverall = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord lowestHumidityInCurrentFile = lowestHumidityInFile(fr.getCSVParser());
            lowestHumidityOverall = getLowestHumidityOf2Records(lowestHumidityInCurrentFile, lowestHumidityOverall);
        }
        return lowestHumidityOverall;
    }
    
    
    private CSVRecord getLowestHumidityOf2Records(CSVRecord currentRecord, CSVRecord lowestRecord){
            if (currentRecord.get("Humidity").equals("N/A")) return null;
            if (lowestRecord == null) lowestRecord = currentRecord;
            else if (Integer.parseInt(currentRecord.get("Humidity")) < Integer.parseInt(lowestRecord.get("Humidity")))
                lowestRecord = currentRecord;
            return lowestRecord;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidityInManyFiles = lowestHumidityInManyFiles();
        if (lowestHumidityInManyFiles == null) System.out.println("its null");
        System.out.println("Lowest humidity was " + lowestHumidityInManyFiles.get("Humidity") + " at " + lowestHumidityInManyFiles.get("DateUTC"));
    }
    
    private double averageTemperatureInFile(CSVParser parser){
        Double totalTemp = null;
        Double averageTemp = null;
        int counter = 0;
        for (CSVRecord record : parser){
            if (record.get("TemperatureF").equals("-9999")) continue;
            if (totalTemp == null) {
                totalTemp = Double.parseDouble(record.get("TemperatureF"));
                counter=1;
            } else {
                totalTemp += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }
        }
        averageTemp = totalTemp / counter;
        return averageTemp;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file: " + averageTemp);
    }
    
    private Optional<Double> averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        Double totalTemp = null;
        int counter = 0;
        for (CSVRecord record : parser){
            if (record.get("TemperatureF").equals("-9999")) continue;
            else if (Integer.parseInt(record.get("Humidity")) > value){
                if (totalTemp == null) {
                totalTemp = Double.parseDouble(record.get("TemperatureF"));
                counter=1;
                } else {
                totalTemp += Double.parseDouble(record.get("TemperatureF"));
                counter++;
                }
            }
        }
        Optional<Double> averageTemp = Optional.ofNullable(totalTemp / counter);
        return averageTemp;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Optional<Double> averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (!averageTemp.isPresent())
            System.out.println("No temperatures with that humidity");
        else System.out.println("Average Temp when high Humidity is: " + averageTemp.get());
    }
    
    
   
}





























