package housestuff;

import java.io.*;
import java.util.Date;

import javax.swing.JTextArea;


public class LoggingUtility {
	
	private String lastSavedLog;
	
	public LoggingUtility() {
		
	}
	
	public void writeAndSave(JTextArea text) throws IOException {
		String fileName = "logs\\" + generateName() + ".txt";
		lastSavedLog = fileName;
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		text.write(bw);
		bw.close();
		fw.close();
	}
	
	public String generateName() {
		Date d = new Date();
		String strLong = Long.toString(d.getTime());
		return strLong;
	}
	
	public String getLastSavedLog() {
		return lastSavedLog;
	}
}
