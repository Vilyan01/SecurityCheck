package housestuff;


import java.io.*;
import javax.sound.sampled.*;


public class Alarm {
	private static String fileName = "LeroyJenkins.wav";
	private File alarmFile;
	
	public Alarm() {
		alarmFile = new File(fileName);
	}
	
	
	public void playAlarm() {
		try{
			AudioInputStream stream = AudioSystem.getAudioInputStream(alarmFile);
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();
		}
		catch(Exception e) {
			
		}
	}
	
	public File getFile() {
		return alarmFile;
	}
}
