package Maze.code;

import java.util.HashMap;
import java.util.Map;

public class Maze {
    FileManager fm;
    String mazeData;
    String[] mazeNodes;
    Node startNode;
    Node exitNode;
    Map<String, Node> map;
    
    public Maze(String filePath){
        fm = new FileManager(filePath);
        fm.readFile(null);
        mazeData = fm.data.substring(fm.data.indexOf("\n")+1);
        mazeNodes = mazeData.split("\n");

        map = new HashMap<>();

        createNodes();
    }

    private void createNodes(){
        for(String line : mazeNodes){
            String[] parts = line.split(","); //split into the name, x and y coordinates, left node name and right node name
            String name = parts[0].trim();
            int x = Integer.parseInt(parts[1].trim()) * 5 + 2;
            int y = Integer.parseInt(parts[2].trim()) * 5 + 2;
            String leftNodeName = parts.length > 3 ? parts[3].trim() : null; //if there is a left node attached add it
            String rightNodeName = parts.length > 4 ? parts[4].trim() : null; //if there is a right node attached add it

            Node node = new Node(name, x, y, leftNodeName, rightNodeName);
            map.put (name, node);

            if(name.equals("START")){
                startNode = node;
            }else if(name.equals("EXIT")){
                exitNode = node;
            }
        }

        linkNodes(); //link all the nodes together
    }

    private void linkNodes(){
        for(Node node : map.values()){
            if(node.leftNodeName != null){ //attach the left node if there
                node.left = map.get(node.leftNodeName);
            }
            if(node.rightNodeName != null){
                node.right = map.get(node.rightNodeName);
            }
            if(node.leftNodeName.equalsIgnoreCase("W")){// if the left node is W (placeholder for exit)
                node.left = exitNode;
            }
            if(node.rightNodeName.equalsIgnoreCase("W")){//if the right node is W (placeholder for exit)
                node.right = exitNode;
            }
        }
    }

    public Node getStartNode(){
        return startNode;
    }

    public Node getExitNode(){
        return exitNode;
    }
}
