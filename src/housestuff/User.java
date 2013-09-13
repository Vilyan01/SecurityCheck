package housestuff;

public class User {
	
	private String userName;
	private String pass;
	
	public User(String name, String password) {
		setUserName(name);
		setPass(password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
