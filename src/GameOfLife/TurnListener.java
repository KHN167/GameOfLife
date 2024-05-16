package GameOfLife;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TurnListener extends MouseAdapter{
    private final GameFrame gameframe;
    
    /**
     * Constructor for objects of type TurnListener.
     * 
     * @param frame the GameFrame to interact with.
     */
    public TurnListener(GameFrame frame) {
        if (frame == null) {
            throw new IllegalArgumentException(
                    "Parameter cannot be null");
        }
        gameframe = frame;
    }
    
    /**
     * Moves time within the World by one turn with each mouse click.
     * @param click the mouse click event to listen to.
     */
    public void mouseClicked(MouseEvent click) {
        gameframe.turn();
    }
}
