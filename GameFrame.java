// Imports
import java.awt.*;
import javax.swing.*;

// Public Class GameFrame --> aka is the frame around the painted canvas
public class GameFrame extends JFrame {
	
	// Instance of GamePanel Class
	GamePanel panel;
	
	// Constructor Method GameFrame
    public GameFrame() {
    	
    	// Instantiating panel instance 
        panel = new GamePanel();
        
        // Adds panel
        this.add (panel);

        // sets title
        this.setTitle ("Pong Game");

        // Prevents resizing
        this.setResizable (false);

        // sets background color
        this.setBackground(Color.black);

        // Prompts user an x button which when pressed, will close the application
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prompts N/A to the size of Jframe and adjusts accordingly to accommodate the size of the game panel
        this.pack();

        // Prompts visibility
        this.setVisible(true);
        
        // sets the frame to appear in the middle of the screen
        this.setLocationRelativeTo(null);
        
        // Starts the music!
        BackgroundMusic.RunMusic("Music/Music.wav"); 
        
    } // End of Constructor

} // End of Public Class GameFrame

