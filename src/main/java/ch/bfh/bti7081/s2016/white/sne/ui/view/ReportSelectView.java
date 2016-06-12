package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.Date;
import java.util.List;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ReportSelectSetImpl;

/**
 * 
 * @author mcdizzu
 *
 */
public interface ReportSelectView {
	
	public interface ReportSelectViewListener {
		void handleGoClick(ReportType reportType, Date from, Date to) throws SneException;

		void handleGoClick(List<ReportSelectSetImpl> reportSelectSets) throws SneException;
		void handleAddClick();
		
	}
	
	public List<ReportSelectSetImpl>  getReportSelectSetImpl();
	
	public void addReportSelectSet(ReportSelectSetImpl rssi);
	
	public void deleteReportSelectSet(ReportSelectSetImpl rssi);
		
	public void addListener(ReportSelectViewListener listener);

}
