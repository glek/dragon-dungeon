
/**
 * Dragon for Dragon Dugeon game
 * Can move into adjacent cells and shoot fire up to 2 blocks away
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class Dragon extends Player
{
    private int DEF_AMMO = 3;
    private int ammo;
    private int withoutAmmo;
    /**
     * Constructor for objects of class Dragon
     * @param m Maze in which the dragon lives
     * @param diff Difficulty of the game, 1 - easy, 2 - medium, 3 - hard, etc
     */
    public Dragon(Maze m, int diff)
    {
        this(0, 0, diff, m);
    }
    
    /**
     * Constructor for dragons
     * @param x Dragon's x position
     * @param y Dragon's y position
     * @param diff Difficulty of the game, 1 - easy, 2 - medium, 3 - hard, etc
     * @param m Dragon's maze
     */
    public Dragon(int x, int y, int diff, Maze m)
    {
        super(x, y, m);
        DEF_AMMO = m.SIZE / 2 - 2;
        ammo = DEF_AMMO;
        withoutAmmo = 0;
    }
    
    /**
     * Take a input character and try to perform an action.  Keep track of when to increase ammo count
     * 'W' means go up.
     * 'A' means go left.
     * 'S' means go down.
     * 'D' means go right.
     * 'M' means drop trap.
     * 'I' means shoot up.
     * 'K' means shoot down.
     * 'J' means shoot left.
     * 'L' means shoot right.
     * @param command The command passed
     */
    @Override
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
        if((command == 'I') || (command == 'i')){
            shootUp();
        }
        if((command == 'K') || (command == 'k')){
            shootDown();
        }
        if((command == 'J') || (command == 'j')){
            shootLeft();
        }
        if((command == 'L') || (command == 'l')){
            shootRight();
        }
        if(withoutAmmo > 3){
            withoutAmmo = 0;
            ammo++;
        }
        if(ammo < DEF_AMMO){
            withoutAmmo++;
        }
        DragonDungeon temp = (DragonDungeon) maze;
        if(temp.hasSwitch(x, y)){
            temp.onSwitch(x, y);
        }
        return;
    }
    
    /**
     * Try to shoot up
     */
    public void shootUp(){
        DragonDungeon temp = (DragonDungeon) maze;
        if(ammo == 0){
            return;
        }
        ammo--;
        temp.shootFire(x, y - 2);
        temp.shootFire(x, y - 1);
        return;
    }
    
    /**
     * Try to shoot down
     */
    public void shootDown(){
        DragonDungeon temp = (DragonDungeon) maze;
        if(ammo == 0){
            return;
        }
        ammo--;
        temp.shootFire(x, y + 2);
        temp.shootFire(x, y + 1);
        return;
    }
    
    /**
     * Try to shoot left
     */
    public void shootLeft(){
        DragonDungeon temp = (DragonDungeon) maze;
        if(ammo == 0){
            return;
        }
        ammo--;
        temp.shootFire(x - 2, y);
        temp.shootFire(x - 1, y);
        return;
    }
    
    /**
     * Try to shoot right
     */
    public void shootRight(){
        DragonDungeon temp = (DragonDungeon) maze;
        if(ammo == 0){
            return;
        }
        ammo--;
        temp.shootFire(x + 2, y);
        temp.shootFire(x + 1, y);
        return;
    }
    
    /**
     * @return Ammo left
     */
    public int getAmmo(){
        return ammo;
    }
}
