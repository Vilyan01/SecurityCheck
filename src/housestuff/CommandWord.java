package housestuff;

public enum CommandWord {
	VIEW("view"), HELP("help"), UNKNOWN("?"), CHECK("check"), OPEN("open"), CLOSE("close"), CREATE("create"), ADD("add");

	private String commandString;
	
	CommandWord(String commandString) {
		this.commandString = commandString;
	}
	
	public String toString() {
		return commandString;
	}
}
