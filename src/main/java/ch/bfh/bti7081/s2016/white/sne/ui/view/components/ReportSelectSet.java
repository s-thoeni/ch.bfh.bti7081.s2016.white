package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.Date;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public interface ReportSelectSet {
	public interface ReportSelectSetListener {
		void handleDeleteClick(ReportSelectSetImpl rssi);

	}
	
	public void addListener(ReportSelectSetListener listener);
	
	public ReportType getReportType();
	public Date getFromDate();
	public Date getToDate();
}
