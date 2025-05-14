package Maze.code;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MazeApp{
    public static void main(String[] args){
        JFileChooser imageFileChooser = new JFileChooser(new File("Maze")); //created so that you can pick the maze
        int stateImageFileChooser = imageFileChooser.showOpenDialog(null);

        if(stateImageFileChooser == JFileChooser.APPROVE_OPTION){
            File selectedFile = imageFileChooser.getSelectedFile();

            JFrame frame = new JFrame("Maze");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Panel panel = new Panel(selectedFile.getAbsolutePath());
            frame.add(panel);
            frame.setSize(750, 650);
            frame.setVisible(true);
            panel.animation();
        }
    }
}