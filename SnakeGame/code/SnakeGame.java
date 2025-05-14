package SnakeGame.code;

import javax.swing.JFrame;

public class SnakeGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Snake"); //name the panel snake
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if the panel is closed, end
        Panel panel = new Panel(); //create the panel
        
        frame.getContentPane().add(panel);
        frame.setSize(1000, 1000); //create size of the panel
        frame.setVisible(true);
    }
}
    
