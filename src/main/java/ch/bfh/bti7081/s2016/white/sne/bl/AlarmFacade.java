package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;

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
	public List<Alarm> getAlarms();

	/**
	 * Persist alarms 
	 * 
	 * @param alarms
	 */
	void storeAlarms(List<Alarm> alarms);
}