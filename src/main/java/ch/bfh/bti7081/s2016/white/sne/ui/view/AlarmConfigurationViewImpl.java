package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.ui.view.components.AlarmSetImpl;

public class AlarmConfigurationViewImpl extends NavigationView implements AlarmConfigurationView{
	private VerticalLayout grid;
	
	private List<AlarmSetImpl> alarmSets;
	private List<AlarmConfigurationViewListener> listeners;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AlarmConfigurationViewImpl() {
		this.alarmSets = new ArrayList<AlarmSetImpl>();
		this.listeners = new ArrayList<AlarmConfigurationViewListener>();
		
		this.grid = new VerticalLayout();
		
		grid.setWidth("100%");
		grid.setHeight("100%");

		grid.setStyleName("dashboard");
		
		this.setWidth(null);
		//grid.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
		
		Button addBtn = new Button("+");
		Button saveBtn = new Button("Speichern");
		Button cancelBtn = new Button("Abbrechen");
		addBtn.setHeight("50px");
		addBtn.setWidth("50px");
		saveBtn.setHeight("50px");
		saveBtn.setWidth("200px");
		cancelBtn.setHeight("50px");
		cancelBtn.setWidth("200px");
		
		addBtn.addClickListener(e -> handleAddClick());
		saveBtn.addClickListener(e -> handleClickSave());
		cancelBtn.addClickListener(e -> handleClickCancel());
		
		VerticalLayout vertical = new VerticalLayout();
		HorizontalLayout h1 = new HorizontalLayout();
		HorizontalLayout h2 = new HorizontalLayout();
		h1.setWidth(100, Unit.PERCENTAGE);
		h2.setWidth(100, Unit.PERCENTAGE);
		h1.addComponent(addBtn);
		h2.addComponent(saveBtn);
		h2.addComponent(cancelBtn);
		vertical.addComponent(grid);
		vertical.addComponent(h1);
		vertical.addComponent(h2);
		
		addTitle();
		this.setContent(vertical);
	}
	
	private void addTitle() {
		HorizontalLayout horizontal = new HorizontalLayout();

		horizontal.setWidth("100%");
		horizontal.setHeight("100%");
		Label type = new Label("Report");
		Label time = new Label("Timeframe");
		Label comp = new Label("Comparator");
		Label warn = new Label("Warning value");
		Label alert = new Label("Alert value");
		Label hidden = new Label(" ");
		
		horizontal.addComponent(type);
		horizontal.addComponent(time);
		horizontal.addComponent(comp);
		horizontal.addComponent(warn);
		horizontal.addComponent(alert);
		horizontal.addComponent(hidden);
		
		horizontal.setComponentAlignment(type, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(time, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(comp, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(warn, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(alert, Alignment.MIDDLE_LEFT);
		horizontal.setComponentAlignment(hidden, Alignment.MIDDLE_LEFT);
		
		grid.addComponent(horizontal);
	}

	@Override
	public void addListener(AlarmConfigurationViewListener listener) {
		listeners.add(listener);
	}

	public void handleAddClick() {
		for(AlarmConfigurationViewListener listener : listeners)
			listener.addClick();
	}
	
	public void handleClickSave() {
		for(AlarmConfigurationViewListener listener : listeners)
			listener.saveClick();
	}

	public void handleClickCancel() {
		for(AlarmConfigurationViewListener listener : listeners)
			listener.cancelClick();
		
	}

	@Override
	public void addAlarmSet(AlarmSetImpl configSet) {
		alarmSets.add(configSet);
		grid.addComponent(configSet);
	}

	@Override
	public void deleteAlarmSet(AlarmSetImpl configSet) {
		alarmSets.remove(configSet);
		grid.removeComponent(configSet);
	}
	
	public List<AlarmSetImpl> getAlarmSets() {
		return this.alarmSets;
	}

	@Override
	public void navigateTo(Component component) {
		getNavigationManager().navigateTo(component);
	}

	public NavigationManager getNatigationManager() {
		return getNavigationManager();
		
	}
}
