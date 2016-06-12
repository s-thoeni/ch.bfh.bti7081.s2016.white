package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
/**
 * Loads and saves the configuration of the user.
 * 
 * @author mcdizzu
 *
 */
public interface ConfigurationDAO {
	
	/**
	 * Returns an empty configuration
	 * 
	 * @return Configuration
	 * @throws SneException
	 */
	public Configuration getConfig() throws SneException;
	
	/**
	 * Reads configuration for specified user from data backend and
	 * returns it as configuration object.
	 * 
	 * @param user - user object
	 * @return Configuration
	 * @throws SneException
	 */
	public Configuration getConfig(User user) throws SneException;
	
	/**
	 * Stores the configuration of given user in data backend persistently
	 * 
	 * @param reports - list of reports
	 * @param user - user object
	 * @throws SneException
	 */
	public void setConfig(List<ReportConfig> reports, User user) throws SneException;
}
