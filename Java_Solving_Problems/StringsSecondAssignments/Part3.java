
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    private int findStopCodon(String dna, int startIndex, String stopCodon){
        int currentIndex = dna.indexOf(stopCodon, startIndex+3);
        while (currentIndex != -1) {
            if ((currentIndex - startIndex) % 3 ==0) return currentIndex;
                currentIndex = dna.indexOf(stopCodon, currentIndex+1);
        }
        return -1;
    }
        

    
    public void testFindStopCodon(){
        //             v  v  v 8v  v  v  v  v  v  v  v  v  v  v
        String dna1 = "ATGTGGTAGTGCATAAAATGAATGTCACTCTAATCCTACTGA";
        int stopCodonIndex1 = findStopCodon(dna1, 0, "TAA");
        System.out.println("stopCodonIndex1 = " + stopCodonIndex1);
        int stopCodonIndex2 = findStopCodon(dna1, 0, "TAG");
        System.out.println("stopCodonIndex2 = " + stopCodonIndex2);
        int stopCodonIndex3 = findStopCodon(dna1, 0, "TGA");
        System.out.println("stopCodonIndex3 = " + stopCodonIndex3);
        int stopCodonIndex4 = findStopCodon(dna1, 8, "TGA");
        System.out.println("stopCodonIndex4 = " + stopCodonIndex4);
        String dna2 = "";
        int stopCodonIndex5 = findStopCodon(dna2, 0, "TAA");
        System.out.println("stopCodonIndex5 = " + stopCodonIndex5);
    }
    
    private String findGene(String dna, int startingIndex){
        int indexOfATG = dna.indexOf("ATG", startingIndex);
        if (indexOfATG == -1) return "";
        int indexTAA = findStopCodon(dna, indexOfATG, "TAA");
        int indexTAG = findStopCodon(dna, indexOfATG, "TAG");                       
        int indexTGA = findStopCodon(dna, indexOfATG, "TGA");
        int minIndex;
        if (indexTAA == -1 || (indexTAG != -1 && indexTAG < indexTAA)){
            minIndex = indexTAG;
        } else {
            minIndex = indexTAA;
        }
        if (minIndex == -1 || (indexTGA != -1 && indexTGA < minIndex)){
            minIndex = indexTGA;
        } 
        if (minIndex == -1) return "";
        return dna.substring(indexOfATG, minIndex+3);
    }
    
    public void testFindGene() {
        String dna1 = "ATGCCCGGGAAATAA";
        String gene1 = findGene(dna1,0);
        System.out.println("dna1 = " + dna1);
        System.out.println("gene1 = " + gene1);
        String dna2 = "ATGCCCGGGAAAA";
        String gene2 = findGene(dna2,0);
        System.out.println("dna2 = " + dna2);
        System.out.println("gene2 = " + gene2);
        String dna3 = "ATGCCCGGGAAATAGGACTAAAGGTGA";
        String gene3 = findGene(dna3,0);
        System.out.println("dna3 = " + dna3);
        System.out.println("gene3 = " + gene3);
        String dna4 = "CCCGGGAAATGA";
        String gene4 = findGene(dna4,0);
        System.out.println("dna4 = " + dna4);
        System.out.println("gene4 = " + gene4);
        String dna5 = "";
        String gene5 = findGene(dna5,0);
        System.out.println("dna5 = " + dna5);
        System.out.println("gene5 = " + gene5);
    }
    
    private void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) break;
            System.out.println(currentGene);
            startIndex = startIndex + currentGene.length();
            //startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public void testPrint(){
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        printAllGenes(dna);
    }
    
    private int countGenes(String dna){
        int startIndex = 0;
        int counter = 0;
        if (dna.isEmpty()) return 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) break;
            counter++;
            startIndex = startIndex + currentGene.length();
        }
        return counter;
    }
    
    public void testCountGenes(){
        String stringB = "ATGAACGAATTGAATC";  //0
        int countGenes1 = countGenes(stringB);
        System.out.println("countGenes1 = " + countGenes1);
        String stringD = "ATAAAA";            //2
        int countGenes2 = countGenes(stringD);
        System.out.println("countGenes2 = " + countGenes2);
        String stringF = "ATGATCTAATTTATGCTGCAACGGTGAAGA";   //6
        int countGenes3 = countGenes(stringF);
        System.out.println("countGenes3 = " + countGenes3);
        String stringG = "";
        int countGenes4 = countGenes(stringG);
        System.out.println("countGenes4 = " + countGenes4); 
        String stringJ = "ATGCCCTAAGATGAAATAGGATGACTAAAGGTTGA";           //0
        int countGenes5 = countGenes(stringJ);
        System.out.println("countGenes5 = " + countGenes5);
        String stringK = "CCCGGGAAATGA";
        int countGenes6 = countGenes(stringK);
        System.out.println("countGenes6 = " + countGenes6);    
    }

}
