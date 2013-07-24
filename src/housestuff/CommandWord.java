package housestuff;

public enum CommandWord {
	HELP("help"), UNKNOWN("?"), CHECK("check"), OPEN("open"), CLOSE("close"), ADD("add"), QUIT("quit"), CHANGE("change");

	private String commandString;
	
	CommandWord(String commandString) {
		this.commandString = commandString;
	}
	
	public String toString() {
		return commandString;
	}
}
