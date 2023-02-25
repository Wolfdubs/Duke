
/**
 * Write a description of BatchProcess here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class BatchProcess {
    public void batchProcessGrayScale(){
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()){
            ImageResource image = new ImageResource(file);
            ImageResource grayImage = ConvertImage.makeGrayScaleImage(image);
            ConvertImage.saveGrayScaleImage(image, grayImage);
        }
    }

}
