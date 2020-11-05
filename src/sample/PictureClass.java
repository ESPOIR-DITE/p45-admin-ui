package sample;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class PictureClass {
    private String fileName = Paths.get("").toAbsolutePath().toString()+"output.jpg";
    private File file_save_path = new File(fileName);
    byte[] bytes;

    public byte[] getBytes(){
        return bytes;
    }

    public void run(){


            JButton jButton = new JButton("Take picture");
            Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(WebcamResolution.VGA.getSize());

            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setDisplayDebugInfo(true);
            panel.setImageSizeDisplayed(true);
            panel.setMirrored(true);

            JPanel myPanel = new JPanel();
            myPanel.setLayout(new FlowLayout());
            myPanel.add(panel);
            myPanel.add(jButton);

            JFrame window = new JFrame("Test webcam panel");
            window.add(myPanel);
            window.setResizable(true);
            //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.pack();
            window.setVisible(true);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jButton.setVisible(false);

                    bytes = WebcamUtils.getImageBytes(webcam, "jpg");
                    //System.out.println("Byte in Picture: "+bytes);

                    window.dispose();
                    webcam.close();
                }
            } );


    }
}

