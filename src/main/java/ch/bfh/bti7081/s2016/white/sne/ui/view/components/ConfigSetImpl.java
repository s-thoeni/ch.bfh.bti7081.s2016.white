package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class ConfigSetImpl extends CustomComponent implements ConfigSet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ConfigSetListener> listeners;
	
	public ConfigSetImpl(ReportType report, ReportTimeframe span) {
		this.listeners = new ArrayList<ConfigSetListener>();
		
		NativeSelect reportSelector = new NativeSelect();
		NativeSelect timeSelector = new NativeSelect();
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
	
	public ConfigSetImpl() {
		this(null, null);
	}
	

	@Override
	public void addListener(ConfigSetListener listener) {
		listeners.add(listener);
		
	}
	
	public void handleDeleteClick() {
		for(ConfigSetListener listener : listeners)
			listener.deleteClick(this.getId());
	}

	
}

