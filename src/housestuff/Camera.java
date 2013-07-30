package housestuff;
public class Camera {

	String cameraName;
	
	public Camera(String name) {
		cameraName = name;
	}
	
	public String getName() {
		return cameraName;
	}
	
	public void setName(String newName) {
		cameraName = newName;
	}
}
