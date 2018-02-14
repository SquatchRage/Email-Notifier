
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class Sound implements LineListener {
	
	static Clip	audioClip;
	static AudioInputStream	audioInputStream;
	static URL	url;
	static boolean	stillPlaying;
	
	public  void makeASound(){
		try{
		url = this.getClass().getClassLoader().getResource("uh_oh.wav");
		audioInputStream = AudioSystem.getAudioInputStream(url);
		try {
			audioClip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		audioClip.addLineListener(this);
		audioClip.start();
		}
		catch(UnsupportedAudioFileException uafe){
			
			System.out.println("Unsupported File Type");
		}
		catch(IOException ioe){
			System.out.println("Line unavailable");
		}
		
	}

	
	public void update(LineEvent event){
		
		System.out.println("Got a LineEvent...");
		if(event.getType() == LineEvent.Type.START)
			System.out.println("Got a START LineEvent");
		else if(event.getType() == LineEvent.Type.STOP);
		{
			System.out.println("Got a STOP LineEvent");
			event.getLine().close();
			stillPlaying = false;
			System.exit(0);
		}
		
	}
}
