import java.awt.Point;
import java.util.ArrayList;

/**
 * Main class for Dragon Dungeon game
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class DragonDungeon extends Maze
{
    private Exit exit;
    private ArrayList<Fire> fireList;
    /**
     * Constructor for objects of class Dungeon
     * Makes new player at (1, 1) and places 4 monsters and 4 switches and the exit
     * @param diff The difficulty where 1 is easy, 2 is medium, 3 is hard, etc
     */
    public DragonDungeon(int diff)
    {
        super(diff);
        p = new Dragon(1, 1, diff, this);
        fireList = new ArrayList<Fire>();
        placeExit();
        placeMonsters(SIZE / 2 - 1);
        placeSwitches(SIZE / 2 - 1);
    }
    
    /**
     * @return True if all switches pressed
     */
    public boolean allPressed(){
        Switch temp;
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                if(hasSwitch(x, y)){
                    temp = (Switch) grid[y][x];
                    if(!temp.getState()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * Override of the hasWon function of maze
     * @return True if the player is on the exit and all the switches have been pressed
     */
    @Override
    public boolean hasWon(){
        if((p.getX() == exit.getX()) && (p.getY() == exit.getY())){
            if(allPressed()){
                return true;
            }
        }
        return false;
    }
    
    /**
     * @param x X co-ordinate
     * @param y Y co-ordinate
     * @return True if the exit is at x, y - false otherwise
     */
    public boolean hasExitAt(int x, int y){
        if((x == exit.getX()) && (y == exit.getY())){
            return true;
        }
        return false;
    }
    
    /**
     * @return A string representation of the current maze
     */
    @Override
    public String toString(){
       String output = "";
       for(int i = 0; i < SIZE; i++){
           for(int x = 0; x < SIZE; x++){
               if(hasWallAt(x, i)){
                   output += "W";
                   continue;
               }
               if(hasFireAt(x, i)){
                   output += "F";
                   continue;
                }
               if(hasMonsterAt(x, i)){
                   output += "M";
                   continue;
               }
               if((i == p.getY()) && (x == p.getX())){
                   output += "D";
                   continue;
               }
               if(hasSwitch(x, i)){
                   output += "S";
                   continue;
               }
               if(hasExitAt(x, i) && allPressed()){
                   output += "X";
                   continue;
               }
               if(grid[i][x] == null){
                   output += ".";
               }
           }
           output += "\n";
       }
       return output;
    }
    
    /**
     * Set's the monster at i, j to be dead if there's a monster there
     * @param i The X position of the monster to kill
     * @param j The Y position of the monster to kill
     */
    public void killMonster(int i, int j){
        for(Monster m : monsters){
            if((m.getX() == i) && (m.getY() == j)){
                m.kill();
            }
        }
        return;
    }
    
    /**
     * Try to shoot fire into the square given if there is no wall
     * Kill any monsters caught in the fire
     * @param x X position to shoot fire into
     * @param y Y position to shoot fire into
     */
    public void shootFire(int x, int y){
        if(!hasWallAt(x, y)){
            fireList.add(new Fire(x, y));
            if(hasMonsterAt(x, y)){
                killMonster(x, y);
            }
        }
    }
    
    /**
     * @param x X position to check
     * @param y Y position to check
     * @return True if there is fire at (x, y) false otherwise
     */
    public boolean hasFireAt(int x, int y){
        for(Fire a : fireList){
            if((a.getX() == x) && (a.getY() == y)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Clear all fire from the board
     */
    public void clearFire(){
        fireList.clear();
        return;
    }
    
    /**
     * Select an exit point on the opposite side of the maze
     */
    public void placeExit(){
        for(int y = SIZE - 1; y >= 0; y--){
            for(int x = SIZE - 1; x >= 0; x--){
                if(grid[y][x] == null){
                    exit = new Exit(x, y);
                    return;
                }
            }
        }
        return;
    }
    
    /**
     * Place the switches for the dungeon
     * @param amount The number of switches
     */
    public void placeSwitches(int amount){
        int tempAmount = amount;
        java.util.Random rand = new java.util.Random();
        for(int x = SIZE - 1; x >= 0; x--){
            for(int y = SIZE - 1; y >= 0; y--){
                if((rand.nextInt(10) % 5 == 0) && (!hasWallAt(x, y)) && (!hasExitAt(x, y)) && (tempAmount > 0)){
                    grid[y][x] = new Switch();
                    tempAmount--;
                }
            }
        }
        return;
    }
    
    /**
     * @param x X position to check
     * @param y Y position to check
     * @return True if there is a switch at (x, y)
     */
    public boolean hasSwitch(int x, int y){
        if(grid[y][x] instanceof Switch){
            return true;
        }
        return false;
    }
    
    /**
     * @param x X position of switch
     * @param y Y position of switch
     * @return True if the switch at (x, y) has been stepped on, false otherwise
     */
    public boolean switchActive(int x, int y){
        if(hasSwitch(x, y)){
            Switch temp = (Switch) grid[y][x];
            return temp.getState();
        }
        return false;
    }
    
    /**
     * The switch at (x, y) has been stepped on, change it's state
     * @param x The x position of the switch to change
     * @param y The y position of the switch to change
     */
    public void onSwitch(int x, int y){
        Switch temp = (Switch) grid[y][x];
        temp.stepOn();
        return;
    }
}
