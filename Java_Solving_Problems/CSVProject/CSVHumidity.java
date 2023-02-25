
/**
 * Write a description of CSVHumidity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.util.Optional;

public class CSVHumidity {
    
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
        Double averageTemp = totalTemp / counter;
        Optional<Double> averageTempOptional = Optional.ofNullable(averageTemp);
        if (!averageTempOptional.isPresent()) return Optional.of(Double.MIN_VALUE);
        else return averageTempOptional;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Optional<Double> averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (averageTemp.get() == Double.MIN_VALUE)
            System.out.println("No temperatures with that humidity");
        else System.out.println("Average Temp when high Humidity is: " + averageTemp.get());
    }

}
