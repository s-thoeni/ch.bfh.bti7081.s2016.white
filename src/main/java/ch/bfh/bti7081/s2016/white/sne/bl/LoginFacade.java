package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public interface LoginFacade {
	public User checkPassword(String username, String Password) throws SneException;
	
	public ArrayList<User> getUsers() throws SneException;

	public User getUser(String username) throws SneException;
}
