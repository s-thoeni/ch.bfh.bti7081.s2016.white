package ch.bfh.bti7081.s2016.white.sne.data.states;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;

public class AlarmOkState implements AlarmState {

	@Override
	public Object[] visualizeAlarm(Alarm alarm) {
		// no output when ok (For testing reasons there is output)
		ReportConfig config = alarm.getAlarmReportConfig();
		return new Object[]{"Ok", config.getReportType().toString(),alarm.getAlarmReport().getSummary(), alarm.getOperator().toString(),alarm.getWarningValue()};
	}

}
