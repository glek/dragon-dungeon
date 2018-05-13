import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;

/**
 * Write a description of class Maze here.
 * 
 * @author Michael Damian Mulligan
 * @version 01/03/2011
 */
public class Maze
{
    /**
     * Size of SIZE x SIZE maze
     */
    public static int SIZE = 10;
    
    /**
     * Time during which there has been fewer then the max number of monsters
     */
    protected int timeWithout;
    
    /**
     * Reference to the player
     */
    protected Player p;
    
    /**
     * The maze itself as a grid of Items
     */
    protected Item[][] grid;
    
    /**
     * A list of monsters
     */
    protected ArrayList<Monster> monsters;

    /**
     * Constructor for objects of class Maze
     * @param diff The games difficulty where 1 is easy, 2 is medium, 3 is hard, etc
     */
    public Maze(int diff)
    {
        SIZE = 5 * diff + 5;
        grid = new Item[SIZE][SIZE];
        monsters = new ArrayList<Monster>();
        makeWalls();
        timeWithout = 0;
    }
    
    /**
     * @return A reference to the current Player object
     */
    public Player getPlayer(){
        return p;
    }
    
    /**
     * @param i The x position to check
     * @param j The y position to check
     * @return True if a monster, false otherwise
     */
    public boolean hasMonsterAt(int i, int j){
        for(Monster m : monsters){
            if((m.getY() == j) && (m.getX() == i)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * @param i The x position to check
     * @param j The y position to check
     * @return True if a wall, false otherwise
     */
    public boolean hasWallAt(int i, int j){
        if((i < SIZE) && (i >= 0) && (j < SIZE) && (j >= 0)){
            if(grid[j][i] instanceof Wall){
                return true;
            }
        }
        return false;
    }
    /**
     * @return ArrayList of monsters in the game.
     */
    public ArrayList<Monster> getMonsters(){
        return monsters;
    }
    
    /**
     * @return True if a monster is on the same space as the player, false otherwise
     */
    public boolean hasLost(){
        for(Monster m : monsters){
            if((m.getY() == p.getY()) && (m.getX() == p.getX())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * @return False always, define win conditions in subclass!
     */
    public boolean hasWon(){
        return false;
    }
    
    /**
     * Take the grid and turn it to a string
     * @return String representation of grid
     */
    @Override
    public String toString(){
       String output = "";
       for(int i = 0; i < grid.length; i++){
           for(int x = 0; x < grid[i].length; x++){
               for(Monster m : monsters){
                   if((m.getX() == x) && (m.getY() == i)){
                       output += "M";
                       continue;
                   }
                   continue;
               }
               if((i == p.getY()) && (x == p.getX())){
                   output += "P";
                   continue;
               }
               if(grid[i][x] instanceof Wall){
                   output+= "W";
                   continue;
               }
               if(grid[i][x] == null){
                   output+= ".";
               }
               output += "\n";
           }
       }
       return output;
    }
    
    /**
     * Figure out the new game state.
     * Move monsters and remove those who are dead.
     */
    public void resolve(){
        ArrayList<Monster> deadMonster = new ArrayList<Monster>();
        for(Monster m : monsters){
            if(m.alive()){
                m.move(false);
            }
            else{
                deadMonster.add(m);
            }
        }
        for(Monster d : deadMonster){
            monsters.remove(monsters.indexOf(d));
        }
        for(Monster m : monsters){
            m.move(true);
        }
        if(monsters.size() < (SIZE / 2 - 1)){
            timeWithout += 1;
        }
        if(timeWithout > 5){
            timeWithout = 0;
            placeMonsters((SIZE / 2 - 1) - monsters.size());
        }
        return;
    }
    
    /**
     * Populate the dungeon with monsters
     * @param amount The number of monsters to add
     */
    public void placeMonsters(int amount){
        int tempAmount = amount;
        java.util.Random rand = new java.util.Random();
        for(int y = SIZE - 1; y >= 0; y--){
            for(int x = SIZE - 1; x >= 0; x--){
                if(rand.nextInt(10) % 5 == 0){
                    if((tempAmount > 0) && (!hasWallAt(x, y)) && (x != p.getX()) && (y != p.getY())){
                        addMonster(new Monster(x, y, this));
                        tempAmount--;
                    }
                }
            }
        }
        return;
    }
    
    /**
     * Add a monster to the maze.
     * @param m The monster to add
     */
    public void addMonster(Monster m){
        if(!hasWallAt(m.getX(), m.getY())){
            monsters.add(m);
        }
        return;
    }
    
    /**
     * Print the grid with M as monster, P as player, W as wall, and . as nothing
     */
    public void print(){
        System.out.println(this.toString());
    }
    
    /**
     * @param x The x position of the range to check
     * @param y The y position of the range to check
     * @return True if the value is within the game grid
     */
    public boolean validRange(int x, int y){
        if((x < SIZE) && (x >= 0)){
            if((y < SIZE) && (y >= 0)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Setup the walls for a game using Prim's algorithm.
     */
    public void makeWalls(){
        Item[][] tempgrid = new Item[SIZE][SIZE];
        Random tester = new Random();
        ArrayList<Item> walls = new ArrayList<Item>();
        int selectedWall = 0;
        Wall currentWall = null;
        int selectedX = 0;
        int selectedY = 0;
        
        //Fill the grid with walls
        for(int y = 0; y < SIZE; y++){
            for(int x = 0; x < SIZE; x++){
                tempgrid[y][x] = new Wall(x, y);
            }
        }
        
        //We start the maze here
        int startX = 1;
        int startY = 1;
        
        //Take surrounding walls
        tempgrid[startY][startX] = null;
        if((startX - 1 >= 0) && (tempgrid[startY][startX - 1] instanceof Wall)){
            walls.add(tempgrid[startY][startX - 1]);
        }
        if((startX + 1 < SIZE) && (tempgrid[startY][startX + 1] instanceof Wall)){
            walls.add(tempgrid[startY][startX + 1]);
        }
        if((startY - 1 >= 0) && (tempgrid[startY - 1][startX] instanceof Wall)){
            walls.add(tempgrid[startY - 1][startX]);
        }
        if((startY + 1 < SIZE) && (tempgrid[startY + 1][startX] instanceof Wall)){
            walls.add(tempgrid[startY + 1][startX]);
        }
        
        //While there's space to make more of the maze
        while(!walls.isEmpty()){
            selectedWall = tester.nextInt(walls.size());
            currentWall = (Wall) walls.get(selectedWall);
            walls.remove(selectedWall);
            selectedY = currentWall.getY();
            selectedX = currentWall.getX();
            
            if((validRange(selectedX - 1, selectedY)) && (tempgrid[selectedY][selectedX - 1] == null)){
                if((validRange(selectedX + 1, selectedY)) && (validRange(selectedX, selectedY + 1)) && (validRange(selectedX, selectedY - 1)) && (tempgrid[selectedY][selectedX + 1] instanceof Wall) && (tempgrid[selectedY + 1][selectedX] instanceof Wall) && (tempgrid[selectedY - 1][selectedX] instanceof Wall)){
                    tempgrid[selectedY][selectedX] = null;
                    if((validRange(selectedX + 1, selectedY)) && (tempgrid[selectedY][selectedX + 1] instanceof Wall)){
                        walls.add(tempgrid[selectedY][selectedX + 1]);
                    }
                    if((validRange(selectedX, selectedY - 1)) && (tempgrid[selectedY - 1][selectedX] instanceof Wall)){
                        walls.add(tempgrid[selectedY - 1][selectedX]);
                    }
                    if((validRange(selectedX, selectedY + 1)) && (tempgrid[selectedY + 1][selectedX] instanceof Wall)){
                        walls.add(tempgrid[selectedY + 1][selectedX]);
                    }
                    continue;
                }
            }
            
            if((validRange(selectedX + 1, selectedY)) && (tempgrid[selectedY][selectedX + 1] == null)){
                if((validRange(selectedX - 1, selectedY)) && (validRange(selectedX, selectedY + 1)) && (validRange(selectedX, selectedY - 1)) && (tempgrid[selectedY][selectedX - 1] instanceof Wall) && (tempgrid[selectedY + 1][selectedX] instanceof Wall) && (tempgrid[selectedY - 1][selectedX] instanceof Wall)){
                    tempgrid[selectedY][selectedX] = null;
                    if((validRange(selectedX + 1, selectedY)) && (tempgrid[selectedY][selectedX + 1] instanceof Wall)){
                        walls.add(tempgrid[selectedY][selectedX + 1]);
                    }
                    if((validRange(selectedX, selectedY - 1)) && (tempgrid[selectedY - 1][selectedX] instanceof Wall)){
                        walls.add(tempgrid[selectedY - 1][selectedX]);
                    }
                    if((validRange(selectedX, selectedY + 1)) && (tempgrid[selectedY + 1][selectedX] instanceof Wall)){
                        walls.add(tempgrid[selectedY + 1][selectedX]);
                    }
                    continue;
                }
            }
            
            if((validRange(selectedX, selectedY - 1)) && (tempgrid[selectedY - 1][selectedX] == null)){
                if((validRange(selectedX, selectedY + 1)) && (validRange(selectedX + 1, selectedY)) && (validRange(selectedX - 1, selectedY)) && (tempgrid[selectedY + 1][selectedX] instanceof Wall) && (tempgrid[selectedY][selectedX + 1] instanceof Wall) && (tempgrid[selectedY][selectedX - 1] instanceof Wall)){
                    tempgrid[selectedY][selectedX] = null;
                    if((validRange(selectedX + 1, selectedY)) && (tempgrid[selectedY][selectedX + 1] instanceof Wall)){
                        walls.add(tempgrid[selectedY][selectedX + 1]);
                    }
                    if((validRange(selectedX - 1, selectedY)) && (tempgrid[selectedY][selectedX - 1] instanceof Wall)){
                        walls.add(tempgrid[selectedY][selectedX - 1]);
                    }
                    if((validRange(selectedX, selectedY + 1)) && (tempgrid[selectedY + 1][selectedX] instanceof Wall)){
                        walls.add(tempgrid[selectedY + 1][selectedX]);
                    }
                    continue;
                }
            }
            
            if((validRange(selectedX, selectedY + 1)) && (tempgrid[selectedY + 1][selectedX] == null)){
                if((validRange(selectedX, selectedY - 1)) && (validRange(selectedX + 1, selectedY)) && (validRange(selectedX - 1, selectedY)) && (tempgrid[selectedY - 1][selectedX] instanceof Wall) && (tempgrid[selectedY][selectedX + 1] instanceof Wall) && (tempgrid[selectedY][selectedX - 1] instanceof Wall)){
                    tempgrid[selectedY][selectedX] = null;
                    if((validRange(selectedX + 1, selectedY)) && (tempgrid[selectedY][selectedX + 1] instanceof Wall)){
                        walls.add(tempgrid[selectedY][selectedX + 1]);
                    }
                    if((validRange(selectedX - 1, selectedY)) && (tempgrid[selectedY][selectedX - 1] instanceof Wall)){
                        walls.add(tempgrid[selectedY][selectedX - 1]);
                    }
                    if((validRange(selectedX, selectedY + 1)) && (tempgrid[selectedY + 1][selectedX] instanceof Wall)){
                        walls.add(tempgrid[selectedY + 1][selectedX]);
                    }
                    continue;
                }
            }
        }
        
        for(int j = 0; j < SIZE; j++){
            for(int i = 0; i < SIZE; i++){
                grid[j][i] = tempgrid[j][i];
            }
        }
        return;
    }
}
