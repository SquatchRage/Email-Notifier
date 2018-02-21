
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class Sound  {
	
	public static void playSound()
	{
		
	
	      try {
	    	  String waveName = "hs3words.wav";    
	    	  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(waveName).getAbsoluteFile());
	    	  Clip clip = AudioSystem.getClip();
	    	  clip.open(audioInputStream);
	    	  clip.start();
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
}