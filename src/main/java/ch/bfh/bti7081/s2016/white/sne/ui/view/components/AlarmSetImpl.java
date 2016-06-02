package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.ArrayList;
import java.util.List;

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
	 * 
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

	public AlarmSetImpl() {
		this(null);
	}

	@Override
	public void addListener(AlarmSetListener listener) {
		listeners.add(listener);

	}

	public void handleDeleteClick() {
		for (AlarmSetListener listener : listeners)
			listener.deleteClick(this.getId());
	}

	public ReportType getReportType() {
		if (reportSelector.getValue() instanceof ReportType) {
			return (ReportType) reportSelector.getValue();
		} else {
			return null;
		}
	}

	public ReportTimeframe getReportTimeframe() {
		if (timeSelector.getValue() instanceof ReportTimeframe) {
			return (ReportTimeframe) timeSelector.getValue();
		} else {
			return null;
		}
	}

	public Operator getComparator() {
		if (compSelector.getValue() instanceof Operator) {
			return (Operator) compSelector.getValue();
		} else {
			return null;
		}
	}

	public int getWarnValue() {
		String uiValue = warningVal.getValue();
		try {
			Integer convertedValue = (Integer) warningVal.getConvertedValue();
			return convertedValue;
		} catch (ConversionException e) {
			Notification.show("Could not convert value: " + uiValue);
			return 0;
		}
	}

	public int getErrorValue() {
		String uiValue = alarmVal.getValue();
		try {
			Integer convertedValue = (Integer) alarmVal.getConvertedValue();
			return convertedValue;
		} catch (ConversionException e) {
			Notification.show("Could not convert value: " + uiValue);
			return 0;
		}
	}

}