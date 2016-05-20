package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.Comparator;

public class Alarm {
	private Report alarmReport;
	private int value;
	private Comparator comparator;

	public Alarm(Report alarmReport, int value, Comparator comparator) {
		this.alarmReport = alarmReport;
		this.value = value;
		this.comparator = comparator;
	}

	public Report getAlarmReport() {
		return this.alarmReport;
	}

	public void setAlarmReport(Report alarmReport) {
		this.alarmReport = alarmReport;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Comparator getComparator() {
		return this.comparator;
	}

	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}

}
