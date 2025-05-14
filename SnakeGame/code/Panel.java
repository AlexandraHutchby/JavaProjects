package SnakeGame.code;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;

public class Panel extends JPanel implements KeyListener{
    Snake snake;
    
    int vx = 0;
    int vy = 0;
    
    Random rand;
    ArrayList<NumberLocation> numberLocations; //a list of all the numbers currently being displayed
    LetterLocation letter; //the letter that is being displayed - only one at a time
    boolean playing = true;
    
    public Panel()
    {
        this.addKeyListener(this);     
        this.setFocusable(true); 
        this.setBackground(Color.BLACK); //change background colour
        
        snake = new Snake(500, 500); //x and y coordinates of the start of the snake
        rand = new Random();
        
        //creating all the number that are being displayed at the start of the game
        numberLocations = new ArrayList<>(); 
        for(int i = 0; i < 10; i++){
            int randomX = rand.nextInt(98) * 10; //I found that 98 helps that you can see the value without it being half out of the panel
            int randomY = rand.nextInt(98) * 10;
            int randomNum = rand.nextInt(9);
            numberLocations.add(new NumberLocation(randomX, randomY, randomNum));
        }
        
        //creating the letter that is being displayed at the start of the game
        int randomX = rand.nextInt(98) * 10;
        int randomY = rand.nextInt(98) * 10;
        int randomASCII;
        if(rand.nextBoolean()){
            randomASCII = rand.nextInt(26) + 65;
        }else{
            randomASCII = rand.nextInt(26) + 97;
        }
        letter = new LetterLocation(randomX, randomY, String.valueOf((char) randomASCII));
        
        //creating a thread so that the game will be constantly updating but not being instantaneous
        new Thread(()->{
            while(playing){
                updateGame();
                repaint();
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    System.out.println("Interruped Exception in Panel");
                }
            }
        }).start();
    }
    
    //function to check if the snake is moving and if it is moving to move and to check if has collided with numbers or the letter
    private void updateGame(){
        if(vx != 0 || vy != 0){
            snake.move(vx, vy);
            checkForCollisions();
        }
    }
    
    //function to check if the snake has hit any numbers of letters
    private void checkForCollisions(){
        Node head = snake.getHead();
        if(head == null) return; //the list is 0 so nothing to check against
        
        //to check if the snake has hit the letter
        if(head.x == letter.getX() && head.y == letter.getY()){ //compare the snakes x and y coordinates to the x and y coordinates of the letter
            snake.addToSnake(head.x, head.y, letter.getLetter()); //add the letter to the snake in order
            
            //change the letter to a new letter and position to the game
            letter.setX(rand.nextInt(99) * 10);
            letter.setY(rand.nextInt(99) * 10);
            int randomASCII;
            if(rand.nextBoolean()){
                randomASCII = rand.nextInt(26) + 65;
            }else{
                randomASCII = rand.nextInt(26) + 97;
            }
            letter.setLetter(String.valueOf((char) randomASCII));
            
            //create a new number to be added to the game - making it harder
            int randomX = rand.nextInt(99) * 10;
            int randomY = rand.nextInt(99) * 10;
            int randomNum = rand.nextInt(9);
            numberLocations.add(new NumberLocation(randomX, randomY, randomNum));
        }
        
        //to check if for any number that is in the numberlocations that the snake has hit any
        for(NumberLocation number : numberLocations){
            if(head.x == number.getX() && head.y == number.getY()){ //compare the snakes x and y coordinates to the x and y coordinates of each number
                snake.removeFromSnake(number.getNumber()); //remove an item from the snake based on the number that was hit 
                
                //if the snake becomes no exisitant the game is over so end the game
                if(snake.getSize() < 0){
                    playing = false;
                    System.out.println("GAME OVER!! SNAKE HAS DIED");
                    System.exit(0);
                }
                
                //change the number to a new number and position in the game
                number.setX(rand.nextInt(99) * 10);
                number.setY(rand.nextInt(99) * 10);
                number.setNumber(rand.nextInt(9));
                break;
            }
        }
    }
    
    public void paint(Graphics g)
    {
        paintComponent(g);
        g.setColor(Color.WHITE); //create all the numbers, letters and snake to be in white
        
        snake.drawSnake(g); //call the snake draw function
        
        for(NumberLocation number : numberLocations){
            g.drawString(String.valueOf(number.getNumber()), number.getX(), number.getY()); //display the numbers
        }
        
        g.drawString(letter.getLetter(), letter.getX(), letter.getY()); //display the letter
    }


    @Override
    public void keyTyped(KeyEvent ke) {
        

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyChar() == 'w')
        {
            vy = -10; //go up - vy goes down as 0 is the top and 1000 is the bottom
        }
        if(ke.getKeyCode() == KeyEvent.VK_DOWN || ke.getKeyChar() == 's')
        {
            vy = 10; //go down - vy goes up as 0 is the top and 1000 is the bottom
        }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyChar() == 'a')
        {
            vx = -10; //go left - vx goes down as 0 is the left and 1000 is the right
        }
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyChar() == 'd')
        {
            vx = 10; //go right - vx goes up at 0 is the left and 1000 is the right
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        vx = 0; //reset the vx once the key is released
        vy = 0; //reset the vy once the key is released
    }    
}
