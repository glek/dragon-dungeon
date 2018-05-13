import javax.swing.JButton;
import javax.swing.Icon;

/**
 * Button for graphical game interface
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class GameButton extends JButton
{
    private String action;

    /**
     * Constructor for objects of class GameButton
     * @param text Text to display on button
     * @param action Action assigned to button
     */
    public GameButton(String text, String action)
    {
        super(text);
        this.action = action;
    }
    
    /**
     * Constructor for objects of class GameButton
     * @param icon Image to display on button
     * @param action Action assigned to button
     */
    public GameButton(Icon icon, String action)
    {
        super(icon);
        this.action = action;
    }

    /**
     * @return Action assigned to button
     */
    public String getActionName(){
        return action;
    }
}
