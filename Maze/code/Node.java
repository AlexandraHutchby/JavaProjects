package Maze.code;

public class Node {
    /*
     * I created this class as a way to store all the information for all the points
     * It holds the name that it is, the x and y coordinates, the left and right nodes and their names
     */

     public String name;
     public int x;
     public int y;
     public String leftNodeName;
     public String rightNodeName;
     public Node left;
     public Node right;

     public Node(){ //if no information is given then it will be 0 or null
        this.x = 0;
        this.y = 0;
        this.leftNodeName = null;
        this.rightNodeName = null;
        this.left = null;
        this.right = null;
     }

     public Node(String name, int x, int y, String leftNodeName, String rightNodeName){
        this.name = name;
        this.x = x;
        this.y = y;
        this.leftNodeName = leftNodeName;
        this.rightNodeName = rightNodeName;
        this.left = null;
        this.right = null;
     }
}
