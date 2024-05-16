package GameOfLife;

import java.awt.GridLayout;
import javax.swing.JFrame;




public class GameFrame extends JFrame{
    
    
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -8245623141045202000L;
    /**
     * 
     */

    private final World world;
    

    
    public GameFrame(final World obj) {
        world = obj;
        
        
    }
    

    /**
     * initializes the frame.
     */
    public void init() {
        setTitle("Game of Life");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));
        
        for(int row = 0; row < world.getRowCount(); row++) {
            for(int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row,col));
            }
        }
        
    
    }

    
    
    public void turn() {
        world.turn();
        repaint();
        validate();
        
    }
    
 


  

   






 
    


   

  

   
}
