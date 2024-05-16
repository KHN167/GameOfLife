package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class omnivore extends lifeForm{
    /**
     * 
     */
    private static final long serialVersionUID = -5757237534287066766L;
    private static int red = 0;
    private static int blue = 255;
    private static int green = 0;
    private static int hungerBar = 0;
    

    private Cell cell;
    
    
    public omnivore(Cell position) {
        super(position,red,green,blue);
    }
    
    
    private void makeAMove() {
        Cell[][] cells = cell.AdjacentCells();
        boolean moveTake = false;
        
        while(!moveTake) {
            Point point = getDirection();
            int x = (int)point.getX();
            int y = (int)point.getY();
            
            if(cells[y][x] != null) {
                lifeForm life = cells[y][x].getLifeform();
                if(life instanceof omniEdible || life == null) {
                    if(life instanceof omniEdible) {
                        eats(cells[y][x]);
                        
                    }
                    removeCell(cell);
                    setCell(cells[y][x]);
                    moveTake = true;
                    
                }
            }
            
        }
        
      
        
    }
    
    
    protected boolean checkNeighbours() {
        int empty = 0;
        int food = 0;
        int numOfOmni = 0;
        final Cell[][] cells = cell.AdjacentCells();
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(cells[i][j].getLifeform() == null) {
                    empty++;
                } else if (cells[i][j].getLifeform() instanceof omnivore) {
                    numOfOmni++;
                } else if(cells[i][j].getLifeform() instanceof omniEdible) {
                    food++;
                }
            }
        }
        if(numOfOmni > 1 && empty >= 3 && food >= 3) {
            return true;
            
        } else {
            return false;
        }
    }
   


    protected void spawn() {
        Cell local = cell.randomEmptyCell();
        omnivore omni = new omnivore(local);
        omni.init();
        omni.moveTaken = true;
        omni.revalidate();
    }

   
 

    protected void turn() {
        
        
        if(!moveTaken) {
            if(hungerBar == 5) {
                die();
            }
        } else {
            hungerBar++;
            
            if(checkNeighbours()) {
                spawn();
            }
            makeAMove();
        }
        moveTaken = true;
        
    }


   
    

}
