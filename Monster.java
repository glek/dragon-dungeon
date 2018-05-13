import java.awt.Point;
import java.util.ArrayList;

/**
 * Generic monster for game
 * 
 * @author Michael Damian Mulligan
 * @version March 30, 2011
 */
public class Monster extends Creature
{
    private boolean isAlive;
    private boolean hasMoved;
    /**
     * Constructor for objects of class Monster
     * @param m Maze the monster exists in
     */
    public Monster(Maze m)
    {
        this(0, 0, m);
    }
    
    /**
     * Constructor for monster
     * @param x X co-ordinate of the monster
     * @param y Y co-ordinate of the monster
     * @param m Maze the monster exists in
     */
    public Monster(int x, int y, Maze m)
    {
        super(x, y, m);
        isAlive = true;
        hasMoved = false;
    }
    
    /**
     * Move one step in the general direction of the Player
     * @param roundTwo Is this the second movement attempt.  If so, check if the monster has moved already
     */
    public void move(boolean roundTwo) {
        if(!roundTwo){
            Point moveTo = findPath(maze.getPlayer().getX(), maze.getPlayer().getY());
            int moveX = (int) moveTo.getX();
            int moveY = (int) moveTo.getY();
            if((canGoTo(moveX, moveY)) && (!maze.hasMonsterAt(moveX, moveY))){
                x = moveX;
                y = moveY;
                hasMoved = true;
            }
        }
        else if(!hasMoved){
            Point moveTo = findPath(maze.getPlayer().getX(), maze.getPlayer().getY());
            int moveX = (int) moveTo.getX();
            int moveY = (int) moveTo.getY();
            if((canGoTo(moveX, moveY)) && (!maze.hasMonsterAt(moveX, moveY))){
                x = moveX;
                y = moveY;
            }
        }
        else{
            hasMoved = false;
        }
        return;
    }
    
    /**
     * Kill the monster.
     */
    public void kill(){
        isAlive = false;
        return;
    }
    
    /**
     * @return True if monster is alive, false otherwise
     */
    public boolean alive(){
        return isAlive;
    }
    
    /**
     * Pathfinding for monster.
     * @param x The X co-ordinate of where to find a path to
     * @param y The Y co-ordinate of where to find a path to
     * @return A Point with the X, Y space to move to
     */
    public Point findPath(int x, int y){
        int startX = x;
        int startY = y;
        int goalX = getX();
        int goalY = getY();
        int lastPlace = 0;
        ArrayList<int[]> temp = new ArrayList<int[]>();
        temp.add(new int[3]);
        temp.get(lastPlace)[0] = startX;
        temp.get(lastPlace)[1] = startY;
        temp.get(lastPlace)[2] = 0;
        ArrayList<int[]> toRemove = new ArrayList<int[]>();
        ArrayList<int[]> tempStore = new ArrayList<int[]>();
        
        while(true){
            if(!maze.hasWallAt(temp.get(lastPlace)[0] + 1, temp.get(lastPlace)[1])){
                tempStore.add(new int[3]);
                tempStore.get(tempStore.size() - 1)[0] = temp.get(lastPlace)[0] + 1;
                tempStore.get(tempStore.size() - 1)[1] = temp.get(lastPlace)[1];
                tempStore.get(tempStore.size() - 1)[2] = temp.get(lastPlace)[2] + 1;
            }
            if(!maze.hasWallAt(temp.get(lastPlace)[0] - 1, temp.get(lastPlace)[1])){
                tempStore.add(new int[3]);
                tempStore.get(tempStore.size() - 1)[0] = temp.get(lastPlace)[0] - 1;
                tempStore.get(tempStore.size() - 1)[1] = temp.get(lastPlace)[1];
                tempStore.get(tempStore.size() - 1)[2] = temp.get(lastPlace)[2] + 1;
            }
            if(!maze.hasWallAt(temp.get(lastPlace)[0], temp.get(lastPlace)[1] + 1)){
                tempStore.add(new int[3]);
                tempStore.get(tempStore.size() - 1)[0] = temp.get(lastPlace)[0];
                tempStore.get(tempStore.size() - 1)[1] = temp.get(lastPlace)[1] + 1;
                tempStore.get(tempStore.size() - 1)[2] = temp.get(lastPlace)[2] + 1;
            }
            if(!maze.hasWallAt(temp.get(lastPlace)[0], temp.get(lastPlace)[1] - 1)){
                tempStore.add(new int[3]);
                tempStore.get(tempStore.size() - 1)[0] = temp.get(lastPlace)[0];
                tempStore.get(tempStore.size() - 1)[1] = temp.get(lastPlace)[1] - 1;
                tempStore.get(tempStore.size() - 1)[2] = temp.get(lastPlace)[2] + 1;
            }
            for(int[] a : tempStore){
                for(int[] b : temp){
                    if((a[0] == b[0]) && (a[1] == b[1])){
                        if(a[2] < b[2]){
                            toRemove.add(b);
                        }
                        else{
                            toRemove.add(a);
                        }
                    }
                }
            }
            for(int[] c : toRemove){
                if(temp.contains(c)){
                    temp.remove(c);
                }
                else{
                    tempStore.remove(c);
                }
            }
            temp.addAll(tempStore);
            tempStore.clear();
            if((((temp.get(lastPlace)[0] == getX() + 1)) || (temp.get(lastPlace)[0] == getX() - 1)) && (temp.get(lastPlace)[1] == getY())){
                return new Point(temp.get(lastPlace)[0], getY());
            }
            else if((((temp.get(lastPlace)[1] == getY() + 1)) || (temp.get(lastPlace)[1] == getY() - 1)) && (temp.get(lastPlace)[0] == getX())){
                return new Point(getX(), temp.get(lastPlace)[1]);
            }
            else if((temp.get(lastPlace)[0] == goalX) && (temp.get(lastPlace)[1] == goalY)){
                break;
            }
            lastPlace += 1;
        }
        return new Point(getX(), getY());
    }
}
