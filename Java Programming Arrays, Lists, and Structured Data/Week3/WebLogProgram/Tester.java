
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testCountUniqueIPs(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("The file has this many unqiue IPs = " + uniqueIPs);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ips1 = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(ips1);
        System.out.println(ips1.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int ips2 = la.countUniqueIPsInRange(200,399);
        System.out.println(ips2);
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int mostFrequentVisitCount = la.mostNumberVisitsByIP(counts);
        System.out.println(mostFrequentVisitCount);
    }
    
    public void testIpsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> mostFrequentVisitors = la.ipsMostVisits(counts);
        System.out.println(mostFrequentVisitors);
    }
    
    public void testIpsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> daysToIpAddresses = la.ipsForDays();
        System.out.println(daysToIpAddresses);
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> daysToIpAddresses = la.ipsForDays();
        String dayWithMostIPAddressVisits = la.dayWithMostIPVisits(daysToIpAddresses);
        System.out.println(dayWithMostIPAddressVisits);
    }
    
    public void testIpsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> daysToIpAddresses = la.ipsForDays();
        ArrayList<String> ipAddressesWithMostVisitsOnDay = la.ipsWithMostVisitsOnDay(daysToIpAddresses, "Sep 30");
        System.out.println(ipAddressesWithMostVisitsOnDay);
    }
    
    
    
}

























