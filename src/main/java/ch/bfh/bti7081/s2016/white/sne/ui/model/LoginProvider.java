package ch.bfh.bti7081.s2016.white.sne.ui.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.bl.LoginFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.LoginFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public class LoginProvider {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LoginProvider.class);
	
	private LoginFacade facade;

	
	public LoginProvider() throws SneException {
		facade = new LoginFacadeImpl();
	}
	
	public User checkPassword(String username, String password) throws SneException{
		logger.debug("->");
		logger.debug("<-");
		return facade.checkPassword(username, password);
	}	
	
	public User getUser(String username) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return facade.getUser(username);
	}
	
}