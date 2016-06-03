package ch.bfh.bti7081.s2016.white.sne.data;

import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;
import ch.bfh.bti7081.s2016.white.sne.data.states.AlarmNotCheckedState;
import ch.bfh.bti7081.s2016.white.sne.data.states.AlarmState;

public class Alarm {
	private ReportConfig alarmReportConfig;
	private Report alarmReport;
	private Operator operator;
	private int errorValue;
	private int warningValue;
	private AlarmState alarmState;
	

	public Alarm(ReportConfig alarmReportConfig, int errorValue, int warningValue, Operator operator) {
		this.alarmReportConfig = alarmReportConfig;
		this.errorValue = errorValue;
		this.warningValue = warningValue;
		this.operator = operator;
		this.alarmState = new AlarmNotCheckedState();
	}

	
	public Object[] visualizeAlarm(){
		this.alarmState.check(this);
		return this.alarmState.visualizeAlarm(this);
	}
	
	public Report getAlarmReport() {
		return this.alarmReport;
	}

	public void setAlarmReport(Report alarmReport) {
		this.alarmReport = alarmReport;
	}

	public int getErrorValue() {
		return this.errorValue;
	}

	public void setErrorValue(int errorValue) {
		this.errorValue = errorValue;
	}

	public int getWarningValue() {
		return this.warningValue;
	}

	public void setWarningValue(int warningValue) {
		this.warningValue = warningValue;
	}

	public ReportConfig getAlarmReportConfig() {
		return this.alarmReportConfig;
	}

	public void setAlarmReportConfig(ReportConfig alarmReportConfig) {
		this.alarmReportConfig = alarmReportConfig;
	}

	public Operator getOperator() {
		return this.operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public void setAlarmState(AlarmState alarmState) {
		this.alarmState = alarmState;
	}
}
