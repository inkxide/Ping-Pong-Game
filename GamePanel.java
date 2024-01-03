// Imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

//Public Class GamePanel --> aka canvas on which the code is painted
public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	// Constant Variable Declarations
	// Dimensions of window
	public static final int GAME_WIDTH = 1000; // Constant variable for width of the game panel
	public static final int GAME_HEIGHT = 555; // Constant variable for height of the game panel
	
	// Size of Ball
	public static final int BALL_DIAMETER = 20; // // Constant variable for Ball Diameter
	
	// Dimensions of Paddles
	public static final int PADDLE_WIDTH = 20; // Constant Variable for Paddle width
	public static final int PADDLE_HEIGHT = 115; // Constant Variable for Paddle height
	
	public static boolean endgame; // Constant Variable for ending game
	
	// Instance of classes for implementing runnable interface
	Thread gameThread; // Thread gameThread for implementing runnable interface
	
	Image image; // Image image for implementing runnable interface
	
	Graphics graphics; // Graphics graphics for implementing runnable interface
	
	Random random; // Random random for implementing runnable interface
	
	LeftSlider leftPlayer1; // Paddle paddle1 for implementing runnable interface for PLAYER 1
	RightSlider rightPlayer2; // Paddle paddle2 for implementing runnable interface for PLAYER 2
	
	PlayerBall ball; // Ball ball for implementing runnable interface

	Score score; // Score score for implementing runnable interface
	
	// Constructor Method GamePanel
	public GamePanel() {
		
		// paddle method
		paddle();
		
		// ball Method
		ball();
		
		// score class equals new class
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		
		// Prompts so if any keys pressed, then its going to read key presses/keystrokes
		this.setFocusable(true); 
		
		// responds to keystrokes & Extends the keyAdapter class
		this.addKeyListener(this); 
		
		// sets screen's dimensions
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		
		// gameThread equals new Thread and passes this
		gameThread = new Thread(this);
		
		// starts gameThread
		gameThread.start();
	} // End of Constructor Method

	// paint Method
	public void paint(Graphics g) {
		
		// Creates image upon the called width and height method
		image = createImage(GAME_WIDTH, GAME_HEIGHT); 
		
		// graphics equals image and gets graphics
		graphics = image.getGraphics();
		
		// draws graphics
		draw(graphics);
		
		// ends graphics
		end(graphics);
		
		/* 
		 * Takes graphics g, draws image. Passes image, 
		 * coordinates x is 0 & y is 0 (top left corner) 
		 * and RJ panel called game panel.
		 */
		g.drawImage(image, 0, 0, this); 

	} // End of paint Method
	
	// newBall Method
	public void ball() {
		
		ball = new PlayerBall(GAME_WIDTH / 2, GAME_HEIGHT / 2); // creates a ball
	} // End of newBall Method

	// paddle Method
	public void paddle() {
		
		// Left-Slider/Paddle 1 
		leftPlayer1 = new LeftSlider (0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
		
		// Right-Slider Paddle 2
		rightPlayer2 = new RightSlider (GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
	} // End of paddle Method

	// Draw Method
	public void draw(Graphics g) {
		
		// Draws paddle 1 for player 1
		leftPlayer1.draw(g);
		
		// draws paddle 2 for player 2
		rightPlayer2.draw(g);
		
		// draws ball
		ball.draw(g);
		
		// draws score
		Score.draw(g);
	} // End of draw Method

	/*
	 * Calls the move method in other classes to update positions.
	 * This method is constantly called from run(). 
	 * By doing this, movements appear fluid and natural.
	 * If we take this out the movements appear sluggish and laggy.
	 */
	// move Method
	public void move() {
		
		// Makes Paddle1's (left-slider) movement smoother
		leftPlayer1.move();
		
		// Makes Paddle2's (right-slider) movement smoother
		rightPlayer2.move();
		
		// Makes ball's movement smoother
		ball.move();
	} // End of move Method

	// checkCollision Method which responds to all types of collisions
	public void checkCollision() {
		
		// Bounces ball off the top & window edges
		// if y positioning of the ball: ball.y is less than or equal to 0, then run
		if (ball.y <= 0) {
			
			ball.setYDirection(-ball.yChange);
		} // End of if
		
		// if y positioning of the ball: ball.y is greater than or equal to ball diameter subtracted from game height, then run
		if (ball.y > GAME_HEIGHT - BALL_DIAMETER) {
			
			ball.setYDirection(-ball.yChange);
		} // End of if
		
		// Stops Paddles at windows edges
		// if leftPlayer1.y is less than or equal to 0, then run the code
		if (leftPlayer1.y <= 0) {
			
			leftPlayer1.y = 0;
		} // End of if
		

		if (ball.x <= 0) {
			
			score.player2++;

			ball();
			paddle();
		} // End of if
		
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) 
		{
			score.player1++;
			paddle();
			ball();
		} // End of if
		
		if (leftPlayer1.y > GAME_HEIGHT - PADDLE_HEIGHT) {
			
			leftPlayer1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		} // End of if
		
		if (rightPlayer2.y < 0) {
			
			rightPlayer2.y = 0;
		} // End of if
		
		if (rightPlayer2.y > GAME_HEIGHT - PADDLE_HEIGHT) {
			
			rightPlayer2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}// End of if
		
		
		// Bounces ball of the paddles
		// if ball intersects right-slider then bounce right
		// if statement for comparing these 2 objects, to check if there is any collision between them
		if (ball.intersects(leftPlayer1)) {
			
			ball.xChange = Math.abs(ball.xChange);
			ball.xChange++;
			ball.setXDirection(ball.xChange);
			ball.setYDirection(ball.yChange);
		} // End of if
		
		// if ball intersects right-slider then bounce left
		// if statement for comparing these 2 objects, to check if there is any collision between them
		if (ball.intersects(rightPlayer2)) {
			
			ball.xChange = Math.abs(ball.xChange);
			ball.xChange++;
			ball.setXDirection(-ball.xChange);
			ball.setYDirection(ball.yChange);
		} // End of if
	} // End of checkCollision method

	// end Method
	public void end(Graphics g) {
		
		// if value of score.player1 equals 7, then run and end the game
		if (score.player1 == 7) {
			
			ball.xChange = 0;
			ball.yChange = 0;
			endmessage(g);
		} // End of if
		
		// if value of score.player2 equals 7, then run and end the game
		if (score.player2 == 7) {
			
			ball.xChange = 0;
			ball.yChange = 0;
			endmessage(g);
		} // End of if
	} // End of end method
	
	// endmessage method
	public void endmessage(Graphics g) {
		
		// if endgame equals true, then run
		if (endgame = true) {
			
			g.setColor(Color.yellow); // sets color to yellow
			g.setFont(new Font("BoldPrice", Font.ROMAN_BASELINE, 60)); // sets font style and font size
			g.drawString("GAME OVER!", 315, 200); // draws "GAME OVER!" and sets coordinates
			
			// If score.player1 value is greater than score.player2, then run
			if (score.player1 > score.player2) {
				
				g.setColor(Color.red); // sets color to red
				g.setFont(new Font("BoldPrice", Font.ROMAN_BASELINE, 50)); // sets font style and font size
				g.drawString("Player 1 won :)", 100, 400); // draws "Player 1 won :)" and sets coordinates
			
				g.setColor(Color.blue); // sets color to blue
				g.setFont(new Font("BoldPrice", Font.ROMAN_BASELINE, 50)); // sets font style and font size
				g.drawString("Player 2 lost :(", 600, 400); // draws "Player 2 lost :(" and sets coordinates
			} // End of if 
			
			// If Score.player2 value is greater than score.player1, then run
			if (Score.player2 > score.player1) {
			
				g.setColor(Color.red); // sets color to red
				g.setFont(new Font("BoldPrice", Font.ROMAN_BASELINE, 50)); // sets font style and font size
				g.drawString("Player 1 lost :(", 100, 400);
			
				g.setColor(Color.blue);
				g.setFont(new Font("BoldPrice", Font.ROMAN_BASELINE, 50)); // sets font style and font size
				g.drawString("Player 2 won :)", 600, 400); 

			} // End of if
		} // End of if
	} // End of endmessage method

	/* 
	 * run method which makes the game run without an end. 
	 * It calls other methods to move objects.
	 * Checks for collisions, and updates the screen.
	 */ 
	public void run() {
		
		/* 
		 * CPU runs game code quickly, to slow it down! 
		 * The following lines of code "force" the computer to be stuck in a loop for short
		 * intervals between calling other methods to update the screen.
		 */ 
		// Game Loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60; // amountOfTicks for 60 fps
		double ns = 1000000000 / amountOfTicks; // double variable ns  for storing the value of 1 billion divided by amountOfTick's value
		double delta = 0; // double variable delta
		long now;
		
		// while loop for when the game is running
		while (true) {
			
			now = System.nanoTime();
			delta = delta + (now - lastTime) / ns;
			lastTime = now;
			
			// if statement for when delta equals 1, then run the code below
			if (delta >= 1) {
		  
				move();
				checkCollision();
				repaint();
				delta--;
			} // End of if
		} // End of while loop
	} // End of run Method
	
	// keyPressed Method
	public void keyPressed(KeyEvent e) {
		
		leftPlayer1.keyPressed(e);
		rightPlayer2.keyPressed(e);
	} // End of keyPressed Method

	/* When a key is released, it'll send it over to the 
	 * PlayerBall class to be processed
	 */ 
	// keyReleased Method
	public void keyReleased(KeyEvent e) {
		
		leftPlayer1.keyReleased(e);
		rightPlayer2.keyReleased(e);
	} // End of keyReleased Method

	/*
	 *  Left empty because we don't need it, it must be here because it is required to
	 *  be overided by the KeyListener interface.
	 */
	// keyTyped method
	public void keyTyped(KeyEvent e) 
	{
	} // End of keyTyped method

} // End of Public Class
