
/**
 * Write a description of ConvertImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class ConvertImage {
    public static ImageResource makeGrayScaleImage(ImageResource startingImage){
        ImageResource outputImage = new ImageResource(startingImage.getWidth(), startingImage.getHeight());
        for (Pixel pixel : startingImage.pixels()) {
            Pixel startingPixel = startingImage.getPixel(pixel.getX(), pixel.getY());
            int averageColor = (startingPixel.getRed() + startingPixel.getGreen() + startingPixel.getBlue()) / 3;
            Pixel averageColorPixel = new Pixel(startingPixel);
            averageColorPixel.setRed(averageColor); averageColorPixel.setGreen(averageColor); averageColorPixel.setBlue(averageColor); 
            outputImage.setPixel(pixel.getX(), pixel.getY(), averageColorPixel);
        }
        return outputImage;
    }
    
    public void testMakeGrayScaleImage(){
        ImageResource ir = new ImageResource();
        ImageResource grayImage = makeGrayScaleImage(ir);
        saveGrayScaleImage(ir, grayImage);
        grayImage.draw();
        
        
    }
    
    public static void saveGrayScaleImage(ImageResource startingImage, ImageResource grayImage){
        String originalFileName = startingImage.getFileName();
        System.out.println(originalFileName);
        String newFileName = "gray-" + originalFileName;
        grayImage.setFileName(newFileName);
        grayImage.save();
    }

}
