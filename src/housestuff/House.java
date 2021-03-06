package housestuff;

import java.util.ArrayList;
public class House {
	private int totalPeople;
	private String name;
	private ArrayList<Door> doors;
	private ArrayList<Window> windows;
	public ArrayList<Camera> cameras;
	
	public House(String address) {
		setName(address);
		totalPeople = 0;
		doors = new ArrayList<>();
		windows = new ArrayList<>();
		cameras = new ArrayList<>();
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
	
	public void addCamera(String cameraName) {
		Camera camera = new Camera(cameraName);
		cameras.add(camera);
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
	
	public String openDoor(String doorName) {
		Door d = findDoor(doorName);
		if(d != null) {
			if(d.isSecured() == true) {
				d.open();
				return (doorName + " opened.");
			}
			else {
				return (doorName + " is already open.");
			}
		}
		else {
			return (doorName + " not found.");
		}
			
	}
	
	public String closeDoor(String doorName) {
		Door d = findDoor(doorName);
		if(d != null) {
			if(d.isSecured() == false) {
				d.close();
				return (doorName + " closed.");
			}
			else {
				return (doorName + " is already closed.");
			}
		}
		else {
			return ("Cannot find " + doorName);
		}
	}

	public String openWindow(String windowName) {
		Window w = findWindow(windowName);
		if (w != null) {
			if(w.isSecured()) {
				w.open();
				return (windowName + " opened.");
			}
			else {
				return (windowName + " is already open!");
			}
		}
		else {
			return ("Cannot find " + windowName);
		}
	}
	
	public String closeWindow(String windowName) {
		Window w = findWindow(windowName);
		if(w != null) {
			if(w.isSecured() == false) {
				w.close();
				return (windowName + " closed.");
			}
			else {
				return (windowName + " already closed!");
			}
		}
		else {
			return ("Cannot find " + windowName);
		}
	}
	
	public Camera findCamera(String searchString) {
		boolean finished = false;
		Camera searchCamera = null;
		int i = 0;
		while(!finished && i < cameras.size()) {
			String camera = cameras.get(i).getName();
			if(camera.equals(searchString)) {
				searchCamera = cameras.get(i);
				finished = true;
			}
			i++;
		}
		return searchCamera;
	}
	
	public ArrayList<Camera> getCameras() {
		return cameras;
	}
}
