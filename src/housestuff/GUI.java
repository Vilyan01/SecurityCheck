package housestuff;

/**
 * Login screen and users/database are next...
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.tree.*;

import java.awt.SystemColor;
import java.util.Date;
import java.sql.Timestamp;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import javax.swing.JTextField;
import java.awt.Button;
import java.util.Scanner;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;



public class GUI {
	
	private Scanner reader;
	private CommandWords commands;
	private Scanner tokenizer;
	private LoggingUtility logger;

	private JFrame frame;
	private JTextField textField;
	private JTree tree;
	private JTextArea textArea;
	private House currentHouse;
	private JTree cameraList;
	
	private DefaultMutableTreeNode doors, windows;
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("House");
	private DefaultMutableTreeNode cameraRoot = new DefaultMutableTreeNode("Cameras");
	
	private boolean secMode = false;
	private Alarm al;
	
	private static final String VERSION = "0.02";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		currentHouse = new House("My House");
		al = new Alarm();
		logger = new LoggingUtility();
		
		frame = new JFrame();
		frame.setTitle("SecurityCheck");
		frame.setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JMenuBar menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBar, 784, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JCheckBoxMenuItem mntmSecurityMode = new JCheckBoxMenuItem("Toggle Sec-Mode");
		mntmSecurityMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(secMode == false) {
					secMode = true;
					textArea.append("\n" + getTimeStamp() + "Security Mode Enabled");
				}
				else if(secMode == true) {
					secMode = false;
					textArea.append("\n" + getTimeStamp() + "Security Mode Disabled");
				}
			}
		});
		mnOptions.add(mntmSecurityMode);
		
		JMenuItem mntmAutodetect = new JMenuItem("Auto-Detect...");
		mnFile.add(mntmAutodetect);
		
		JMenuItem mntmLog = new JMenuItem("Save Log");
		mnFile.add(mntmLog);
		mntmLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					logger.writeAndSave(textArea);
					JOptionPane.showMessageDialog(frame, "Log " + logger.getLastSavedLog() + " created.");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, "Error:  Could not save log.");
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmVersionInfo = new JMenuItem("Version Info");
		mntmVersionInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Version: " + VERSION);
			}
		});
		mnAbout.add(mntmVersionInfo);
		
		cameraList = new JTree(cameraRoot);
		springLayout.putConstraint(SpringLayout.EAST, cameraList, -10, SpringLayout.EAST, frame.getContentPane());
		cameraList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, cameraList, 6, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.SOUTH, cameraList, 313, SpringLayout.SOUTH, menuBar);
		cameraList.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		frame.getContentPane().add(cameraList);
		refreshTree(cameraList);
		
		Canvas canvas = new Canvas();
		springLayout.putConstraint(SpringLayout.WEST, canvas, 148, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, canvas, -141, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, cameraList, 7, SpringLayout.EAST, canvas);
		canvas.setBackground(SystemColor.desktop);
		springLayout.putConstraint(SpringLayout.NORTH, canvas, 6, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.SOUTH, canvas, 313, SpringLayout.SOUTH, menuBar);
		frame.getContentPane().add(canvas);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				int e = arg0.getKeyCode();
				if(e == KeyEvent.VK_ENTER){
					textArea.append("\n" + processCommand(getCommand(textField)));
					textField.setText("");
				}
			}
			
		});
		springLayout.putConstraint(SpringLayout.NORTH, textField, -30, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Button button = new Button("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("\n" + processCommand(getCommand(textField)));
				textField.setText("");
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, button);
		springLayout.putConstraint(SpringLayout.NORTH, button, 195, SpringLayout.SOUTH, cameraList);
		springLayout.putConstraint(SpringLayout.WEST, button, -80, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, button, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, button, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 4, SpringLayout.SOUTH, cameraList);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -4, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, cameraList);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setText("Welcome to SecurityCheck!  Type '" + CommandWord.HELP + "' for a "
				+ "list of commands.");
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		tree = new JTree(rootNode);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		doors = addAFile("Doors", rootNode);
		windows = addAFile("Windows", rootNode);
		refreshTree(tree);
		
		springLayout.putConstraint(SpringLayout.NORTH, tree, 6, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.WEST, tree, 10, SpringLayout.WEST, menuBar);
		springLayout.putConstraint(SpringLayout.SOUTH, tree, -4, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, tree, -8, SpringLayout.WEST, canvas);
		frame.getContentPane().add(tree);
	}
	
	//------------------------------------------------PARSER COMPONENTS------------------------------------------------------------
	


	private Command getCommand(JTextField field) {
		commands = new CommandWords();
		reader = new Scanner(field.getText());
		String inputLine = null;
		String word1 = null;
		String word2 = null;
		String word3 = null;
		
		inputLine = reader.nextLine();
		tokenizer = new Scanner(inputLine);
		if(tokenizer.hasNext()) {
			word1 = tokenizer.next();
			if(tokenizer.hasNext()) {
				word2 = tokenizer.next();
				if(tokenizer.hasNext()) {
					word3 = tokenizer.next();
				}
			}
		}
		return new Command(commands.getCommandWord(word1), word2, word3);
	}
	
	private String showCommands() {
		return commands.showAll();
	}
	
	//------------------------------------------DRIVER INFO-----------------------------------------------------
	
	public String processCommand(Command command) {
		String output = null;
		
		CommandWord commandWord = command.getCommandWord();
		switch(commandWord) {
		case UNKNOWN:
			output = getTimeStamp() + "Command is not valid.";
			break;
		
		case HELP:
			output = getTimeStamp() + showHelp();
			break;
			
		case CHECK:
			output = getTimeStamp() + checkSecurity();
			break;
			
		case OPEN:
			output = getTimeStamp() + open(command);
			break;
		
		case CLOSE:
			output = getTimeStamp() + close(command);
			break;
			
		case ADD:
			output = getTimeStamp() + add(command);
			break;
		}
		return output;				
	}

	private String add(Command command) {
		String output = "";
		if(command.hasSecondWord()) {
			if(command.getSecondWord().equals("door")) {
				if(command.hasThirdWord()) {
					currentHouse.addDoor(command.getThirdWord());
					addAFile(command.getThirdWord(), doors);
					refreshTree(tree);
					output = (command.getThirdWord() + " successfully added.");
				}
				else {
					output = ("Must enter a name for the door.");
				}
			}
			else if(command.getSecondWord().equals("window")) {
				if(command.hasThirdWord()) {
					currentHouse.addWindows(command.getThirdWord());
					addAFile(command.getThirdWord(), windows);
					refreshTree(tree);
					output = (command.getThirdWord() + " successfully added.");
				}
			}
			else if(command.getSecondWord().equals("camera")) {
				if(command.hasThirdWord()) {
					currentHouse.addCamera(command.getThirdWord());
					addAFile(command.getThirdWord(), cameraRoot);
					refreshTree(cameraList);
					output = (command.getThirdWord() + " successfully added.");
				}
				else {
					output = "Must enter a name for the camera.";
				}
			}
			else {
				output = ("You can't add that.  Current version only allows for doors, windows, and cameras to be added.");
			}
		}
		else {
			output = "Add what?";
		}
			
		return output;
	}

	private void refreshTree(JTree tree) {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		model.reload();
		for(int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
		
	}

	private String close(Command command) {
		String output = null;
		if(command.hasSecondWord()) {
			if(command.getSecondWord().equals("door")) {
				if(command.hasThirdWord()) {
					changeDoorNodeName("(" + command.getThirdWord() + ")", command.getThirdWord());
					output = currentHouse.closeDoor(command.getThirdWord());
					refreshTree(tree);
				}
				else {
					output = ("Must enter door name.  Example: close door door1");
				}
			}
			else if(command.getSecondWord().equals("window")) {
				if(command.hasThirdWord()) {
					changeWindowNodeName("(" + command.getThirdWord() + ")", command.getThirdWord());
					output = currentHouse.closeWindow(command.getThirdWord());
					refreshTree(tree);
				}
				else {
					output = ("Must enter a window name.  Example:  close window window1");
				}
			}
		}
		else {
			output = ("You fucked it up.  Example:  close <window/door> <doorname>");
		}
		return output;
	}

	private String open(Command command) {
		String output = null;
		if(command.hasSecondWord()) {
			if(command.getSecondWord().equals("door")) {
				if(command.hasThirdWord()) {
					output = currentHouse.openDoor(command.getThirdWord());
					changeDoorNodeName(command.getThirdWord(), "(" + command.getThirdWord() + ")");
					if (secMode == true) {
						soundAlarm();
						output += "\nAlarm Sounded!";
					}
					refreshTree(tree);
				}
				else {
					output = ("Must enter a name for the door.  Example: open door door1");
				}
			}
			else if(command.getSecondWord().equals("window")) {
				if(command.hasThirdWord()) {
					output = currentHouse.openWindow(command.getThirdWord());
					changeWindowNodeName(command.getThirdWord(), "(" + command.getThirdWord() + ")");
					refreshTree(tree);
					if(secMode == true) {
						soundAlarm();
						output += "\nAlarm Sounded!";
					}
				}
				else {
					output = ("Must enter a name for the window.  Example:  open window window1");
				}
			}
		}
		else {
			output = ("You royally fucked it up.  Example:  open door door1");
		}
		return output;
	}

	private void soundAlarm() {
		if(al.getFile() != null) {
			al.playAlarm();
		}
		else {
			JOptionPane.showMessageDialog(frame, "Warning:  Alarm sound is not available.");
		}
		
	}
	
	private String getTimeStamp() {
		String timeStamp = null;
		Date date = new Date();
		Timestamp t = new Timestamp(date.getTime());
		timeStamp = "[" + t.toString() + "] ";
		
		return timeStamp;
	}

	private void changeWindowNodeName(String windowName, String newName) {
		DefaultMutableTreeNode node = findWindowNode(windowName);
		if(node != null) {
			node.setUserObject(newName);
		}
		
	}

	private DefaultMutableTreeNode findWindowNode(String windowName) {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode node = null;
		for(int i = 0; i < model.getChildCount(windows); i++) {
			if(model.getChild(windows, i).toString().equals(windowName)) {
				node = (DefaultMutableTreeNode)model.getChild(windows, i);
			}
		}
		return node;
	}

	private void changeDoorNodeName(String doorName, String newName) {
		DefaultMutableTreeNode node = findDoorNode(doorName);
		if(node != null) {
			node.setUserObject(newName);
		}
	}
	
	private DefaultMutableTreeNode findDoorNode(String doorName) {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode node = null;
		for(int i = 0; i < model.getChildCount(doors); i++) {
			if(model.getChild(doors, i).toString().equals(doorName)) {
				node = (DefaultMutableTreeNode) model.getChild(doors, i);
			}
		}
		
		return node;
	}

	private String checkSecurity() {
		String securityString = "Unsecured doors: " + currentHouse.checkDoors() + "\n" +
				"Unsecured windows: " + currentHouse.checkWindows();
		if(secMode == true) {
			securityString += "\nSecurity Mode is enabled";
		}
		
		return securityString;
	}

	private String showHelp() {
		return "Your commands are: " + showCommands();
	}
	
	private DefaultMutableTreeNode addAFile(String fileName,
			DefaultMutableTreeNode parentFolder) {
		DefaultMutableTreeNode newFile = new DefaultMutableTreeNode(fileName);
		parentFolder.add(newFile);
		return newFile;
	}
}
