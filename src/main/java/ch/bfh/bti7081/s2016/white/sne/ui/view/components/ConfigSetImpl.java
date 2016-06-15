package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class ConfigSetImpl extends CustomComponent implements ConfigSet{
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigSetImpl.class);
	
	/**
	 * Class serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of listeners for the configSetImpl class
	 */
	private List<ConfigSetListener> listeners;

	private NativeSelect reportSelector = new NativeSelect();
	private NativeSelect timeSelector = new NativeSelect();
	
	public ConfigSetImpl(ReportType report, ReportTimeframe span) {
		this.listeners = new ArrayList<ConfigSetListener>();
				
		Button removeBtn = new Button("-");
		
		ReportType[] types = ReportType.values();

		for(ReportType type: types) {
			reportSelector.addItem(type);
		}

		reportSelector.setNullSelectionAllowed(true);
		reportSelector.setStyleName("configselect");
		
		
		ReportTimeframe[] times = ReportTimeframe.values();
		for(ReportTimeframe time: times) {
			timeSelector.addItem(time);
		}			

		timeSelector.setNullSelectionAllowed(true);
		timeSelector.setStyleName("configselect");
		
		reportSelector.select(report);
		timeSelector.select(span);

		 
		removeBtn.setHeight("50px");
		removeBtn.setWidth("50px");

		removeBtn.addClickListener(e -> handleDeleteClick());
		
		HorizontalLayout horizontal = new HorizontalLayout();
		
		horizontal.setWidth("100%");
		horizontal.setHeight("100%");
		horizontal.addComponent(reportSelector);
		horizontal.addComponent(timeSelector);
		horizontal.addComponent(removeBtn);
		
		horizontal.setComponentAlignment(reportSelector, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(timeSelector, Alignment.MIDDLE_LEFT);

		setCompositionRoot(horizontal);
	}
	
	/**
	 * Default constructor
	 */
	public ConfigSetImpl() {
		this(null, null);
	}
	

	@Override
	public void addListener(ConfigSetListener listener) {
		logger.debug("->");
		
		listeners.add(listener);
		logger.debug("<-");
	}
	
	/**
	 * Notifier for click on the "-" Button.
	 */
	public void handleDeleteClick() {
		logger.debug("->");
		
		for(ConfigSetListener listener : listeners)
			listener.deleteClick(this.getId());
		logger.debug("<-");
	}
	/** 
	 * Return the report type selected at the ConfigSet
	 * 
	 * @return The report type from the type selector.
	 */
	public ReportType getReportType(){
		logger.debug("->");
		if(reportSelector.getValue() instanceof ReportType){
			logger.debug("<-");
			return (ReportType) reportSelector.getValue();
		} else{
			logger.debug("<-");
			return null;
		}
	}
	
	/**
	 * Return the timeframe selected at the ConfigSet
	 * 
	 * @return The timeframe from the timeframe selector.
	 */
	public ReportTimeframe getReportTimeframe(){
		logger.debug("->");
		
		if(timeSelector.getValue() instanceof ReportTimeframe){
			logger.debug("<-");
			return (ReportTimeframe) timeSelector.getValue();
		} else{
			logger.debug("<-");
			return null;
		}
	}
}
