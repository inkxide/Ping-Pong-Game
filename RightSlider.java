// Imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class RightSlider extends Rectangle {

	// Variable Declarations
    private int speed = 10; // Variable for right-slider speed
    public int yChange; // Variable for velocity
    
 // Constructor Method RightSlider
    public RightSlider (int x, int y, int PADDLE_HEIGHT, int PADDLE_WIDTH) {
        super(x, y, PADDLE_HEIGHT, PADDLE_WIDTH);
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
        	
            setYDirection(-speed);
            move();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        	
            setYDirection(speed);
            move();
        }
    }

    // called from GamePanel when any key is released (no longer being pressed down)
    // Makes the ball stop moving in that direction
    public void keyReleased(KeyEvent e) {
    	
        if (e.getKeyCode() == KeyEvent.VK_UP) {
        	
            setYDirection(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        	
            setYDirection(0);
        }
    }

    // called whenever the movement of the ball changes in the y-direction (up/down)
    public void setYDirection(int yDirection) {
        yChange = yDirection;
    }

    public void move() {
    	
        y = y + yChange;
    }

    public void draw(Graphics g) {
    	
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
} // End of RightSlider Class
