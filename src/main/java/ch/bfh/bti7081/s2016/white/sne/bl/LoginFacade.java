package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;
import ch.bfh.bti7081.s2016.white.sne.data.User;

public interface LoginFacade {
	public User checkPassword(String username, String Password);
	
	public ArrayList<User> getUsers();

	public User getUser(String username);
}
