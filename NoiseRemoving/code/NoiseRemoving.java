package NoiseRemoving.code;

import java.io.File;
import javax.swing.JFileChooser;

public class NoiseRemoving {
    public static void main(String[] args) {
        JFileChooser imageFileChooser = new JFileChooser(new File("NoiseRemoving"));
        int stateImageFileChooser = imageFileChooser.showOpenDialog(null);

        if(stateImageFileChooser == JFileChooser.APPROVE_OPTION){
            File selectedFile = imageFileChooser.getSelectedFile();
            String fileName = selectedFile.getPath();

            //Process the image
            ImageProcess ip = new ImageProcess(fileName);
            ip.cleanNoise();

            //Save the processed image to this folder
            String outputFilePath = selectedFile.getParent() + File.separator + "noise_removed.jpg";
            ip.save(outputFilePath);
        }
    }
}
