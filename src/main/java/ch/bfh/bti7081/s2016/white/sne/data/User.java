package ch.bfh.bti7081.s2016.white.sne.data;

public class User {
	private String userName;
	private Configuration cofiguration;

	public User(String userName, Configuration cofiguration) {
		this.userName = userName;
		this.cofiguration = cofiguration;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Configuration getCofiguration() {
		return this.cofiguration;
	}

	public void setCofiguration(Configuration cofiguration) {
		this.cofiguration = cofiguration;
	}

}
