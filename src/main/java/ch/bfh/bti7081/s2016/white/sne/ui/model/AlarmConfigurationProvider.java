package ch.bfh.bti7081.s2016.white.sne.ui.model;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.bl.AlarmFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.AlarmFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public class AlarmConfigurationProvider {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AlarmConfigurationProvider.class);

	private AlarmFacade facade;

	public AlarmConfigurationProvider(User user) throws SneException {
		facade = new AlarmFacadeImpl(user);
	}

	public List<Alarm> getAlarms() throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return facade.getAlarms();
	}

	public void setAlarms(List<Alarm> alarms, User user) throws SneException {
		logger.debug("->");
		facade.storeAlarms(alarms);
		logger.debug("<-");
	}
}
