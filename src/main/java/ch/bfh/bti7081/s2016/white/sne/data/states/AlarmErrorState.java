package ch.bfh.bti7081.s2016.white.sne.data.states;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
/**
 * ErrorState of State Pattern
 * 
 * @author sprim5
 *
 */
public class AlarmErrorState implements AlarmState {

	@Override
	/**
	 * Generate row for use in a Vaadin Table
	 * 
	 * @param alarm
	 * @return Object[] (mainly Strings)
	 */
	public Object[] visualizeAlarm(Alarm alarm) {
		return new Object[]{"Error", alarm.getAlarmReport().getName(),alarm.getAlarmReport().getSummary() +" "+ alarm.getOperator().toString() +" "+ alarm.getErrorValue()};
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
		if (alarm.getOperator().compareInt(summaryVal, alarm.getWarningValue())) {
			alarm.setAlarmState(new AlarmWarningState());
		} else if(!alarm.getOperator().compareInt(summaryVal, alarm.getErrorValue())){
			alarm.setAlarmState(new AlarmOkState());
		}
	}

}
