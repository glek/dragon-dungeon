import java.util.ArrayList;

/**
 * Static wall, can't go through.  Building block of maze.
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class Wall implements Item
{
    private int X;
    private int Y;
    /**
     * Constructor for objects of class Wall
     * @param x Wall's x position
     * @param y Wall's y position
     */
    public Wall(int x, int y)
    {
        X = x;
        Y = y;
    }
    
    /**
     * @return X position
     */
    public int getX(){
        return X;
    }
    
    /**
     * @return Y position
     */
    public int getY(){
        return Y;
    }
}
