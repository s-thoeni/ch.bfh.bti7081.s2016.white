package ch.bfh.bti7081.s2016.white.sne.data.states;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
/**
 * NotCheckedState of State Pattern
 * 
 * @author sprim5
 *
 */
public class AlarmNotCheckedState implements AlarmState {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AlarmNotCheckedState.class);
	
	@Override
	/**
	 * Generate row for use in a Vaadin Table
	 * 
	 * @param alarm
	 * @return Object[] (mainly Strings)
	 */
	public Object[] visualizeAlarm(Alarm alarm) {
		logger.debug("->");
		logger.debug("<-");
		// no output when unchecked
		return null;
	}
	
	@Override
	/**
	 * Handles Statechanges
	 * 
	 * @param alarm
	 */
	public void check(Alarm alarm) throws SneException{
		logger.debug("->");
		
		ReportFacade repFac = new ReportFacadeImpl();
		alarm.setAlarmReport(repFac.getReport(alarm.getAlarmReportConfig(), true));
		
		int summaryVal = alarm.getAlarmReport().getSummary();
		if (alarm.getOperator().compareInt(summaryVal, alarm.getErrorValue())) {
			alarm.setAlarmState(new AlarmErrorState());
		} else if (alarm.getOperator().compareInt(summaryVal, alarm.getWarningValue())) {
			alarm.setAlarmState(new AlarmWarningState());
		} else {
			alarm.setAlarmState(new AlarmOkState());
		}
		logger.debug("<-");
	}
	
	@Override
	public String toString() {
		logger.debug("->");
		logger.debug("<-");
		return "NotChecked";
	}

}
