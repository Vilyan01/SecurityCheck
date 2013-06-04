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
	
	public void addWindows(int windowNumber) {
		for(int i = 0; i < windowNumber; i++) {
			String name = "" + i;
			Window window = new Window(name);
			windows.add(window);
		}
	}
	
	public void addDoors(int doorNumber) {
		for(int i = 0; i < doorNumber; i++) {
			String name = "" + i;
			Door door = new Door(name);
			doors.add(door);
		}
	}
}