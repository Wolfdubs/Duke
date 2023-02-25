
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class ImageInversion {
    
    public static ImageResource makeInversion(ImageResource startingImage){
        ImageResource invertedImage = new ImageResource(startingImage.getWidth(), startingImage.getHeight());
        for (Pixel pixel : startingImage.pixels()){
            Pixel startingPixel = startingImage.getPixel(pixel.getX(), pixel.getY());
            int originalRed = startingPixel.getRed(); int originalGreen = startingPixel.getGreen(); int originalBlue = startingPixel.getBlue();
            int newRed = (255 - originalRed); int newGreen = (255 - originalGreen); int newBlue = (255 - originalBlue);
            Pixel invertedPixel = new Pixel(startingPixel);
            invertedPixel.setRed(newRed); invertedPixel.setGreen(newGreen); invertedPixel.setBlue(newBlue);
            invertedImage.setPixel(pixel.getX(), pixel.getY(), invertedPixel);
        }
        return invertedImage;
    }
    
    public void testMakeInversion(){
        ImageResource ir = new ImageResource();
        ImageResource invertedImage = makeInversion(ir);
        invertedImage.draw();
        saveInvertedImage(ir, invertedImage);
    }
    
    public static void saveInvertedImage(ImageResource startingImage, ImageResource invertedImage){
        String originalFileName = startingImage.getFileName();
        System.out.println(originalFileName);
        String newFileName = "inverted-" + originalFileName;
        invertedImage.setFileName(newFileName);
        invertedImage.save();
    }

}
