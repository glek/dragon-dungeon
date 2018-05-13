
/**
 * Dragon's fire, kills enemies
 * For rendering purposes
 * 
 * @author Michael Damian Mulligan
 * @version March 31, 2011
 */
public class Fire implements Item
{
    private int x;
    private int y;

    /**
     * Constructor for objects of class Fire
     * @param x Fire's x position
     * @param y Fire's y position
     */
    public Fire(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X position
     */
    public int getX(){
        return x;
    }
    
    /**
     * @return Y position
     */
    public int getY(){
        return y;
    }
}
