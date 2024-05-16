package GameOfLife;

import java.awt.Point;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class herbivore extends lifeForm implements omniEdible{

    private Cell cell;
    private boolean moveTaken;
    public static int red = 225;
    public static int green = 225;
    public static int blue = 0;
    
    

    public herbivore (Cell position) {
        super(position,red,green,blue);
        hungerBar = 0;
       
    }


    private void makeAMove() {
        boolean moveTake = false;
        int stuck = 0;
        Cell[][] cells = cell.AdjacentCells();
        
        while (!moveTake) {
            Point point = getDirection();
            int row = (int)point.getY();
            int col = (int)point.getX();
                       
            if (cells[row][col] != null) {
                lifeForm life = cells[row][col].getLifeform(); 
                if (life == null || life instanceof plant) {
                    if (life instanceof plant) { 
                        eats(cells[row][col]);
                    }
                    removeCell(cell);
                    setCell(cells[row][col]);
                    moveTake = true;
                } 
            } else if (stuck == 8) {  
                moveTake = true;
            }
            stuck++;
        }
        
    }
    
    






    /*
     * if hunger = 5 then the entity dies otherwise hunger goes up and makes a move.
     */

    public void turn() {
        
        if (!moveTaken) {
            if (hungerBar == 5) {
                die();
            } else {
                hungerBar++; 
                if(checkNeighbours()) {
                    spawn();
                }
            }
            makeAMove();
        }
        moveTaken = true;
        
    }











    protected void spawn() {
        Cell local = cell.randomEmptyCell();
        herbivore herb = new herbivore(local);
        herb.init();
        herb.moveTaken = true;
        herb.revalidate();
    }









    protected boolean checkNeighbours() {
        int row;
        int col;
        int numOfHerb = 0;
        int empty = 0;
        int food = 0;
        
        final Cell[][] cells = cell.AdjacentCells();
        
        for(row = 0; row < 3; row++) {
            for(col = 0; col < 3; col++) {
                if(cells[row][col].getLifeform() == null) {
                    empty++;
                } else if(cells[row][col].getLifeform() instanceof herbivore) {
                    numOfHerb++;
                } else if(cells[row][col].getLifeform() instanceof plant) {
                    food++;
                }
                
            }
            
        }
        if(empty >= 2 && food >= 2 && numOfHerb >= 1) {
            return true;
        } else {
            return false;
        }
    }

}

    
    
    
    

