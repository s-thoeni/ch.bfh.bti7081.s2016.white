package ch.bfh.bti7081.s2016.white.sne.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing a user of sne.
 * @author team white
 *
 */
public class User {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(User.class);
	
	/**
	 * user's name
	 */
	private String userName;
	
	/**
	 * user's password
	 */
	private String password;

	/**
	 * Instantiates a user object with specified name
	 * @param userName as string
	 */
	public User(String userName) {
		this.userName = userName;
		this.password = null;
	}
	
	/**
	 * Instantiates a user object with specified name
	 * @param userName as string
	 * @param password as string
	 */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Returns user's name
	 * @return name as string
	 */
	public String getUserName() {
		logger.debug("->");
		logger.debug("<-");
		return this.userName;
	}

	/**
	 * Assigns name to user
	 * @param userName as string
	 */
	public void setUserName(String userName) {
		logger.debug("->");
		
		this.userName = userName;
		logger.debug("<-");
	}
	
	/**
	 * Returns user's password
	 * @return password as string
	 */
	public String getPassword() {
		logger.debug("->");
		logger.debug("<-");
		return this.password;
	}
}
