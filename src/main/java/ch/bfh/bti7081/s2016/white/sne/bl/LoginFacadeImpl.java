package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.dao.UserDAO;
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
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LoginFacadeImpl.class);
	
	/**
	 * Data access object for Users
	 */
	private UserDAO dao;
	
	/**
	 * Constructs a new LoginFacadeImpl. Initializes UserDAO
	 * @throws SneException
	 */
	public LoginFacadeImpl() throws SneException {
		dao = new UserDAOImpl();
	}
	
	@Override
	public User checkPassword(String username, String password) throws SneException {
		logger.debug("->");
		ArrayList<User> userList = getUsers();
		for(User u: userList) {
			if(u.getUserName().equals(username)) {
				if(u.getPassword().equals(password)) {
					logger.debug("<-");
					return u;
				}
			}
		}
		logger.debug("<-");
		return null;
	}

	@Override
	public ArrayList<User> getUsers() throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return dao.getUserlist();
	}
	/**
	 * @param username the username as String
	 * @return a User object which maches with the given username
	 * 
	 */
	public User getUser(String username) throws SneException {
		logger.debug("->");
		ArrayList<User> userList = getUsers();
		for (User u: userList) {
			if(u.getUserName().equals(username)) {
				logger.debug("<-");
				return u;
			}
		}
		logger.debug("<-");
		return null;
	}
}
