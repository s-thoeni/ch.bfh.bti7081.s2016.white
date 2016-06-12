package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.dao.AlarmDao;
import ch.bfh.bti7081.s2016.white.sne.dao.AlarmDaoImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Reads and stores alarms from the respective data access object (DAO)XS
 * 
 * @author thons1
 */
public class AlarmFacadeImpl implements AlarmFacade {
	/**
	 * Initialize new logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AlarmFacadeImpl.class);

	/**
	 * Data access object for Alarms
	 */
	private AlarmDao dao;

	/**
	 * Current user information
	 */
	private User user;

	/**
	 * Create new facade instance. User is the mandatory user configuration.
	 * 
	 * @param user
	 * @throws SneException
	 */
	public AlarmFacadeImpl(User user) throws SneException {
		logger.debug("->");

		this.user = user;
		dao = new AlarmDaoImpl();
		logger.debug("<-");
	}

	/**
	 * Returns a list of set alarms for the specified user persistently.
	 * 
	 * @return List<Alarm>
	 * @throws SneException
	 */
	@Override
	public List<Alarm> getAlarms() throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return dao.getAlarms(this.user);
	}

	/**
	 * Stores a list of alarms for the specified user persistently.
	 * 
	 * @param alarms
	 * @throws SneException
	 */
	@Override
	public void storeAlarms(List<Alarm> alarms) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		dao.storeAlarms(alarms, this.user);
	}
}