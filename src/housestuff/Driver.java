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
			
		//case OPEN:
			//open(command);
			//break;
		
		//case CLOSE:
			//close(command);
			//break;
			
		//case ADD:
			//add(command);
			//break;
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
		System.out.println("Unsecured doors: " + house.checkDoors().toString());
		System.out.println("Unsecured windows: " + house.checkWindows().toString());
	}
	
	public static void main(String[] args) {
		Driver d = new Driver();
		d.start();
	}
}
