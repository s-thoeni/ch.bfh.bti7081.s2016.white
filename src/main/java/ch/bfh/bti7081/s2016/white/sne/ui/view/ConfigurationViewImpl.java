package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;

import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardView.DashboardViewListener;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;




public class ConfigurationViewImpl extends CustomComponent implements ConfigurationView{
	private GridLayout grid;
	
	private List<ConfigSet> configSets;
	private List<ConfigurationViewListener> listeners;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConfigurationViewImpl() {
		this.configSets = new ArrayList<ConfigSet>();
		this.listeners = new ArrayList<ConfigurationViewListener>();
		
		this.grid = new GridLayout(1, 6);
		grid.setMargin(false);
		grid.setSpacing(true);
		grid.setWidth("100%");
		grid.setHeight("100%");

		grid.setStyleName("dashboard");
		
		this.setWidth(null);

		
		setCompositionRoot(grid);
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
