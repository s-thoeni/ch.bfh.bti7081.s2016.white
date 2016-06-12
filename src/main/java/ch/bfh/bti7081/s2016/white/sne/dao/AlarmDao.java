package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Class implementing connection to data backend for alarm configuration
 * @author team white
 *
 */
public interface AlarmDao {
	
	/**
	 * Reads alarms from data backend of a specified user. Returns a list
	 * of alarms.
	 * @param user
	 * @return List<Alarm>
	 * @throws SneException
	 */
	public List<Alarm> getAlarms(User user) throws SneException;

	/**
	 * Stores a list of alarms for specified user in data backend
	 * @param alarms as List<Alarm>
	 * @param user
	 * @throws SneException
	 */
	void storeAlarms(List<Alarm> alarms, User user) throws SneException;
}