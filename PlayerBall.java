// Imports
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class PlayerBall extends Rectangle {
	
	// Instance of the random Class
    Random r = new Random();
    
    public int yChange; // int variable for how fast the ball will move on the x-axis
    public int xChange; // int variable for how fast the ball will move on the y-axis

    public int InitialSpeed = 2; // int variable for the initial speed of the ball
    
    public static final int BALL_DIAMETER = 20; // Constant Variable for size of the ball
    
    // Instance of classes for implementing runnable interface
    public BufferedImage ball; 
    public String direction;


    // constructor creates ball at given location with given dimensions
    public PlayerBall(int x, int y) {
    	
        super(x, y, BALL_DIAMETER, BALL_DIAMETER);
        
        // variable for randomXDirection. if it's 0, then ball go left and if it's 1, then ball go right
        int randomXDirection = r.nextInt(2);
        // if statement for moving the ball randomly on x-axis
        if (randomXDirection == 0) {
        	
            randomXDirection--;
        } // End of if 
        
        setXDirection(randomXDirection * InitialSpeed);
        
        // variable for randomYDirection. if it's 0, then ball go left and if it's 1, then ball go right
        int randomYDirection = r.nextInt(2);
        if (randomYDirection == 0) {
        	
            randomYDirection--;
        } // End of if
        
        setYDirection(randomYDirection * InitialSpeed);
    } // End of Constructor Method

    
    // setYDirection Method which is called whenever the direction of the ball changes in the y-axis (up or down)
    public void setYDirection(int yDirection) {
    	
        yChange = yDirection;
    } // End of setXDirection Method

    // setXDirection Method which is called whenever the direction of the ball changes in the x-axis (left or right)
    public void setXDirection(int xDirection) {
        xChange = xDirection;
    } // End of setYDirection Method

    /*
     * move Method called frequently from both PlayerBall class and GamePanel class.
     * Updates the current location of the ball.
     */
    public void move() {
    	
        y += yChange;
        x += xChange;
    } // End of move Method

    /*
     * draw method which is called frequently from the GamePanel class
     * Draws the current location of the ball to the screen
     */
    public void draw(Graphics g) {
    	
        g.setColor(Color.WHITE);
        g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
    } // End of draw Method 
} // End of Public Class
