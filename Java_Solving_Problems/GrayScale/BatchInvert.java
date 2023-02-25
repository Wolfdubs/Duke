/**
 * Write a description of BatchInvert here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class BatchInvert {
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()){
            ImageResource image = new ImageResource(file);
            ImageResource invertedImage = ImageInversion.makeInversion(image);
            ImageInversion.saveInvertedImage(image, invertedImage);
        }
    }

}
