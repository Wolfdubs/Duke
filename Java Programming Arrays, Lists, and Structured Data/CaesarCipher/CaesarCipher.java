
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String message, int key){
        StringBuilder sb = new StringBuilder(message);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);
        for (int i = 0; i < sb.length(); i++) {
            char currentChar = sb.charAt(i);
            int positionInAlphabet;
            if (Character.isUpperCase(currentChar)) {
                positionInAlphabet = alphabet.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabet.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            } else if (Character.isLowerCase(currentChar)){
                positionInAlphabet = alphabetLower.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabetLower.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            }  
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public void testEncrypt(){
        String encryptedString = encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
        System.out.println(encryptedString);
        System.out.println(encrypt("First Legion", 23));
        System.out.println(encrypt("First Legion", 17));
        System.out.println("QUIZ ANSWER = " + encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        /*FileResource fr = new FileResource();
        String fileString = fr.asString();
        int key = 5;
        String encryptedFileString = encrypt(fileString, key);
        System.out.println("key is "+ key + "\n" + encryptedFileString);
        String decrypted = encrypt(encryptedFileString, 26-key);
        System.out.println("key is "+ (26-key) + "\n" + decrypted);*/
        
        System.out.println("ASSESSMENT:");
        System.out.println("Question1 = " + encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?",15));
        System.out.println("Question2 = " + encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?",21,8));
              
    }
    
    public String encryptTwoKeys(String message, int key1, int key2){
        StringBuilder sb = new StringBuilder(message);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        String shiftedAlphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0,key2);
        for (int i = 0; i < sb.length(); i+=2) {
            char currentChar = sb.charAt(i);
            int positionInAlphabet;
            if (Character.isUpperCase(currentChar)) {
                positionInAlphabet = alphabet.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabet1.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            } else if (Character.isLowerCase(currentChar)){
                positionInAlphabet = alphabetLower.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabetLower1.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            }  
        }
        for (int i = 1; i < sb.length(); i+=2) {
            char currentChar = sb.charAt(i);
            int positionInAlphabet;
            if (Character.isUpperCase(currentChar)) {
                positionInAlphabet = alphabet.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabet2.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            } else if (Character.isLowerCase(currentChar)){
                positionInAlphabet = alphabetLower.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabetLower2.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            }  
        }
        
        return sb.toString();
    }
    
    public void testEncrypt2Keys(){
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
        System.out.println("QUIZ ANSWER = " + encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        System.out.println("ASSESSMENT 21,8: " + encryptTwoKeys("Duke Computer Science Department OverviewOne of the many hallmarks of our success has been fruitful collaborations among different groups within the department, with research groups in other departments at Duke, with other institutes, and with industry. As we move into the information age, the focus of science is shifting from the discovery of new information to the computationally intensive task of processing and analyzing information.We have outstanding programs in geometric computing; internet systems, networking, and security; biological computing; memory systems and massive data management; and learning and modeling. The research interests of our faculty overlap with these areas and with researcher areas in other disciplines such as biology, engineering, nanotechnology, and environmental sciences.CS Dept Photo We also do work in a number of other important areas including computer graphics and visualization, sensor networks, numerical analysis, software engineering, complexity theory, and robotics.The department is arguably unique in the symbiosis that exists between the education group and the research faculty. The synergy between them has been a key to the success in continually reforming the curriculum and integrating research and education. The department is using a dual approach to combine research and education. Bringing research into the curriculum is the best way to train students about the most advanced technology and to disseminate the latest developments of computing technology in society.CS Dept Photo We encourage undergraduate students to get involved with ongoing major research projects through the C-SURF program, undergraduate theses, Research Experience for Undergraduates (REU) support, independent studies, etc. Some of our exceptional first majors graduate with distinction, which involves a significant research component, and in many cases the research has resulted in publications in leading conferences.The eminence of our research and teaching faculty is the biggest strength of the department. Many faculty members have been recognized both at university and national levels for their excellence in research, education, and service. Highly visible, multidisciplinary projects are being conducted, sponsored by various funding agencies.The department provides an extremely stimulating, productive, and friendly environment in the form of classroom, office, and lab space; computing infrastructure; teaching support; and graduate fellowships and assistantships. It enables faculty and students to accomplish their full potential. The department is constructed to encourage innovative collaborations among the sciences, engineering, environmental studies, and medicine.",21,8)); 
        System.out.println("ASSESSMENT 8,12: " + encryptTwoKeys("Duke Computer Science Department OverviewOne of the many hallmarks of our success has been fruitful collaborations among different groups within the department, with research groups in other departments at Duke, with other institutes, and with industry. As we move into the information age, the focus of science is shifting from the discovery of new information to the computationally intensive task of processing and analyzing information.We have outstanding programs in geometric computing; internet systems, networking, and security; biological computing; memory systems and massive data management; and learning and modeling. The research interests of our faculty overlap with these areas and with researcher areas in other disciplines such as biology, engineering, nanotechnology, and environmental sciences.CS Dept Photo We also do work in a number of other important areas including computer graphics and visualization, sensor networks, numerical analysis, software engineering, complexity theory, and robotics.The department is arguably unique in the symbiosis that exists between the education group and the research faculty. The synergy between them has been a key to the success in continually reforming the curriculum and integrating research and education. The department is using a dual approach to combine research and education. Bringing research into the curriculum is the best way to train students about the most advanced technology and to disseminate the latest developments of computing technology in society.CS Dept Photo We encourage undergraduate students to get involved with ongoing major research projects through the C-SURF program, undergraduate theses, Research Experience for Undergraduates (REU) support, independent studies, etc. Some of our exceptional first majors graduate with distinction, which involves a significant research component, and in many cases the research has resulted in publications in leading conferences.The eminence of our research and teaching faculty is the biggest strength of the department. Many faculty members have been recognized both at university and national levels for their excellence in research, education, and service. Highly visible, multidisciplinary projects are being conducted, sponsored by various funding agencies.The department provides an extremely stimulating, productive, and friendly environment in the form of classroom, office, and lab space; computing infrastructure; teaching support; and graduate fellowships and assistantships. It enables faculty and students to accomplish their full potential. The department is constructed to encourage innovative collaborations among the sciences, engineering, environmental studies, and medicine.",8,21)); 
        System.out.println("ASSESSMENT 17,4: " + encryptTwoKeys("Duke Computer Science Department OverviewOne of the many hallmarks of our success has been fruitful collaborations among different groups within the department, with research groups in other departments at Duke, with other institutes, and with industry. As we move into the information age, the focus of science is shifting from the discovery of new information to the computationally intensive task of processing and analyzing information.We have outstanding programs in geometric computing; internet systems, networking, and security; biological computing; memory systems and massive data management; and learning and modeling. The research interests of our faculty overlap with these areas and with researcher areas in other disciplines such as biology, engineering, nanotechnology, and environmental sciences.CS Dept Photo We also do work in a number of other important areas including computer graphics and visualization, sensor networks, numerical analysis, software engineering, complexity theory, and robotics.The department is arguably unique in the symbiosis that exists between the education group and the research faculty. The synergy between them has been a key to the success in continually reforming the curriculum and integrating research and education. The department is using a dual approach to combine research and education. Bringing research into the curriculum is the best way to train students about the most advanced technology and to disseminate the latest developments of computing technology in society.CS Dept Photo We encourage undergraduate students to get involved with ongoing major research projects through the C-SURF program, undergraduate theses, Research Experience for Undergraduates (REU) support, independent studies, etc. Some of our exceptional first majors graduate with distinction, which involves a significant research component, and in many cases the research has resulted in publications in leading conferences.The eminence of our research and teaching faculty is the biggest strength of the department. Many faculty members have been recognized both at university and national levels for their excellence in research, education, and service. Highly visible, multidisciplinary projects are being conducted, sponsored by various funding agencies.The department provides an extremely stimulating, productive, and friendly environment in the form of classroom, office, and lab space; computing infrastructure; teaching support; and graduate fellowships and assistantships. It enables faculty and students to accomplish their full potential. The department is constructed to encourage innovative collaborations among the sciences, engineering, environmental studies, and medicine.",17,4)); 
        System.out.println("ASSESSMENT 4,17: " + encryptTwoKeys("Duke Computer Science Department OverviewOne of the many hallmarks of our success has been fruitful collaborations among different groups within the department, with research groups in other departments at Duke, with other institutes, and with industry. As we move into the information age, the focus of science is shifting from the discovery of new information to the computationally intensive task of processing and analyzing information.We have outstanding programs in geometric computing; internet systems, networking, and security; biological computing; memory systems and massive data management; and learning and modeling. The research interests of our faculty overlap with these areas and with researcher areas in other disciplines such as biology, engineering, nanotechnology, and environmental sciences.CS Dept Photo We also do work in a number of other important areas including computer graphics and visualization, sensor networks, numerical analysis, software engineering, complexity theory, and robotics.The department is arguably unique in the symbiosis that exists between the education group and the research faculty. The synergy between them has been a key to the success in continually reforming the curriculum and integrating research and education. The department is using a dual approach to combine research and education. Bringing research into the curriculum is the best way to train students about the most advanced technology and to disseminate the latest developments of computing technology in society.CS Dept Photo We encourage undergraduate students to get involved with ongoing major research projects through the C-SURF program, undergraduate theses, Research Experience for Undergraduates (REU) support, independent studies, etc. Some of our exceptional first majors graduate with distinction, which involves a significant research component, and in many cases the research has resulted in publications in leading conferences.The eminence of our research and teaching faculty is the biggest strength of the department. Many faculty members have been recognized both at university and national levels for their excellence in research, education, and service. Highly visible, multidisciplinary projects are being conducted, sponsored by various funding agencies.The department provides an extremely stimulating, productive, and friendly environment in the form of classroom, office, and lab space; computing infrastructure; teaching support; and graduate fellowships and assistantships. It enables faculty and students to accomplish their full potential. The department is constructed to encourage innovative collaborations among the sciences, engineering, environmental studies, and medicine.",4,17)); 
        System.out.println("ASSESSMENT 13 22: " + encryptTwoKeys("Duke Computer Science Department OverviewOne of the many hallmarks of our success has been fruitful collaborations among different groups within the department, with research groups in other departments at Duke, with other institutes, and with industry. As we move into the information age, the focus of science is shifting from the discovery of new information to the computationally intensive task of processing and analyzing information.We have outstanding programs in geometric computing; internet systems, networking, and security; biological computing; memory systems and massive data management; and learning and modeling. The research interests of our faculty overlap with these areas and with researcher areas in other disciplines such as biology, engineering, nanotechnology, and environmental sciences.CS Dept Photo We also do work in a number of other important areas including computer graphics and visualization, sensor networks, numerical analysis, software engineering, complexity theory, and robotics.The department is arguably unique in the symbiosis that exists between the education group and the research faculty. The synergy between them has been a key to the success in continually reforming the curriculum and integrating research and education. The department is using a dual approach to combine research and education. Bringing research into the curriculum is the best way to train students about the most advanced technology and to disseminate the latest developments of computing technology in society.CS Dept Photo We encourage undergraduate students to get involved with ongoing major research projects through the C-SURF program, undergraduate theses, Research Experience for Undergraduates (REU) support, independent studies, etc. Some of our exceptional first majors graduate with distinction, which involves a significant research component, and in many cases the research has resulted in publications in leading conferences.The eminence of our research and teaching faculty is the biggest strength of the department. Many faculty members have been recognized both at university and national levels for their excellence in research, education, and service. Highly visible, multidisciplinary projects are being conducted, sponsored by various funding agencies.The department provides an extremely stimulating, productive, and friendly environment in the form of classroom, office, and lab space; computing infrastructure; teaching support; and graduate fellowships and assistantships. It enables faculty and students to accomplish their full potential. The department is constructed to encourage innovative collaborations among the sciences, engineering, environmental studies, and medicine.",13,22)); 
        System.out.println("ASSESSMENT 22 13: " + encryptTwoKeys("Duke Computer Science Department OverviewOne of the many hallmarks of our success has been fruitful collaborations among different groups within the department, with research groups in other departments at Duke, with other institutes, and with industry. As we move into the information age, the focus of science is shifting from the discovery of new information to the computationally intensive task of processing and analyzing information.We have outstanding programs in geometric computing; internet systems, networking, and security; biological computing; memory systems and massive data management; and learning and modeling. The research interests of our faculty overlap with these areas and with researcher areas in other disciplines such as biology, engineering, nanotechnology, and environmental sciences.CS Dept Photo We also do work in a number of other important areas including computer graphics and visualization, sensor networks, numerical analysis, software engineering, complexity theory, and robotics.The department is arguably unique in the symbiosis that exists between the education group and the research faculty. The synergy between them has been a key to the success in continually reforming the curriculum and integrating research and education. The department is using a dual approach to combine research and education. Bringing research into the curriculum is the best way to train students about the most advanced technology and to disseminate the latest developments of computing technology in society.CS Dept Photo We encourage undergraduate students to get involved with ongoing major research projects through the C-SURF program, undergraduate theses, Research Experience for Undergraduates (REU) support, independent studies, etc. Some of our exceptional first majors graduate with distinction, which involves a significant research component, and in many cases the research has resulted in publications in leading conferences.The eminence of our research and teaching faculty is the biggest strength of the department. Many faculty members have been recognized both at university and national levels for their excellence in research, education, and service. Highly visible, multidisciplinary projects are being conducted, sponsored by various funding agencies.The department provides an extremely stimulating, productive, and friendly environment in the form of classroom, office, and lab space; computing infrastructure; teaching support; and graduate fellowships and assistantships. It enables faculty and students to accomplish their full potential. The department is constructed to encourage innovative collaborations among the sciences, engineering, environmental studies, and medicine.",22,13)); 
        System.out.println("assessment q.6" + encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.",14,24));
        System.out.println("assessment q.6 14 24" + encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.",14,24));
        System.out.println("assessment q.6 16 6" + encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.",16,6));
        System.out.println("assessment q.6" + encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.",14,24));
        System.out.println("assessment q.6" + encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.",14,24));
    }
    
    public String decryptByCharCount(String encrypted){
        int mostFrequentIndex = maxIndex(encrypted);
        int dKey = mostFrequentIndex - 4;  //4 is index of 'e'
        if (mostFrequentIndex < 4) dKey = 26 - (4-mostFrequentIndex);
        System.out.println("Most frequenct index = " + mostFrequentIndex);
        System.out.println("Decryption key = " + dKey);
        String decrypted = encrypt(encrypted, 26 - dKey);
        return decrypted;
    }
    
    private int maxIndex(String encrypted){
        int[] charFrequencies = Arrays.charOccurrenceInString(encrypted);
        int mostFrequent = 0;
        int mostFrequentIndex = -1;
        for (int i = 0; i < charFrequencies.length; i++) {
            if (charFrequencies[i] > mostFrequent) {
                mostFrequent = charFrequencies[i]; 
                mostFrequentIndex = i;
            }
        }
        return mostFrequentIndex;
    }
    
    public String decryptByCharCountOld(String encrypted){
        int[] charFrequencies = Arrays.charOccurrenceInString(encrypted);
        int mostFrequent = 0;
        int mostFrequentIndex = -1;
        for (int i = 0; i < charFrequencies.length; i++) {
            if (charFrequencies[i] > mostFrequent) {
                mostFrequent = charFrequencies[i]; 
                mostFrequentIndex = i;
            }
        }
        
        int dKey = mostFrequentIndex - 4;  //4 is index of 'e'
        if (mostFrequentIndex < 4) dKey = 26 - (4-mostFrequentIndex);
        System.out.println("Most frequenct index = " + mostFrequentIndex);
        String decrypted = encrypt(encrypted, 26 - dKey);
        return decrypted;
    }
    
    
    public String decryptTwoKnownKeys(String message, int key1, int key2){
        StringBuilder sb = new StringBuilder(message);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        String shiftedAlphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0,key2);
        for (int i = 0; i < sb.length(); i+=2) {
            char currentChar = sb.charAt(i);
            if (key1 < 4) key1 = 26 - key1;
//            else k
          //  char decryptedChar = 
            int positionInAlphabet;
            if (Character.isUpperCase(currentChar)) {
                positionInAlphabet = alphabet.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabet1.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            } else if (Character.isLowerCase(currentChar)){
                positionInAlphabet = alphabetLower.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabetLower1.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            }  
        }
        for (int i = 1; i < sb.length(); i+=2) {
            char currentChar = sb.charAt(i);
            int positionInAlphabet;
            if (Character.isUpperCase(currentChar)) {
                positionInAlphabet = alphabet.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabet2.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            } else if (Character.isLowerCase(currentChar)){
                positionInAlphabet = alphabetLower.indexOf(currentChar);
                if (positionInAlphabet != -1) {
                    char encryptedChar = shiftedAlphabetLower2.charAt(positionInAlphabet);
                    sb.setCharAt(i, encryptedChar);
                }
            }  
        }
        
        return sb.toString();
    }
    
}
































