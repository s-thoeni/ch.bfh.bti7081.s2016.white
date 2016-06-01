package ch.bfh.bti7081.s2016.white.sne.data;

import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;

public class Alarm {
	private Report alarmReport;
	private Operator operator;
	private int errorValue;
	private int warningValue;
	private AlarmState alarmState;

	public Alarm(Report alarmReport, int errorValue, int warningValue, Operator operator) {
		this.alarmReport = alarmReport;
		this.errorValue = errorValue;
		this.warningValue = warningValue;
		this.operator = operator;
		this.alarmState = new AlarmOkState();
	}

//	public void checkAlarmState(){
//		int summaryVal = alarmReport.getSummary();
//		if(this.operator.compareInt(summaryVal, this.errorValue)){
//			this.alarmState = new AlarmErrorState();
//		}else if(this.operator.compareInt(summaryVal, this.warningValue)){
//			this.alarmState = new AlarmWarningState();
//		}else{
//			this.alarmState = new AlarmOkState();
//		}
//		
//	}
	
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

}
