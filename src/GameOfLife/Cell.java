package GameOfLife;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;



public class Cell extends JPanel{
    /**
     * 
     */
    private final World world;
    private lifeForm life;
    private Cell[][] cell;
    private final int rows;
    private final int cols;
    
    
   
    /**
     * 
     * @param world is the world that cells are placed on
     * @param row is the position of the row for the cell
     * @param col is the position of the col for the cell
     */
    public Cell(World world, int row, int col) {
        this.world = world;
        this.rows = row;
        this.cols = col;
        cell = new Cell[3][3];
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    /**
     * initializes cell and place the lifeform on the cell randomly.
     */
    public void init() {
        int num = RandomGenerator.nextNumber(100);
        

        if (num >= 80) {
            life = new herbivore(this);
            life.init();
        } else if (num >= 60) {
            life = new plant(this);
            life.init();
        }else if(num >= 50) {
            life = new carnivore(this);
            life.init();
        }else if(num >= 45) {
            life = new omnivore(this);
            life.init();
        }
        else {
            life = null;
        }
        cell = world.AdjacentCells(this);
        
    }
    
    

    
    
   

 
    
   
  
    
    /**
     * colors the background of the frame.
     */
    public void paintComponent(Graphics draw) {
        super.paintComponent(draw);
        draw.setColor(new Color(255,255,255));
        draw.fillRect(0, 0, getWidth(), getHeight());
        repaint();
        revalidate();
    }
    
   
    /**
     * 
     * @return the type of life form.
     */
    public lifeForm getLifeform() {
        return life;
    }
    
   

    /**
     * sets the lifeform to the cell.
     * @param lifes
     */
    public void setLifeFormOnMap(lifeForm object) {
        if(life == null) {
            life = object;  
        }    
    }
    
    
    public Cell getEmptyCell() {
        ArrayList<Cell> cellList = new ArrayList<Cell>();
        Random randomNum = new Random();
        int seed;
        Cell empty;

        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(cell[row][col] != null) {
                    if(cell[row][col].getLifeform() == null) {
                        cellList.add(cell[row][col]);
                        
                    }
                }
            }
        }
        if(cellList.size() == 0) {
            return null;
        } else {
            seed = randomNum.nextInt(cellList.size());
            empty = cellList.get(seed);
            return empty;
        }
    }
    
    
    
    
    /**
     * removes the life form from the grid.
     * @param lifes 
     */
    public void removeLifeFormOnMap(lifeForm object) {
        if(life != null) {
            life = null;
        }
    }
    
    /**
     * returns the location of the cell as a Point object.
     */
    public Point getLocation() {
        Point location = new Point(cols, rows);
        return (location);
    }
    
    
    /**
     * 
     * @return all adjacent cells in a 2d array.
     */
    public Cell[][] AdjacentCells() {
        return cell;
    }
    
    
    
    public Cell randomEmptyCell() {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        Random gen = new Random();
        Cell emptyCell;
        int randomNum;
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(cell[i][j] != null) {
                    if(cell[i][j].getLifeform() == null) {
                        cells.add(cell[i][j]);
                    }
                }
            }
        }
        if(cells.size() == 0) {
            return null;
        } else {
            randomNum = gen.nextInt(cells.size());
            emptyCell = cells.get(randomNum);
            return emptyCell;
        }
    }
    
    
    
    
  

    public void turn() {
        if(life != null) {
            life.turn();
        }
        repaint();
        revalidate();
    }
    

    /**
     * resets the turns for herbivore class and plant class.
     */
    public void resetTurn() {
        if(life != null) {
            life.resetTurn();
        }
    }
    

    

  








    
}
