package ch.bfh.bti7081.s2016.white.sne.data.states;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
/**
 * OkState of State Pattern
 * 
 * @author sprim5
 *
 */
public class AlarmOkState implements AlarmState {

	@Override
	/**
	 * Generate row for use in a Vaadin Table
	 * 
	 * @param alarm
	 * @return Object[] (mainly Strings)
	 */
	public Object[] visualizeAlarm(Alarm alarm) {
		// no output when ok (For testing reasons there is output)
		return new Object[]{"OK", alarm.getAlarmReport().getName(),alarm.getAlarmReport().getSummary() +" "+ alarm.getOperator().toString() +" "+ alarm.getWarningValue()};
	}
	
	@Override
	/**
	 * Handles Statechanges
	 * 
	 * @param alarm
	 */
	public void check(Alarm alarm){
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
	}

}
