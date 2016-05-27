package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardView.DashboardViewListener;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

public class ConfigurationViewImpl extends NavigationView implements ConfigurationView{
	private VerticalLayout grid;
	
	private List<ConfigSet> configSets;
	private List<ConfigurationViewListener> listeners;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConfigurationViewImpl() {
		this.configSets = new ArrayList<ConfigSet>();
		this.listeners = new ArrayList<ConfigurationViewListener>();
		
		this.grid = new VerticalLayout();
		
		grid.setWidth("100%");
		grid.setHeight("100%");

		grid.setStyleName("dashboard");
		
		this.setWidth(null);
		//grid.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
		
		
		this.setContent(grid);
	}
	
	@Override
	public void addListener(ConfigurationViewListener listener) {
		listeners.add(listener);
		
	}

	@Override
	public void clickOk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addConfigSet(ConfigSetImpl configSet) {
		configSets.add(configSet);
		grid.addComponent(configSet);
	}

	@Override
	public void deleteConfigSet(ConfigSetImpl configSet) {
		configSets.remove(configSet);
		grid.removeComponent(configSet);
	}

}
