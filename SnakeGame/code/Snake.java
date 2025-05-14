package SnakeGame.code;

import java.awt.Graphics;

public class Snake extends LinkedList{
    int x; //x coordinate of the snake
    int y; //y coordinatte of the snake
    int indexlength = 0; //length of the snake (length is actually the index of the items rather than the actual length of the snake - length -1)
    
    public Snake(int x, int y)
    {
        super(); //create the linked list
        add(x, y, "$"); //add the head to the list
    }
    
    //add a new item to the snake and increase the length
    public void addToSnake(int x, int y, String value){
        addInOrder(x, y, value); //add in order so that it will be alphabetical based on ASCII value
        indexlength++;
    }
    
    //remove an item from the snake and decrease the length
    public void removeFromSnake(int position){
        if(position > indexlength){ //if the number given is larger than the length of indexes then remove from the end instead
            removeFromTail();
        }else{ //else just remove based on the index
            remove(position);
        }
        indexlength--;
    }
    
    //move the snake
    public void move(int vx, int vy){
        Node head = getHead(); 
        
        if(head != null){ //if head is null then their is nothing to move 
            int prevX = head.x; //storage for the current position of the head so that the items move like a snake
            int prevY = head.y; //same as above but for y
            
            head.x += vx; //change the location of the head based on the change vx
            head.y += vy; //same but for vy (given based on the inputed keys to see which way the snake moves)
            
            Node current = head.next; //for the rest of the variables to ensure they move in a snake like motion
            
            while(current != null){
                int tempX = current.x; //temporary variable so that the item after goes to the current spot
                int tempY = current.y; 
                
                current.x = prevX; //move the current node to where the head/last node was
                current.y = prevY;
                
                prevX = tempX; //update the previous to where the item was before it moved
                prevY = tempY;
                
                current = current.next; //go to the next element
            }
        }
    }
    
    //drawing the snake
    public void drawSnake(Graphics g){
        Node node = getHead();
        
        while(node != null){ 
            g.drawString(node.data, node.x, node.y); //draw where it is
            node = node.next;
        }
    }
    
    public int getSize(){
        return indexlength;
    }
}

