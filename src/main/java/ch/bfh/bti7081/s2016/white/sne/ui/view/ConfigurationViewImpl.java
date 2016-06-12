package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

public class ConfigurationViewImpl extends NavigationView implements ConfigurationView {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigurationViewImpl.class);
	
	/**
	 * UI Grid Layout
	 */
	private VerticalLayout grid;
	
	private List<ConfigSetImpl> configSets;
	private List<ConfigurationViewListener> listeners;
	
	/**
	 * Class serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	public ConfigurationViewImpl() {
		this.configSets = new ArrayList<ConfigSetImpl>();
		this.listeners = new ArrayList<ConfigurationViewListener>();
		
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
		
		this.setContent(vertical);
	}
	
	@Override
	public void addListener(ConfigurationViewListener listener) {
		logger.debug("->");
		
		listeners.add(listener);
		logger.debug("<-");
	}

	public void handleAddClick() {
		logger.debug("->");
		
		for(ConfigurationViewListener listener : listeners)
			listener.addClick();
		logger.debug("<-");
	}
	
	public void handleClickSave() {
		logger.debug("->");
		
		for(ConfigurationViewListener listener : listeners)
			listener.saveClick();
		logger.debug("<-");
	}

	public void handleClickCancel() {
		logger.debug("->");
		
		for(ConfigurationViewListener listener : listeners)
			listener.cancelClick();
		logger.debug("<-");
	}

	@Override
	public void addConfigSet(ConfigSetImpl configSet) {
		logger.debug("->");
		
		configSets.add(configSet);
		grid.addComponent(configSet);
		logger.debug("<-");
	}

	@Override
	public void deleteConfigSet(ConfigSetImpl configSet) {
		logger.debug("->");
		
		configSets.remove(configSet);
		grid.removeComponent(configSet);
		logger.debug("<-");
	}
	
	public List<ConfigSetImpl> getConfigSets() {
		logger.debug("->");
		logger.debug("<-");
		return this.configSets;
	}

	@Override
	public void navigateTo(Component component) {
		logger.debug("->");
		logger.debug("<-");
		getNavigationManager().navigateTo(component);
	}

	public NavigationManager getNavigationManager() {
		logger.debug("->");
		logger.debug("<-");
		return super.getNavigationManager();
	}
}
