package housestuff;

import java.util.*;

/**
 * 
 * Still deciding whether I want to change make the GUI class my new driver, somehow get both
 * work together so they can modify each other.
 * 
 * @author Brian
 *
 */
public class Driver {
	private Parser parser;
	private House currentHouse;
	private ArrayList<House> houses;
	
	public Driver() {
		parser = new Parser();
		houses = new ArrayList<>();
	}
	
	public void start() {
		printWelcome();
		
		House h = new House("house1");
		houses.add(h);
		currentHouse = h;
		
		boolean finished = false;
		while(!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thanks for using SecurityCheck!");
	}
	
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to SecurityCheck.  A security monitor for a");
		System.out.println("notional house.  Type " + CommandWord.HELP + " for");
		System.out.println("a list of commands.");
		System.out.println();
		
	}
	
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;
		
		CommandWord commandWord = command.getCommandWord();
		
		switch(commandWord) {
		case UNKNOWN:
			System.out.println("Command is not valid.");
			break;
			
		case HELP:
			printHelp();
			break;
			
		case QUIT:
			wantToQuit = quit(command);
			break;
			
		case CHECK:
			checkSecurity();
			break;
			
		case OPEN:
			open(command);
			break;
		
		case CLOSE:
			close(command);
			break;
			
		case ADD:
			add(command);
			break;
		
		case CHANGE:
			change(command);
			break;
		}
		return wantToQuit;
	}
	
	private void printHelp() {
		System.out.println("Your commands are:");
		parser.showCommands();
	}
	
	private boolean quit(Command command) {
		if(command.hasSecondWord()) {
			System.out.println("You can't quit that...");
			return false;
		}
		else {
			return true;
		}
	}
	
	private void checkSecurity() {
		System.out.println("Unsecured doors: " + currentHouse.checkDoors());
		System.out.println("Unsecured windows: " + currentHouse.checkWindows());
	}
	
	
	private void add(Command command) {
		if(command.getSecondWord().equals("door")) {
			if(command.hasThirdWord()) {
				currentHouse.addDoor(command.getThirdWord());
				System.out.println("Door successfully added.");
			}
			else {
				System.out.println("Must enter a name for the door.");
			}
		}
		else if(command.getSecondWord().equals("window")) {
			if(command.hasThirdWord()) {
				currentHouse.addWindows(command.getThirdWord());
				System.out.println("Window successfully added.");
			}
		}
		else if(command.getSecondWord().equals("house")) {
			if(command.hasThirdWord()) {
				House h = new House(command.getThirdWord());
				houses.add(h);
				System.out.println(command.getThirdWord() + " added.");
			}
			else {
				System.out.println("Add a name for your house.");
			}
		}
		else {
			System.out.println("You can't add that.  Current version only allows for doors and windows to be added.");
		}
	}
	
	public void open(Command command) {
		if(command.hasSecondWord()) {
			if(command.getSecondWord().equals("door")) {
				if(command.hasThirdWord()) {
					currentHouse.openDoor(command.getThirdWord());
				}
				else {
					System.out.println("Must enter a name for the door.  Example: open door door1");
				}
			}
			else if(command.getSecondWord().equals("window")) {
				if(command.hasThirdWord()) {
					currentHouse.openWindow(command.getThirdWord());
				}
				else {
					System.out.println("Must enter a name for the window.  Example:  open window window1");
				}
			}
		}
		else {
			System.out.println("You royally fucked it up.  Example:  open door door1");
		}
	}
		
		
	
	public void close(Command command) {
		if(command.getSecondWord().equals("door")) {
			if(command.hasThirdWord()) {
				currentHouse.closeDoor(command.getThirdWord());
			}
			else {
				System.out.println("Must enter door name.  Example: close door door1");
			}
		}
		else if(command.getSecondWord().equals("window")) {
			if(command.hasThirdWord()) {
				currentHouse.closeWindow(command.getThirdWord());
			}
			else {
				System.out.println("Must enter a window name.  Example:  close window window1");
			}
		}
		else {
			System.out.println("You fucked it up.  Example:  close <window/door> <doorname>");
		}
	}
	
	public House findHouse(String houseName) {
		House selectedHouse = null;
		boolean finished = false;
		int i = 0;
		while(!finished) {
			House h = houses.get(i);
			if(h.getName().equals(houseName)) {
				finished = true;
				selectedHouse = houses.get(i);
			}
			i++;
		}
		
		return selectedHouse;
	}
	
	public void change(Command command) {
		if(command.hasSecondWord()) {
			House h = findHouse(command.getSecondWord());
			if(h != null) {
				currentHouse = h;
				System.out.println("House changed to " + currentHouse.getName());
			}
			else {
				System.out.println("Cannot find the house you were looking for.");
			}
		}
		else {
			System.out.println("Please enter the name of the house you are looking for.  Example: change house1");
		}
	}
	
	
	public static void main(String[] args) {
		Driver d = new Driver();
		d.start();
	}
}
