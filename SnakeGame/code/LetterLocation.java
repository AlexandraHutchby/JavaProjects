package SnakeGame.code;

public class LetterLocation {
    private int x; //x coordinate of the letter
    private int y; //y coordinate of the letter
    private String letter; //which letter is being shown
    
    public LetterLocation(int x, int y, String letter){ //create the first letter
        this.x = x;
        this.y = y;
        this.letter = letter;
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
     * @return the letter
     */
    public String getLetter() {
        return letter;
    }

    /**
     * @param letter the letter to set
     */
    public void setLetter(String letter) {
        this.letter = letter;
    }
}
