
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    private String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        int endIndex = dna.indexOf("TAA", startIndex+3);
        boolean validSequence = (endIndex - startIndex) % 3 == 0;                if ((startIndex == -1) || (endIndex == -1) || !validSequence) return "";
        String simpleGene = dna.substring(startIndex, endIndex+3);
        return simpleGene;
    }
    
    private void testSimpleGene(){
        String dna1 = "ATGTAA";
        System.out.println(dna1);
        String simpleGene1 = findSimpleGene(dna1);
        System.out.println(simpleGene1);  
        String dna2 = "ATGCTGGTTCGCTAA";
        System.out.println(dna2);
        String simpleGene2 = findSimpleGene(dna2);
        System.out.println(simpleGene2);
        String dna3 = "ATCCCTTCCAAGGTTCCTAA";
        System.out.println(dna3);
        String simpleGene3 = findSimpleGene(dna3);
        System.out.println(simpleGene3);
        String dna4 = "AGGGTTGCCGTCAA";
        System.out.println(dna4);
        String simpleGene4 = findSimpleGene(dna4);
        System.out.println(simpleGene4);
        String dna5 = "ATGTACGATGCGTAGCATCTATAAGCTCATAATAA";
        System.out.println(dna5);
        String simpleGene5 = findSimpleGene(dna5);
        System.out.println(simpleGene5);
        String dna6 = "ATCCCTTCCAAGGTCTAA";
        System.out.println(dna6);
        String simpleGene6 = findSimpleGene(dna6);
        System.out.println(simpleGene6);
    }
    
    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }
}










