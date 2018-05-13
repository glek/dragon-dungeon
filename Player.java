
/**
 * Player for games, should be extended as needed
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class Player extends Creature
{
    /**
     * Constructor for objects of class Player
     * @param m Maze in which the player lives
     */
    public Player(Maze m)
    {
        this(0, 0, m);
    }
    
    /**
     * Constructor for players
     * @param x Player's y position
     * @param y Player's x position
     * @param m Maze in which the player lives
     */
    public Player(int x, int y, Maze m)
    {
        super(x, y, m);
    }

    /**
     * Take a input character and try to perform an action.
     * 'W' means go up.
     * 'A' means go left.
     * 'S' means go down.
     * 'D' means go right.
     * 'M' means drop trap.
     * @param command The character given
     */
    public void processCommand(char command){
        if((command == 'W') || (command == 'w')){
            moveUp();
        }
        if((command == 'S') || (command == 's')){
            moveDown();
        }
        if((command == 'A') || (command == 'a')){
            moveLeft();
        }
        if((command == 'D') || (command == 'd')){
            moveRight();
        }
        return;
    }
}
