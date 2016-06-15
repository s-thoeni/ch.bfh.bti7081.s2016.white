package ch.bfh.bti7081.s2016.white.sne.ui.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Provider for configuration view. Needs to be initialized with a user object. Reads and writes configuration of user.
 * 
 * @author shepd1
 *
 */
public class ConfigurationProvider {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigurationProvider.class);
	
	/**
	 * Business layer of configuration
	 */
	private ConfigurationFacade facade;
	
	/**
	 * Data object configuration
	 */
	private Configuration config;

	/**
	 * Data object user
	 */
	private User user;
	
	/**
	 * 
	 * @param user user for which the configuration should be initialized
	 * @throws SneException
	 */
	public ConfigurationProvider(User user) throws SneException {
		this.setUser(user);
		facade = new ConfigurationFacadeImpl();
		this.config = facade.getConfig(user);
	}
	
	public Configuration getConfig() {
		logger.debug("->");
		logger.debug("<-");
		return config;
	}
	
	public void setConfig(Configuration config, User user) throws SneException {
		logger.debug("->");
		this.config = config;
		facade.setConfig(this.config.getReports(), user);
		logger.debug("<-");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
