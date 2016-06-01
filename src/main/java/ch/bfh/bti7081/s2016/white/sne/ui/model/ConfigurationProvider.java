package ch.bfh.bti7081.s2016.white.sne.ui.model;

import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;


public class ConfigurationProvider {
	private ConfigurationFacade facade;
	private Configuration config;
	
	public ConfigurationProvider(User user) {
		facade = new ConfigurationFacadeImpl();
		this.config = facade.getConfig(user);
	}
	
	public Configuration getConfig() {
		return config;
	}
}
