
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.util.Map.Entry;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
     
     public void readFile() {
         FileResource fr = new FileResource();
         for (String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry le : records){
             String ipAddress = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddress)){
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPVisitsOnDay = new ArrayList<>();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             Date date = le.getAccessTime();
             String dateString = date.toString();
             String dateSubstring = dateString.substring(4,10);
             if (dateSubstring.equals(someday) && !uniqueIPVisitsOnDay.contains(ipAddress)){
                 uniqueIPVisitsOnDay.add(ipAddress);
             }
         }
         return uniqueIPVisitsOnDay;
     }
     
     protected int countUniqueIPsInRange(int low, int high){
         ArrayList<String> ips = new ArrayList<>();
         for (LogEntry le :  records){
             int statusCode = le.getStatusCode();
             String ip = le.getIpAddress();
             if (statusCode >= low && statusCode <= high && !ips.contains(ip)){
                 ips.add(ip);
             }
         }
         return ips.size();
     }
     
     protected void printAllHigherThanNum(int num){
         for (LogEntry le :  records){
             int statusCode = le.getStatusCode();
             if (statusCode > num){
                 System.out.println(le);
             }
         }    
     }
     
     // returns a HashMap<String, Integer> that maps an IP address to the number of times that 
     //IP address appears in records, meaning the number of times this IP address visited the 
     //website
     protected HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String, Integer> map = new HashMap<>();
         for (LogEntry le : records){
             String ip = le.getIpAddress();
             if (!map.containsKey(ip)){
                 map.put(ip, 1);
             } else {
                 map.put(ip, map.get(ip)+1);
             }
         }
         return map;
     }
     
     //returns the maximum number of visits to this website by a single IP address
     protected int mostNumberVisitsByIP(HashMap<String,Integer> map){
         int maxVisitsByIP = 0;
         for (String ip : map.keySet()){
             int count = map.get(ip);
             if (count > maxVisitsByIP){
                 maxVisitsByIP = count;
             }
         }
         return maxVisitsByIP;
     }
     
     //returns an ArrayList of Strings of IP addresses that all have the maximum number of 
     //visits to this website
     protected ArrayList<String> ipsMostVisits(HashMap<String,Integer> map){
         ArrayList<String> mostFrequentIPs = new ArrayList<>();
         int maxVisitsByIP = mostNumberVisitsByIP(map);
         for (String ip : map.keySet()){
             if (map.get(ip) == maxVisitsByIP){
                 mostFrequentIPs.add(ip);
             }
         }
         return mostFrequentIPs;
     }
     
     //returns a HashMap<String, ArrayList<String>> that uses records and maps days from web 
     //logs to an ArrayList of IP addresses that occurred on that day (including repeated IP 
     //addresses)
     protected HashMap<String,ArrayList<String>> ipsForDays(){
         HashMap<String,ArrayList<String>> ipsVisitingOnDays = new HashMap<>();
         for (LogEntry le : records){
             ArrayList<String> ipsOnDate = new ArrayList<>();
             String dateSubString = le.getAccessTime().toString().substring(4,10);
             for (LogEntry le2 : records){
                 if (le2.getAccessTime().toString().substring(4,10).equals(dateSubString)){
                    ipsOnDate.add(le.getIpAddress());
                    }
             }
             ipsVisitingOnDays.put(dateSubString,ipsOnDate);
         }
         return ipsVisitingOnDays;
     }
     
     //returns the day that has the most IP address visits. If there is a tie, then return any 
     //such day
     protected String dayWithMostIPVisits(HashMap<String,ArrayList<String>> daysToIPAddresses ){
         String date = null;
         int mostIPsOnADay = 0;
         for (Entry<String, ArrayList<String>> entry : daysToIPAddresses.entrySet()){
             if(entry.getValue().size() > mostIPsOnADay){
                 mostIPsOnADay = entry.getValue().size();
                 date = entry.getKey();
             }
         }
         return date;
     }
     
     //returns an ArrayList<String> of IP addresses that visited on specific day
     protected ArrayList<String> ipsVisitingOnSpecificDay(HashMap<String,ArrayList<String>> map, String day){
         ArrayList<String> ipsWithMostVisitsOnDay = new ArrayList<>();
         for (String mapDay : map.keySet()){
             if (mapDay.equals(day)) {
                 ipsWithMostVisitsOnDay.addAll(map.get(mapDay));
             }
         }
         return ipsWithMostVisitsOnDay;
     }
     
     //returns an ArrayList<String> of IP addresses that had the most accesses on the given day.
     protected ArrayList<String> ipsWithMostVisitsOnDayOld(HashMap<String,ArrayList<String>> map, String day){
        ArrayList<String> ipAddressesForDay = map.get(day);
        HashMap<String,Integer> countForIP = new HashMap<>();
        for (String ip : ipAddressesForDay){
            if (!countForIP.containsKey(ip)) countForIP.put(ip,1);
            else  countForIP.put(ip, countForIP.get(ip)+1);
        }
        return ipsMostVisits(countForIP);
     }
     
     //returns an ArrayList<String> of IP addresses that had the most accesses on the given day.
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> allIpsOnDay, String someday)
    {
        ArrayList<String>        ipsMostVisitOnDay = new ArrayList<>();
        ArrayList<String>        mostVisited       = new ArrayList<>();
        HashMap<String, Integer> visits            = new HashMap<>();

        for(String key: allIpsOnDay.keySet())
        {
            if(key.equals(someday))
            {
                for(int i = 0; i < allIpsOnDay.get(key).size(); i++)
                {
                    String ip = allIpsOnDay.get(key).get(i);

                    if(!ipsMostVisitOnDay.contains(ip))
                    {
                        ipsMostVisitOnDay.add(ip);
                        visits.put(ip, 1);
                    }
                    else
                    {
                        visits.put(ip, visits.get(ip) + 1);
                    }
                }
            }
        }

        mostVisited = ipsMostVisits(visits);

        return mostVisited;
    }
    
    public ArrayList<String> ipsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dayandIPs, String aDay){

        ArrayList<String> mostIPs = new ArrayList<String>();
        mostIPs = uniqueIPVisitsOnDay(aDay);
        HashMap<String,Integer> counts = new HashMap<String,Integer>();      
        
        for (int k=0;k<mostIPs.size();k++) {
          String s = mostIPs.get(k) ; 
          if (!counts.containsKey(s)) {
                 counts.put(s,1);
             }
             else {
                 int freq = counts.get(s);
                 counts.put(s,freq+1);
             }
       }
       return ipsMostVisits(counts);
    }
    
    
     
   
     
}








































