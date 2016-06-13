package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.dao.UserDAOImpl;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Facade-class for login. Gets userlist from DAO and provides
 * it to Model (UI).
 * 
 * @author shepd1
 *
 */
public class LoginFacadeImpl implements LoginFacade {

	/**
	 * Initialize new logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigurationFacadeImpl.class);
	
	private UserDAOImpl dao;
	
	public LoginFacadeImpl() {
		dao = new UserDAOImpl();
	}
	
	@Override
	public User checkPassword(String username, String password) throws SneException {
		ArrayList<User> userList = getUsers();
		for(User u: userList) {
			if(u.getUserName() == username) {
				if(u.getPassword() == password) {
					return u;
				}
			}
		}
		return null;
	}

	@Override
	public ArrayList<User> getUsers() throws SneException {
		return dao.getUserlist();
	}
	
	public User getUser(String username) throws SneException {
		ArrayList<User> userList = getUsers();
		for (User u: userList) {
			if(u.getUserName() == username) {
				return u;
			}
		}
		return null;
	}
}
