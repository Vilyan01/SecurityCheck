package housestuff;
/**
 * Class for a window.  Currently only contains a particular name for a window, and whether the window is open or closed.
 * 
 * @author Brian
 *
 */
public class Window {
	private String name;
	private boolean secured;
	
	/**
	 * Constructor method for a window.  Set as default to closed.
	 * 
	 * @param name The name (or location) of where the window is located.
	 */
	public Window(String name) {
		this.name = name;
		secured = true;
	}
	
	/**
	 * Checks to see if a window is secured.
	 * @return True if the window is closed, false if it is open.
	 */
	public boolean isSecured() {
		return secured;
	}
	
	/**
	 * Closes the window.
	 */
	public void close() {
		secured = true;
	}
	
	/**
	 * Opens the window.
	 */
	public void open() {
		secured = false;
	}
	
	/**
	 * @return The name of the window.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Changes the name of the window.
	 * @param newName New name you wish to name the window.
	 */
	public void setName(String newName) {
		name = newName;
	}
}
