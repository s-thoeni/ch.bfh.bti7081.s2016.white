package ch.bfh.bti7081.s2016.white.sne.data.states;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
/**
 * WarningState of State Pattern
 * 
 * @author sprim5
 *
 */
public class AlarmWarningState implements AlarmState {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AlarmWarningState.class);
	
	@Override
	/**
	 * Generate row for use in a Vaadin Table
	 * 
	 * @param alarm
	 * @return Object[] (mainly Strings)
	 */
	public Object[] visualizeAlarm(Alarm alarm) {
		logger.debug("->");
		
		Label label = new Label(FontAwesome.EXCLAMATION_TRIANGLE.getHtml(), ContentMode.HTML);
		label.addStyleName("Warning");
		logger.debug("<-");
		return new Object[]{label, alarm.getAlarmReport().getName(), alarm.getAlarmReport().getSummary() +" "+ alarm.getOperator().toString() +" "+ alarm.getWarningValue()};
	}
	
	@Override
	/**
	 * Handles Statechanges
	 * 
	 * @param alarm
	 */
	public void check(Alarm alarm){
		logger.debug("->");
		
		ReportFacade repFac = new ReportFacadeImpl();
		alarm.setAlarmReport(repFac.getReport(alarm.getAlarmReportConfig(), true));
		
		int summaryVal = alarm.getAlarmReport().getSummary();
		if (alarm.getOperator().compareInt(summaryVal, alarm.getErrorValue())) {
			alarm.setAlarmState(new AlarmErrorState());
		} else if(!alarm.getOperator().compareInt(summaryVal, alarm.getWarningValue())){
			alarm.setAlarmState(new AlarmOkState());
		}
		logger.debug("<-");
	}
	
	@Override
	public String toString() {
		logger.debug("->");
		logger.debug("<-");
		return "Warning";
	}

}
