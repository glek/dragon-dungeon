
/**
 * Exit for DragonDungeon, all switches must be on to escape
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class Exit implements Item
{
    private int x;
    private int y;

    /**
     * Constructor for objects of class Exit
     * @param i Exit's X position
     * @param j Exit's Y position
     */
    public Exit(int i, int j)
    {
        x = i;
        y = j;
    }

    /**
     * @return X of exit
     */
    public int getX(){
        return x;
    }
    
    /**
     * @return Y of exit
     */
    public int getY(){
        return y;
    }
}
