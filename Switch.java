
/**
 * Switch item, all must be on to win game
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class Switch implements Item
{
    private boolean active;

    /**
     * Constructor for objects of class Switch
     */
    public Switch()
    {
        active = false;
    }

    /**
     * Set the switch to active if stepped on
     */
    public void stepOn(){
        active = true;
    }
    
    /**
     * @return True if switch has been stepped on, false otherwise
     */
    public boolean getState(){
        return active;
    }
}
