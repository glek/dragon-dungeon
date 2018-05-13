
/**
 * A creature in game
 * 
 * @author Michael Damian Mulligan
 * @version 01/03/2011
 */
public class Creature
{
    /**
    * X co-ordinate of creature
    */
    protected int x;
    
    /**
     * Y co-ordinate of creature
     */
    protected int y;
    
    /**
     * Maze in which the creature lives
     */
    protected Maze maze;

    /**
     * Constructor for objects of class Creature
     * @param m Maze in which the creature exists
     */
    public Creature(Maze m)
    {
        this(0, 0, m);
    }
    
    /**
     * Constructor for Creatures
     * @param x X co-ordinate of the creature
     * @param y Y co-ordinate of the creature
     * @param m Maze the creature exists in
     */
    public Creature(int x, int y, Maze m)
    {
        maze = m;
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return x position
     */
    public int getX(){
        return x;
    }

    /**
     * @return y position
     */
    public int getY(){
        return y;
    }
    
    /**
     * @return True if valid move, false otherwise
     */
    public boolean canGoTo(int i, int j){
        if((i > Maze.SIZE - 1) || (i < 0)){
            return false;
        }

        if((j > Maze.SIZE - 1) || (j < 0)){
            return false;
        }

        if((j != y) && (i != x)){
            return false;
        }
        
        if(maze.hasWallAt(i, j)){
            return false;
        }

        if((java.lang.Math.abs(i - x) > 1) || (java.lang.Math.abs(j - y) > 1)){
            return false;
        }
        return true;
    }
    
    /**
     * Try to move down
     */
    public void moveDown(){
        if(canGoTo(x, y + 1)){
            y += 1;
        }
        return;
    }

    /**
     * Try to move up
     */
    public void moveUp(){
        if(canGoTo(x, y - 1)){
            y -= 1;
        }
        return;
    }

    /**
     * Try to move left
     */
    public void moveLeft(){
        if(canGoTo(x - 1, y)){
            x -= 1;
        }
        return;
    }

    /**
     * Try to move right
     */
    public void moveRight(){
        if(canGoTo(x + 1, y)){
            x += 1;
        }
        return;
    }
}
