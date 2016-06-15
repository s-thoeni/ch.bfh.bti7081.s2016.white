package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.ArrayList;

import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
/**
 * Class implementing connection to data backend to access login information
 * 
 * @author shepd1
 * @author eller1
 *
 */
/**
 * Class implementing connection to data backend to access login information
 * @author team white
 *
 */
public interface UserDAO {
	
	/**
	 * Reads all users from data backend and returns them as ArrayList
	 * @return ArrayList<User>
	 * @throws SneException
	 */
	public ArrayList<User> getUserlist() throws SneException;
}
