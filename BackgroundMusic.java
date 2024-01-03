// Imports
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// Public class BackgroundMusic
public class BackgroundMusic {
	
	// runMusic method
	public static void RunMusic (String path) {
		
		// try for music
		try {
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream (new File (path));
			Clip clip = AudioSystem.getClip();
			clip.open (inputStream);
			clip.loop(0);
		} // end of try
		
		// catch for UnsupportedAudioFileException
		catch (UnsupportedAudioFileException e) {
			
			e.printStackTrace();
		} // End of catch
		
		// catch for IOException
		catch (IOException e) {
			
			e.printStackTrace();
		} // End of catch
		
		// catch for LineUnavailableException
		catch (LineUnavailableException e) {
			
			e.printStackTrace();
		} // End of catch
	} // End of runMusic
} // End of Public Class
