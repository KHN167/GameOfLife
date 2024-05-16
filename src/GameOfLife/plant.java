package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class plant extends lifeForm implements omniEdible{

    private static final long serialVersionUID = 4426729960439708794L;
    public static final int red = 0;
    public static final int green = 225;
    public static final int blue = 0;
    
    
    /*
     * gives the cell position, and makes move taken = false;
     */
    public plant(Cell position) {
        super(position, red, green, blue);
    }
    
    







    protected void spawn() {
        Cell local = cell.randomEmptyCell();
        plant plantLife = new plant(local);
        plantLife.init();
        plantLife.moveTaken = true;
        plantLife.revalidate();
        
        
    }







    protected boolean checkNeighbours() {
        int numOfPlant = 0;
        int empty = 0;
        
        final Cell[][] cells = cell.AdjacentCells();
        
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if (cells[row][col] != null) { 
                    if(cells[row][col].getLifeform() == null) {
                        empty++;
                    } else if (cells[row][col].getLifeform() instanceof plant) {
                        numOfPlant++;
                    }  
                }  
            }
        }
        if(empty >= 3 && numOfPlant >= 2) {
            return true;
        } else {
            return false;
        }
    }
    
    
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
        }
        moveTaken = true;
        
    }


}
