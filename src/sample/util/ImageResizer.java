package sample.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * This program demonstrates how to resize an image.
 *
 * @author www.codejava.net
 *
 */
public class ImageResizer {

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */


    public static void resize(String inputImagePath, int scaledWidth, int scaledHeight) throws IOException {
        // reads input image
        //ClassLoader classLoader = new ImageResizer().getClass().getClassLoader();
        //File file = new File(classLoader.getResource(inputImagePath).getFile());
        File file = new File(inputImagePath);

        //File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(file);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        //String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

        // writes to output file
        String fileName1 = Paths.get("").toAbsolutePath().toString()+"resized.jpg";
        File inputFile = new File(fileName1);
        //File inputFile = new File("src/main/java/company/ac/za/studentbookstore/util/resized.jpg");

        if(inputFile != null){
            //File f = new File(url.getFile());
            System.out.println(inputFile.getPath());
            ImageIO.write(outputImage, "jpg", inputFile);
        }
        //File file1 = new File(dir+"resized.jpeg");
    }

    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath, double percent) throws IOException {
        //ClassLoader classLoader = new ImageResizer().getClass().getClassLoader();
        File inputFile = new File(inputImagePath);

        //File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, scaledWidth, scaledHeight);
    }

    /**
     * Test resizing images
     */
    public static void getResizedImage() throws IOException {
        String fileName = Paths.get("").toAbsolutePath().toString()+"output.jpg";
       // ClassLoader classLoader = new ImageResizer().getClass().getClassLoader();
        File file = new File(Paths.get("").toAbsolutePath().toString()+"output.jpg");

        BufferedImage bimg = ImageIO.read(file);
        System.out.println(bimg.getWidth()+"<<<width||height>>>>"+bimg.getHeight());

        double percent=0;
        if(bimg.getWidth()>=5000){
            try{
                 percent = 0.16;
                ImageResizer.resize(fileName, percent);
            } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }

        }else if(bimg.getWidth()>=3000){
            try{
                percent = 0.27;
                ImageResizer.resize(fileName, percent);
            } catch (IOException ex) {
                System.out.println("Error resizing the image.");
                ex.printStackTrace();
            }
        }else if(bimg.getWidth()>=1000){
            try {
                percent = 0.8;
                ImageResizer.resize(fileName, percent);
            } catch (IOException ex) {
                System.out.println("Error resizing the image.");
                ex.printStackTrace();
            }
        }else
            try {
                percent = 1;
                ImageResizer.resize(fileName,  percent);
            } catch (IOException ex) {
                System.out.println("Error resizing the image.");
                ex.printStackTrace();
            }
    }

    public static void getAbsolutDir(){
        System.out.println(Paths.get("").toAbsolutePath().toString());
        File file =new File(Paths.get("/").toAbsolutePath().toString()+"test.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getName());
    }

}
