
/**
 * Write a description of CSVExports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVExports {

    private String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser){
            if (record.get("Country").equals(country)) {
                return (record.get("Country") + ": " +
                record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
        return "NOT FOUND";
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
       // System.out.println(countryInfo(parser, "Nauru"));
       // listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999.999,999,999");
    }
    
    private void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem1) && 
                record.get("Exports").contains(exportItem2)){
                    System.out.println(record.get("Country"));
                }
        }
    }
    
    private int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem)) 
                counter++;
        }
        return counter;
    }
    
    private void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser) {
           if ((record.get("Value (dollars)").length() > amount.length())){
               System.out.println(record.get("Country") + " " + (record.get("Value (dollars)")));
            }
        }
    }
    
}













