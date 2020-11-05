package sample.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageService {
    private String fileName = Paths.get("").toAbsolutePath().toString()+"output.jpg";
    private File file_save_path = new File(fileName);

    private String fileName1 = Paths.get("").toAbsolutePath().toString()+"resized.jpg";
    private File file_read_path = new File(fileName1);
    private BufferedImage image = null;

    public void readAndWriteFile(String path) throws IOException {
        File file = new File(path);
        image = ImageIO.read(file);
        ImageIO.write(image, "JPG", new File(fileName));
    }

    public void pictureWriter(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "JPG", file_save_path);
        System.out.println("image created");
    }
    public byte[] convertToBytes(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file_read_path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum); //no doubt here is 0
            }
        } catch (IOException ex) {
            System.out.println("failed to convert to byte array");
        }
        bos.close();
        fis.close();
        return bos.toByteArray();
    }

    public byte[] encodeIntoByteArray(byte[] image) {
        String encodedString = Base64.getEncoder().encodeToString(image);
        return encodedString.getBytes();
    }

    public Boolean checkFileExtension(File file){
        String extension = "";
        try{
            if(file.exists()){
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
                System.out.println("extention :"+extension);
            }
        }catch (Exception e){
            System.out.println("Exception");
            extension = "";
        }
        if(!extension.equals("")){
            if(extension.equals(".jpg")||extension.equals(".jpeg")){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * this method convert BufferedImage into byteArray.
     * @param bufferedImage
     * @return
     */
    public byte[] convertBufferedImage(BufferedImage bufferedImage){
        BufferedImage image = new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(),bufferedImage.TYPE_3BYTE_BGR);
        return encodeIntoByteArray(((DataBufferByte) image.getRaster().getDataBuffer()).getData());
    }
}
