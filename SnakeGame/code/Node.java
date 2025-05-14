package SnakeGame.code;



public class Node{
    
    int x; //x coordinate of the particular part of the snake
    int y; //y coordinate of the particular part of the snake
    String data; //string of the particular part of the snake
    Node next; //which string comes next in the snake
    
    Node(int x, int y, String data){ //create a new part of the snake based on the new x and y coordinates, and data
        this.x = x;
        this.y = y;
        this.data = data;
        this.next = null;
    }
    
    Node(){ //initialising a new node with no information provided (used as a temporary placeholder in linked list functions
        this.x = 0;
        this.y = 0;
        this.data = null;
        this.next = null;
    }
    
    //if the data given is equal to the data of this particular part of the snake
    public boolean equals(Node node)
    {
        if(this.data == node.data) //comparing data
            return true;
        return data.equals(node.data);
    }
    
    //comparing data given to the data of this particular part of the snake
    public int compareTo(Node node)
    {
        if(equals(node)) //comparing to see if the same
        {
            return 0;
        }
        if(this.data == null)  //if this part of the snake has no information
            return -1;
        if(node.data == null) //if the data given has no information
            return 1;
 
        //comparing the data based on the individual characters in the string then basing on the ASCII numbers
        for(int i = 0; i < this.data.length(); i++){
            char thisCh = this.data.charAt(i);
            char nodeCh = node.data.charAt(i);
            if(thisCh != nodeCh){
                return thisCh - nodeCh;
            }
        }
        return 0; //they are identical
    }
}

