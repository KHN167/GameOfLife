package GameOfLife;

import java.awt.Point;



public class carnivore extends lifeForm implements omniEdible{
    /**
     * 
     */
    private Cell cell;
    private static int red = 255;
    private static int green = 0;
    private static int blue = 0;
    

    
    
    public carnivore(Cell position) {
        super(position,red,green,blue);
        
    }

    
    public void turn() {
        if (!moveTaken) {
            if (hungerBar == 5) {
                die();
            } else {
                hungerBar++;
                if (checkNeighbours()) {
                    spawn();
                }
                makeAMove();
            }
            moveTaken = true;
        }
        
 
    }

    private void makeAMove() {
        Cell[][] cells = cell.AdjacentCells();
        boolean moved = false;
        int stuck = 0;
        
        while (!moved) {
            Point point = getDirection();
            int y1 = (int)point.getY();
            int x1 = (int)point.getX();
                       
            if (cells[y1][x1] != null) {
                lifeForm inhabitant = cells[y1][x1].getLifeform(); 
                if (inhabitant == null || inhabitant instanceof herbivore) {
                    if (inhabitant instanceof herbivore) { 
                        eats(cells[y1][x1]);
                    }
                    removeCell(cell);
                    setCell(cells[y1][x1]);
                    moved = true;
                } 
            } 
            //if it is surrounded by impassable objects or cannot 
            //find a valid path after specified tries, give up
            moved = (stuck == 5);
            stuck++;
        }
    }


    protected void spawn() {
        Cell local = cell.randomEmptyCell();
        carnivore carnLife = new carnivore(local);
        carnLife.init();
        carnLife.moveTaken = true;
        carnLife.revalidate();
        
        
    }


    protected boolean checkNeighbours() {
        int carni = 0;
        int empty = 0;
        int food = 0;
        final Cell[][] cells = cell.AdjacentCells();
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col] != null) {
                    if (cells[row][col].getLifeform() == null) {
                        empty++;
                    } else if (cells[row][col].getLifeform() 
                                instanceof carnivore) {
                        carni++;
                    } else if (cells[row][col].getLifeform() 
                                instanceof herbivore) {
                        food++;
                    }
                }
            }
        }
        if (carni >= 1 && empty >= 3 && food >= 2) {
            return true;
        } else {
            return false;
        }        
    }


   
}

    


