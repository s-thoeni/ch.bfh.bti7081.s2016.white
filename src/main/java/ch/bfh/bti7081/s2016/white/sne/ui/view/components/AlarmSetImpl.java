package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class AlarmSetImpl extends CustomComponent implements AlarmSet {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AlarmSetImpl.class);
	
	/**
	 * Class serial ID
	 */
	private static final long serialVersionUID = 1L;
	private List<AlarmSetListener> listeners;

	private NativeSelect reportSelector = new NativeSelect();
	private NativeSelect timeSelector = new NativeSelect();
	private NativeSelect compSelector = new NativeSelect();
	private TextField alarmVal = new TextField();
	private TextField warningVal = new TextField();

	public AlarmSetImpl(Alarm alarm) {
		this.listeners = new ArrayList<AlarmSetListener>();

		Button removeBtn = new Button("-");

		ReportType[] types = ReportType.values();

		for (ReportType type : types) {
			reportSelector.addItem(type);
		}

		reportSelector.setNullSelectionAllowed(true);
		reportSelector.setStyleName("configselect");

		ReportTimeframe[] times = ReportTimeframe.values();
		for (ReportTimeframe time : times) {
			timeSelector.addItem(time);
		}

		Operator[] op = Operator.values();
		for (Operator o : op) {
			compSelector.addItem(o);
		}

		reportSelector.setNullSelectionAllowed(true);
		reportSelector.setStyleName("configselect");

		timeSelector.setNullSelectionAllowed(true);
		timeSelector.setStyleName("configselect");

		compSelector.setNullSelectionAllowed(false);
		compSelector.setStyleName("configselect");

		if (alarm != null) {
			reportSelector.select(alarm.getAlarmReportConfig().getReportType());
			timeSelector.select(alarm.getAlarmReportConfig().getReportTimeframe());
			alarmVal.setValue(String.valueOf(alarm.getErrorValue()));
			warningVal.setValue(String.valueOf(alarm.getWarningValue()));
			compSelector.select(alarm.getOperator());
		}else{
			alarmVal.setValue("0");
			warningVal.setValue("0");			
		}

		alarmVal.setConverter(Integer.class);
		warningVal.setConverter(Integer.class);

		removeBtn.setHeight("50px");
		removeBtn.setWidth("50px");

		removeBtn.addClickListener(e -> handleDeleteClick());

		HorizontalLayout horizontal = new HorizontalLayout();

		horizontal.setWidth("100%");
		horizontal.setHeight("100%");
		horizontal.addComponent(reportSelector);
		horizontal.addComponent(timeSelector);
		horizontal.addComponent(compSelector);
		horizontal.addComponent(warningVal);
		horizontal.addComponent(alarmVal);
		horizontal.addComponent(removeBtn);

		horizontal.setComponentAlignment(reportSelector, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(timeSelector, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(compSelector, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(warningVal, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(alarmVal, Alignment.MIDDLE_LEFT);

		setCompositionRoot(horizontal);
	}

	/**
	 * Default constructor
	 */
	public AlarmSetImpl() {
		this(null);
	}

	@Override
	public void addListener(AlarmSetListener listener) {
		logger.debug("->");
		
		listeners.add(listener);
		logger.debug("<-");
	}

	public void handleDeleteClick() {
		logger.debug("->");
		
		for (AlarmSetListener listener : listeners)
			listener.deleteClick(this.getId());
		logger.debug("<-");
	}

	public ReportType getReportType() {
		logger.debug("->");
		
		if (reportSelector.getValue() instanceof ReportType) {
			logger.debug("<-");
			return (ReportType) reportSelector.getValue();
		} else {
			logger.debug("<-");
			return null;
		}
	}

	public ReportTimeframe getReportTimeframe() {
		logger.debug("->");
		
		if (timeSelector.getValue() instanceof ReportTimeframe) {
			logger.debug("<-");
			return (ReportTimeframe) timeSelector.getValue();
		} else {
			logger.debug("<-");
			return null;
		}
	}

	public Operator getComparator() {
		logger.debug("->");
		
		if (compSelector.getValue() instanceof Operator) {
			logger.debug("<-");
			return (Operator) compSelector.getValue();
		} else {
			logger.debug("<-");
			return null;
		}
	}

	public int getWarnValue() {
		logger.debug("->");
		
		String uiValue = warningVal.getValue();
		try {
			Integer convertedValue = (Integer) warningVal.getConvertedValue();
			logger.debug("<-");
			return convertedValue;
		} catch (ConversionException e) {
			Notification.show("Could not convert value: " + uiValue);
			logger.debug("<-");
			return 0;
		}
	}

	public int getErrorValue() {
		logger.debug("->");
		
		String uiValue = alarmVal.getValue();
		try {
			Integer convertedValue = (Integer) alarmVal.getConvertedValue();
			logger.debug("<-");
			return convertedValue;
		} catch (ConversionException e) {
			Notification.show("Could not convert value: " + uiValue);
			logger.debug("<-");
			return 0;
		}
	}
}