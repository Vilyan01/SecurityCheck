package housestuff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import java.awt.Canvas;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class GUI extends Driver {

	private JFrame frmSecuritycheck;
	private JTable doorsAndWindows;
	private JTable cameras;
	private JTextArea consoleArea;
	private static final String VERSION = "Version 0.01";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmSecuritycheck.setVisible(true);
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
		
		frmSecuritycheck = new JFrame();
		frmSecuritycheck.setTitle("SecurityCheck");
		frmSecuritycheck.setBackground(Color.GRAY);
		frmSecuritycheck.setBounds(100, 100, 712, 482);
		frmSecuritycheck.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmSecuritycheck.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmAutodetectMods = new JMenuItem("Auto-Detect Mods");
		mntmAutodetectMods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmSecuritycheck, "Not available in current version.\nCheck back soon!");
			}
		});
		mnFile.add(mntmAutodetectMods);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmQuit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About...");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmSecuritycheck, "Monitor your shit!\n" + VERSION);
			}
		});
		mnHelp.add(mntmAbout);
		SpringLayout springLayout = new SpringLayout();
		frmSecuritycheck.getContentPane().setLayout(springLayout);
		
		doorsAndWindows = new JTable();
		doorsAndWindows.setShowHorizontalLines(true);
		doorsAndWindows.setCellSelectionEnabled(true);
		springLayout.putConstraint(SpringLayout.NORTH, doorsAndWindows, 10, SpringLayout.NORTH, frmSecuritycheck.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, doorsAndWindows, 10, SpringLayout.WEST, frmSecuritycheck.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, doorsAndWindows, -114, SpringLayout.SOUTH, frmSecuritycheck.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, doorsAndWindows, -564, SpringLayout.EAST, frmSecuritycheck.getContentPane());
		doorsAndWindows.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmSecuritycheck.getContentPane().add(doorsAndWindows);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, doorsAndWindows);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frmSecuritycheck.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 104, SpringLayout.SOUTH, doorsAndWindows);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 686, SpringLayout.WEST, frmSecuritycheck.getContentPane());
		frmSecuritycheck.getContentPane().add(scrollPane);
		
		this.consoleArea = new JTextArea();
		scrollPane.setViewportView(consoleArea);
		
		Canvas canvas = new Canvas();
		springLayout.putConstraint(SpringLayout.NORTH, canvas, 0, SpringLayout.NORTH, doorsAndWindows);
		springLayout.putConstraint(SpringLayout.WEST, canvas, 6, SpringLayout.EAST, doorsAndWindows);
		springLayout.putConstraint(SpringLayout.SOUTH, canvas, -6, SpringLayout.NORTH, scrollPane);
		canvas.setBackground(SystemColor.inactiveCaption);
		frmSecuritycheck.getContentPane().add(canvas);
		
		cameras = new JTable();
		springLayout.putConstraint(SpringLayout.WEST, cameras, 544, SpringLayout.WEST, frmSecuritycheck.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cameras, -10, SpringLayout.EAST, frmSecuritycheck.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, canvas, -6, SpringLayout.WEST, cameras);
		springLayout.putConstraint(SpringLayout.NORTH, cameras, 0, SpringLayout.NORTH, doorsAndWindows);
		springLayout.putConstraint(SpringLayout.SOUTH, cameras, 0, SpringLayout.SOUTH, doorsAndWindows);
		cameras.setCellSelectionEnabled(true);
		cameras.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmSecuritycheck.getContentPane().add(cameras);
	}
	
	public JTextArea getTextArea() {
		return consoleArea;
	}
}
