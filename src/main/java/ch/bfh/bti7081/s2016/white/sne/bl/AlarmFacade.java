package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Reads and stores alarms from the respective data access object (DAO)XS
 * 
 * @author thons1
 */
public interface AlarmFacade {

	/**
	 * Read and return alarms for a specific user
	 * 
	 * @return List<Alarms> alarms
	 * 
	 */
	public List<Alarm> getAlarms() throws SneException;

	/**
	 * Persist alarms
	 * 
	 * @param alarms
	 * @throws SneException
	 */
	void storeAlarms(List<Alarm> alarms) throws SneException;
}