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
	
	
	public String checkDoors() {
		String doorNames = "";
			for(Door d : doors) {
				if (d.isSecured() == false) {
					doorNames += (d.getName() + " ");
				}
			}
		return doorNames;
	}
	
	public String checkWindows() {
		String windowNames = "";
		for(Window w : windows) {
			if(w.isSecured() == false) {
				windowNames += (w.getName() + " ");
			}
		}
		
		
		return windowNames;
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
	
	
	public Door findDoor(String doorName) {
		Boolean finished = false;
		Door searchDoor = null;
		int i = 0;
		while(!finished && i < doors.size()) {
			String door = doors.get(i).getName();
			if (door.equals(doorName)) {
				searchDoor = doors.get(i);
				finished = true;
			}
			i++;
		}
		return searchDoor;
	}
	
	public Window findWindow(String windowName) {
		Boolean finished = false;
		Window searchWindow = null;
		int i = 0;
		while(!finished && i < windows.size()) {
			String window = windows.get(i).getName();
			if(window.equals(windowName)) {
				searchWindow = windows.get(i);
				finished = true;
			}
			i++;
		}
		return searchWindow;
	}
	
	public void openDoor(String doorName) {
		Door d = findDoor(doorName);
		if(d != null) {
			if(d.isSecured() == true) {
				d.open();
				System.out.println(doorName + " opened.");
			}
			else {
				System.out.println(doorName + " is already open.");
			}
		}
		else {
			System.out.println(doorName + " not found.");
		}
			
	}
	
	public void closeDoor(String doorName) {
		Door d = findDoor(doorName);
		if(d != null) {
			if(d.isSecured() == false) {
				d.close();
				System.out.println(doorName + " closed.");
			}
			else {
				System.out.println(doorName + " is already closed.");
			}
		}
		else {
			System.out.println("Cannot find " + doorName);
		}
	}

	public void openWindow(String windowName) {
		Window w = findWindow(windowName);
		if (w != null) {
			if(w.isSecured()) {
				w.open();
				System.out.println(windowName + " opened.");
			}
			else {
				System.out.println(windowName + " is already open!");
			}
		}
		else {
			System.out.println("Cannot find " + windowName);
		}
	}
	
	public void closeWindow(String windowName) {
		Window w = findWindow(windowName);
		if(w != null) {
			if(w.isSecured() == false) {
				w.close();
				System.out.println(windowName + " closed.");
			}
			else {
				System.out.println(windowName + " already closed!");
			}
		}
		else {
			System.out.println("Cannot find " + windowName);
		}
	}
}
