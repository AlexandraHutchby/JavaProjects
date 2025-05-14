package SnakeGame.code;

public class LinkedList{
    
    public int size = 0; //size of the snake
    public Node head; //first variable of the list
    
    //add head function 
    public void addHead(int x, int y, String data) 
    {
        Node temp = head;
        if(head == null){ //if there currently is no head
            head = temp;
        }else{//if there is a head 
            add(head, temp); 
        }
    }
    
    //get the head function
    public Node getHead()
    {
        return head;
    }
    
    //add a new item to the snake public version - called from other files
    public void add(int x, int y, String data)
    {
        Node newNode = new Node(x, y, data);
        if(head == null){
            head = newNode;
        }
        else{
            add(head, newNode);
        }
        size++;
    }
    
    //add a new item to the snake private - called within the file
    private void add(Node head, Node node)
    {
        if(head.next == null){
            head.next = node;
        }else{
            add(head.next, node);
        }
    }
    
    //remove function where the index is given - in this case when the snake hits the number that is the position
    public void remove(int position) throws NullPointerException
    {
        if(position < 0 || position >= size){ //if number is not an index within the list AKA under 0 or bigger than the size
            throw new IndexOutOfBoundsException("Index out of bounds: "+position);
        }
        
        if(position == 0){
            removeFromHead();
        }
        else{
            removeByIndex(head, position);
        }
    }
    
    //remove function based on index (private function)
    private void removeByIndex(Node head, int position) throws NullPointerException
    {
        
        Node current = getNode(position-1);   
        if(current.next != null){
            current.next = current.next.next;
            
            size--;
        }
    }
    
    //if the position is 0 removing the item from the head
    public Node removeFromHead() throws NullPointerException
    {
        if(head == null){
            return null;
        }
        Node removedNode = head;
        head = head.next;
        size--;
        return removedNode;
    }
    
    //if the position is the same size as the linked list removing it from the end
    public Node removeFromTail()
    {
        if(head == null){
            return null;
        }
        if(size == 1){
            return removeFromHead();
        }
        Node newTail = removeFromTail(head);
        return newTail;
    }
    
    //private function of removing from the tail
    private Node removeFromTail(Node node)
    {
        if(node.next.next == null){
            Node removedNode = node.next;
            node.next = null;
            size--;
            return removedNode;
        }
        return removeFromTail(node.next);
    }
    
    //function to add the items in order based on the data (goes through Node)
    public void addInOrder(int x, int y, String data)
    {
        Node newNode = new Node(x, y, data);
        if(head == null || (head.data != null && head.data.compareTo(data)>0)){ //compares data to see if the item needs to be the new head
            newNode.next = head;
            head = newNode;
        }else {
            addInOrder(head, newNode);
        }
    }
    
    //private function that actually orders the data that is called from the function before
    private void addInOrder(Node currentNode, Node newNode)
    {

        while(currentNode.next != null && currentNode.next.compareTo(newNode) < 0){ //compares data through Node class
            currentNode = currentNode.next;
        }
        newNode.next = currentNode.next;
        currentNode.next = newNode;
        size++;
    }
    
    //returns the node based on the index provided
    public Node getNode(int index)
    {
        return getNode(index, head);
    }
    
    //finds the node in the list based on the index procided
    private Node getNode(int index, Node head)
    {
        int position = 0; // when we return the node we want to return it one before the node we want to remove
        if(head == null){ //if no items in the list
            return null;
        }
        while(position < index && head.next!=null){
            head = head.next;
            position++;
        }
        return head;
    }
    
  
}

