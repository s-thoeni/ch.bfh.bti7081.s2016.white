package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.Date;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class ConfigSetImpl extends CustomComponent implements ConfigSet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConfigSetImpl() {

		ComboBox reportSelector = new ComboBox("Select your report type");
		ComboBox timeSelector = new ComboBox("Select timespan");

		
		ReportType[] types = ReportType.values();
		for(ReportType type: types) {
			reportSelector.addItem(type);
		}
				
		reportSelector.setInputPrompt("Report type");
		reportSelector.setWidth(100.0f, Unit.PERCENTAGE);
		reportSelector.setNullSelectionAllowed(false);
		
		ReportTimeframe[] times = ReportTimeframe.values();
		for(ReportTimeframe time: times) {
			timeSelector.addItem(time);
		}
		
		timeSelector.setInputPrompt("Timespan");
		timeSelector.setWidth(80.0f, Unit.PERCENTAGE);
		reportSelector.setNullSelectionAllowed(false);
		

		HorizontalLayout horizontal = new HorizontalLayout();
		
		horizontal.setWidth(40.0f, Unit.PERCENTAGE);
		horizontal.addComponent(reportSelector);
		horizontal.addComponent(timeSelector);
		horizontal.setComponentAlignment(reportSelector, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(timeSelector, Alignment.MIDDLE_RIGHT);

		setCompositionRoot(horizontal);

	}
	
	public ConfigSetImpl(ReportType report, ReportTimeframe span) {

		ComboBox reportSelector = new ComboBox("Select your report type");
		ComboBox timeSelector = new ComboBox("Select timespan");

		
		ReportType[] types = ReportType.values();
		for(ReportType type: types) {
			reportSelector.addItem(type);
		}
				
		reportSelector.setInputPrompt("Report type");
		reportSelector.setWidth(100.0f, Unit.PERCENTAGE);
		reportSelector.setNullSelectionAllowed(false);
		reportSelector.select(report);
		
		ReportTimeframe[] times = ReportTimeframe.values();
		for(ReportTimeframe time: times) {
			timeSelector.addItem(time);
		}
		
		timeSelector.setInputPrompt("Timespan");
		timeSelector.setWidth(80.0f, Unit.PERCENTAGE);
		timeSelector.setNullSelectionAllowed(false);
		timeSelector.select(span);
		

		HorizontalLayout horizontal = new HorizontalLayout();
		
		horizontal.setWidth(40.0f, Unit.PERCENTAGE);
		horizontal.addComponent(reportSelector);
		horizontal.addComponent(timeSelector);
		horizontal.setComponentAlignment(reportSelector, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(timeSelector, Alignment.MIDDLE_RIGHT);

		setCompositionRoot(horizontal);

	}
}

