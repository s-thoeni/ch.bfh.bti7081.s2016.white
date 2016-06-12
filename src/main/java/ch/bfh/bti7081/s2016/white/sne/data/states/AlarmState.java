package ch.bfh.bti7081.s2016.white.sne.data.states;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Interface for State Pattern
 * 
 * @author sprim5
 *
 */
public interface AlarmState {
	/**
	 * Generate row for use in a Vaadin Table
	 * 
	 * @param alarm
	 * @return Object[] (mainly Strings)
	 */
	public Object[] visualizeAlarm(Alarm alarm);

	/**
	 * Handles Statechanges
	 * 
	 * @param alarm
	 * @throws SneException
	 */
	public void check(Alarm alarm) throws SneException;
}
