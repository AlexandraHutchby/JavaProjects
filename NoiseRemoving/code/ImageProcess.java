package NoiseRemoving.code;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageProcess{
    BufferedImage buffered_image = null;

    public ImageProcess(String image){
        try{
            buffered_image = ImageIO.read(new File(image));
        }catch(IOException ex){
            Logger.getLogger(ImageProcess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void save(String imageName){
        int i = imageName.indexOf(".");
        String type = imageName.substring(i+1);
        try{
            ImageIO.write(buffered_image, type, new File(imageName));
        }
        catch(IOException e){
            System.err.println("image not saved.");
        }
    }

    public void addNoise(float density){
        int width = buffered_image.getWidth();
        int height = buffered_image.getHeight();
        WritableRaster writable_raster = buffered_image.getRaster();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int colour = buffered_image.getRGB(j, i);

                int[] pixel = new int[3];
                pixel[2] = colour & 0xff; //blue
                pixel[1] = (colour & 0xff00) >> 8; //green
                pixel[0] = (colour & 0xff0000) >> 16; //red

                if(Math.random()<density){
                    int noise = (int)(255*Math.round(Math.random()));
                    pixel[0] = noise;
                    pixel[1] = noise;
                    pixel[2] = noise;
                }

                writable_raster.setPixel(j, i, pixel);
            }
        }
    }

    public void cleanNoise(){
        int width = buffered_image.getWidth();
        int height = buffered_image.getHeight();
        WritableRaster writable_raster = buffered_image.getRaster();

        for(int i = 1; i < height-1; i++){
            for(int j = 1; j < width-1; j++){
                Integer[] intensity_r = new Integer[9];
                Integer[] intensity_g = new Integer[9];
                Integer[] intensity_b = new Integer[9];
                int index = 0;
                int[] pixel = new int[3];

                for(int k = -1; k < 2; k++){
                    for(int l = -1; l < 2; l++){
                        int colour = buffered_image.getRGB(j+k, i+l);

                        pixel[2] = colour & 0xff; //blue
                        pixel[1] = (colour & 0xff00) >> 8; //green
                        pixel[0] = (colour & 0xff0000) >> 16; //red
                        intensity_b[index] = pixel[2];
                        intensity_g[index] = pixel[1];
                        intensity_r[index] = pixel[0];
                        index++;
                    }
                }

                //this is my code (code above given)
                SortArray<Integer> red = new SortArray<>(intensity_r);
                red.quickSort();
                SortArray<Integer> blue = new SortArray<>(intensity_b);
                blue.quickSort();
                SortArray<Integer> green = new SortArray<>(intensity_g);
                green.quickSort();

                //index 4 holds median value of R, G and B
                pixel[2] = intensity_b[4];
                pixel[1] = intensity_g[4];
                pixel[0] = intensity_r[4];

                writable_raster.setPixel(j, i, pixel);
            }
        }
    }
}