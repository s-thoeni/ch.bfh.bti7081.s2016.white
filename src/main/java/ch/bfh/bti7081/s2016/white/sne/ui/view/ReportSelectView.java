package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.Date;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * 
 * @author mcdizzu
 *
 */
public interface ReportSelectView {
	
	public interface ReportSelectViewListener {
		void go(ReportType reportType, Date from, Date to);
		
	}
		
	public void addListener(ReportSelectViewListener listener);
	
	public void navigateTo(Component component);

}
