package ch.bfh.bti7081.s2016.white.sne.ui.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;


public class ConfigurationProvider {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigurationProvider.class);
	
	private ConfigurationFacade facade;
	private Configuration config;
	
	public ConfigurationProvider(User user) throws SneException {
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
}
