package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.dao.ConfigurationDAO;
import ch.bfh.bti7081.s2016.white.sne.dao.ConfigurationDAOImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Facade-class for user configuration. Gets configuration from DAO and provides
 * it to Model (UI).
 * 
 * @author team white
 *
 */
public class ConfigurationFacadeImpl implements ConfigurationFacade {

	/**
	 * Initialize new logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigurationFacadeImpl.class);

	/**
	 * Data access object for Configurations
	 */
	private ConfigurationDAO dao;

	public ConfigurationFacadeImpl() throws SneException {
		super();
		dao = new ConfigurationDAOImpl();
	}

	/**
	 * Returns configuration for specified user.
	 * 
	 * @param user
	 * @return Configuration
	 * @throws SneException
	 */
	@Override
	public Configuration getConfig(User user) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return dao.getConfig(user);
	}

	/**
	 * Stores the configuration for given user persistently.
	 * 
	 * @param reports
	 * @param user
	 * @throws SneException
	 */
	@Override
	public void setConfig(List<ReportConfig> reports, User user) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		dao.setConfig(reports, user);
	}

}
