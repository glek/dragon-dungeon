import java.util.Scanner;

/**
 * Main game class, runs game
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class Game
{
    private Maze m;
    /**
     * Constructor for objects of class Game
     * @param maze The maze being played
     */
    public Game(Maze maze)
    {
        m = maze;
    }

    public static void main(String args[]){
        Game myGame;
        myGame = new Game(new DragonDungeon(1));
        myGame.play();
        return;
    }
    
    /**
     * Play the game, calls the GUI
     */
    public void play(){
        GraphicalDragonDungeon gameFrame = new GraphicalDragonDungeon((DragonDungeon) m);
    }
}
