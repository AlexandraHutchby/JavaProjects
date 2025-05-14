package Maze.code;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Stack;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Panel extends JPanel {
    Maze maze;
    private String[] visited;
    private Stack<Node> currentPath;
    private int index = 0;
    private boolean found = false;
    JLabel pathLabel;
    Node[] path;

    public Panel(String mazeFilePath){
        this.maze = new Maze(mazeFilePath); //create the maze based on the maze picked
        this.currentPath = new Stack<>(); //stack of the currenth path
        this.visited = new String[maze.mazeNodes.length]; //array of all the nodes visited - so you don't go back to it
        this.setLayout(null);

        pathLabel = new JLabel("Path: "); //path label for the end
        pathLabel.setBounds(10, 10, 400, 30); //location of the label
        pathLabel.setForeground(Color.BLUE); //colour
        add(pathLabel);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        paintPath(g);
        paintNodes(g);

        if(found && path != null){
            paintFoundPath(g);
        }
    }

    /*
     * This function is to print all the nodes onto the panel and show them
     */
    private void paintNodes(Graphics g){
        for(Node node : maze.map.values()){
            if(node != null){
                //draw node in circle
                g.setColor(Color.BLUE); //colouring in blue
                g.fillOval(node.x * 20, node.y * 20, 20, 20); // this is based on the x and y (I times by 20 to make them spaces out and 20 in diameter)
                g.drawString(node.name, node.x * 20, node.y * 20); //draw the name of the node beside the node
            }
        }
    }

    /*
     * This function is to print all the paths on the panel
     */
    private void paintPath(Graphics g){
        for(Node node : maze.map.values()){
            if(node != null){
                g.setColor(Color.ORANGE); //colouring in orange
                
                //draw left path
                if(node.left != null){
                    g.drawLine(node.x * 20 + 10, node.y * 20 + 10, node.left.x * 20 + 10, node.left.y * 20 + 10);
                }

                //draw right path
                if(node.right != null){
                    g.drawLine(node.x * 20 + 10, node.y * 20 + 10, node.right.x * 20 + 10, node.right.y * 20 + 10);
                }
            }
        }
    }

    /*
     * This function repaints the path when the correct path is found
     */
    private void paintFoundPath(Graphics g){
        g.setColor(Color.GREEN); //colouring in green
        for(int i = 0; i < path.length - 1; i++){
            Node current = path[i];
            Node next = path[i+1];
            g.drawLine(current.x * 20 + 10, current.y * 20 + 10, next.x * 20 + 10, next.y * 20 + 10);
        }

        if(path.length > 0){
            Node exitNode = path[path.length - 1];
            g.setColor(Color.RED); //colouring in red
            g.fillOval(exitNode.x * 20, exitNode.y*20, 20, 20); //repaint the final node in red (as if not the final goes back to blue)
        }
    }

    /*
     * This function finds the path to the end
     */
    private void findPath(Node current){
        if (current == null || found) { //if there is no current node or the path has been found
            return;
        }

        for (String value : visited) { //check the current node against the nodes we already have visited
            if (value != null && current.name.equalsIgnoreCase(value)) {
                return;
            }
        }
        changeColour(current, Color.RED); //colouring in red
        currentPath.push(current); //add the current node to the top of the path
        visited[index++] = current.name; //add the current node to the visited list (ensures it won't be revisited again)

        if (current.name.equalsIgnoreCase("EXIT")) { //if the value is exit
            found = true; //found the path
            printPath(); //printing the path in green
            repaint(); //repainting it to actually show it
            return;
        }

        try { //wait to make it more of an animation
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("interruptedException in find path");
        }

        if (current.left != null && !found) { //if there is a value to the left and path not found go left
            changeColour(current, Color.BLUE); //change back to blue - so red shows as the current one
            findPath(current.left); 
        }
        if (current.right != null && !found) { //if there is value to the right and the path has not been found go right
            changeColour(current, Color.BLUE); //change back to blue
            findPath(current.right);
        }
        
        if (!found) { //if the path has not been found continue - if this was not here it would pop the whole stack and make the path not found
            changeColour(current, Color.BLUE); //change back to blue
            currentPath.pop(); //remove from stack as this isn't in the path
            current = (Node) currentPath.peek(); //change the current top of the path to red
            changeColour(current, Color.RED);
            try {
                Thread.sleep(500); //wait to make it more of an animation
            } catch (InterruptedException e) {
                System.out.println("interruptedException in find path");
            }
            changeColour(current, Color.BLUE); //change back to blue and continue
        }
    }

    /*
     * This function prints the path to the label once the path is found
     */
    private void printPath(){
        StringBuilder message = new StringBuilder("Path: "); //Path string
        path = (Node[]) currentPath.toArray(new Node[0]); //make the stack into an array to iterate through
        
        for(Node n : path){
            message.append(n.name).append(" "); //add each node to the end of the message
        }

        SwingUtilities.invokeLater(()->{
            pathLabel.setText(message.toString()); //print the path to the GUI
        });
    }

    /*
     * This function changes the node colour - reduces code error as it is only written once and is called upon multiple times
     */
    private void changeColour(Node current, Color colour){
        SwingUtilities.invokeLater(()->{
            Graphics g = getGraphics();
            g.setColor(colour); //set the colour we are colouring in to the colour we want
            g.fillOval(current.x * 20, current.y * 20, 20, 20); //repaint the node to the colour we want
        });
    }

    /*
     * This function ensures that there is an animation by using a thread
     */
    public void animation(){
        new Thread(()->findPath(maze.startNode)).start();
    }
}
