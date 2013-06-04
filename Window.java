
public class Window {
	private String name;
	private boolean secured;
	
	public Window(String name) {
		this.name = name;
		secured = true;
	}
	
	public boolean isSecured() {
		return secured;
	}
	
	public void close() {
		secured = true;
	}
	
	public void open() {
		secured = false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
}
