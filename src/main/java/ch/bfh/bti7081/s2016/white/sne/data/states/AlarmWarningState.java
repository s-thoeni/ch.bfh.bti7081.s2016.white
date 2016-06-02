package ch.bfh.bti7081.s2016.white.sne.data.states;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;

public class AlarmWarningState implements AlarmState {

	@Override
	public Object[] visualizeAlarm(Alarm alarm) {
		ReportConfig config = alarm.getAlarmReportConfig();
		return new Object[]{"Warning", config.getReportType().toString(),alarm.getAlarmReport().getSummary(), alarm.getOperator().toString(),alarm.getWarningValue()};
	}

}
