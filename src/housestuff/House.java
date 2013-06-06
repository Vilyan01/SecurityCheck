package housestuff;
import java.util.ArrayList;
public class House {
	private int totalPeople;
	private String name;
	private ArrayList<Door> doors;
	private ArrayList<Window> windows;
	
	public House(String address) {
		setName(address);
		totalPeople = 0;
		doors = new ArrayList<>();
		windows = new ArrayList<>();
	}
	
	public int getNumberOfPeople() {
		return totalPeople;
	}
	
	
	public ArrayList<Door> checkDoors() {
		ArrayList<Door> unsecuredDoors = new ArrayList<>();
			for(Door d : doors) {
				if (d.isSecured() == false) {
					unsecuredDoors.add(d);
				}
			}
		return unsecuredDoors;
	}
	
	public ArrayList<Window> checkWindows() {
		ArrayList<Window> unsecuredWindows = new ArrayList<>();
		for(Window w : windows) {
			if(w.isSecured() == false) {
				unsecuredWindows.add(w);
			}
		}
		return unsecuredWindows;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addWindows(String windowName) {
		Window window = new Window(windowName);
		windows.add(window);
	}
	
	public void addDoor(String doorName) {
		Door door = new Door(doorName);
		doors.add(door);
		}
	
	public Door openDoor(String doorName) {
		Door desiredDoor = null;
		for(Door door : doors) {
			if(door.getName().equals(doorName)) {
				desiredDoor = door;
			}
		}
		return desiredDoor;
	}
	
	public Window openWindow(String windowName) {
		Window desiredWindow = null;
		for(Window window : windows) {
			if(window.getName().equals(windowName)) {
				desiredWindow = window;
			}
		}
		return desiredWindow;
	}
}
