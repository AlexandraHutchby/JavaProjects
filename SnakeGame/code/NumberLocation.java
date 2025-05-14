package SnakeGame.code;


public class NumberLocation {
    private int x; //x coordinate of the number
    private int y; //y coordinate of the number
    private int number; //the number of which is displayed
    
    public NumberLocation(int x, int y, int number){ //creation of the numbers
        this.x = x;
        this.y = y;
        this.number = number;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }
}
