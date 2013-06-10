package housestuff;
/**
 * Driver stuff will go here, so I can actually run the program.  I will deal with this tomorrow.
 * The Parser is still giving me issues, saying the Scanner instance "tokenizer" was never closed.
 * 
 * @author Brian
 *
 */
public class Driver {
	private Parser parser;
	private House house;
	
	public Driver() {
		parser = new Parser();
		house = new House("House1");
	}
	
	public void start() {
		printWelcome();
		
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
		System.out.println("Unsecured doors: " + house.checkDoors());
		System.out.println("Unsecured windows: " + house.checkWindows());
	}
	
	
	private void add(Command command) {
		if(command.getSecondWord().equals("door")) {
			if(command.hasThirdWord()) {
				house.addDoor(command.getThirdWord());
				System.out.println("Door successfully added.");
			}
			else {
				System.out.println("Must enter a name for the door.");
			}
		}
		else if(command.getSecondWord().equals("window")) {
			if(command.hasThirdWord()) {
				house.addWindows(command.getThirdWord());
				System.out.println("Window successfully added.");
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
					house.openDoor(command.getThirdWord());
				}
				else {
					System.out.println("Must enter a name for the door.  Example: open door door1");
				}
			}
			else if(command.getSecondWord().equals("window")) {
				if(command.hasThirdWord()) {
					house.openWindow(command.getThirdWord());
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
				house.closeDoor(command.getThirdWord());
			}
			else {
				System.out.println("Must enter door name.  Example: close door door1");
			}
		}
		else if(command.getSecondWord().equals("window")) {
			if(command.hasThirdWord()) {
				house.closeWindow(command.getThirdWord());
			}
			else {
				System.out.println("Must enter a window name.  Example:  close window window1");
			}
		}
		else {
			System.out.println("You fucked it up.  Example:  close <window/door> <doorname>");
		}
	}
	
	public static void main(String[] args) {
		Driver d = new Driver();
		d.start();
	}
}
