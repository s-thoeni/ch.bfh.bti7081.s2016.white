package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Gets data like users and userlists and and checks the users entered password with the one from the db
 * 
 * @author shepd1
 *
 */
public interface LoginFacade {
	/**
	 * Checks the password for a given user name. Password must be specified as SHA256 Hash. If the passwords
	 * match, a user object with provided user / password combination is returned.
	 * @param username - the username as string for which the password should be checked
	 * @param password - the password-hash (sha256) as String to be checked
	 * @return The User object which matches with the entered one, of passwords match
	 * @throws SneException
	 */
	public User checkPassword(String username, String Password) throws SneException;
	
	/**
	 * Returns all users from the database
	 * @return an Arraylist of all Users in the DB
	 * @throws SneException
	 */
	public ArrayList<User> getUsers() throws SneException;
	
	/**
	 * Returns the user object for a provided user name
	 * @param username the username as string
	 * @return a User object which's username matches with the given parameter
	 * @throws SneException
	 */
	public User getUser(String username) throws SneException;
}
