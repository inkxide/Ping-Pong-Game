// Imports
import java.awt.*;

//Public Class Score
public class Score extends Rectangle {
	
	// Variable Delarations
    public static int GAME_WIDTH;
    public static int GAME_HEIGHT;
    
    public static int score = 0;
    
    public static int player1; // int variable player1 for holding the current score of player 1
    public static int player2; // int variable player1 for holding the current score of player 2
    
    // Constructor Method Score
    public Score(int GAME_WIDTH, int GAME_LENGTH) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_LENGTH;
    } // End of Constructor Method


    // draw Method
 	public static void draw (Graphics g) {
 		
 		Graphics2D g2d = (Graphics2D) g; // g2d variable for Graphics2D
 		
 		g.setColor(Color.white); // sets score color to white
 		// Dashed Line in the Middle
 		Stroke dashed = new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0);
         g2d.setStroke(dashed); // sets stroke type to dashed
         g2d.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT); // creates the dashed line
 		
  		g.setFont(new Font("Consolas", Font.PLAIN, 60)); // sets font style
  		
  		g.setColor(Color.red); // sets score color to red
 		g.drawString(String.valueOf(player1), GAME_WIDTH/2 - 85, 50); // creates score for player 1
 		
 		g.setColor(Color.blue); // sets score color to blue
 		g.drawString(String.valueOf(player2), GAME_WIDTH/2 + 50, 50); // creates score for player 2
 		
 		g.setColor(Color.orange); // sets score color to orange
 		g.setFont(new Font("Helvetica", Font.PLAIN, 20)); // sets font style
 		
 		// if player1 score equals 0 , then keep on running
 		if (player1 == 0) {
 			
            g.drawString("Press W to move up", 136, 100); // draws "Press W to move up" at the coordinates
            g.drawString("Press S to move down", 130, 120); // draws "Press S to move down" at the coordinates
            
            g.drawString("Player 1 use your controls to move your slider.", 50, 420); // draws "Player 1 use your controls to move your slider." at the coordinates
        	g.drawString("Score on other side & reach 7 points to win.", 55, 450); // draws "Score on other side & reach 7 points to win." at the coordinates
        } // End of if
 		
 		// if player1 score equals 0 , then keep on running
        if (player2 == 0) {
        	
            g.drawString("Press Up key to move up", 657, 100); // draws "Press Up key to move up" at the coordinates
            g.drawString("Press Down key to move down", 640, 120); // draws "Press Down key to move down" at the coordinates
            
            g.drawString("Player 2 use your controls to move your slider.", 550, 420); // draws "Player 2 use your controls to move your slider." at the coordinates
        	g.drawString("Score on other side & reach 7 points to win.", 555, 450); // draws "Score on other side & reach 7 points to win." at the coordinates
        } // End of if
 	} // End of draw Method
 } // End of Public Class

