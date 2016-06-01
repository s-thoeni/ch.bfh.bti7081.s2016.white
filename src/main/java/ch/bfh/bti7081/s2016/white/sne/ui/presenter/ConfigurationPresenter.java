package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.Iterator;
import java.util.List;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponent;

public class ConfigurationPresenter implements ConfigurationView.ConfigurationViewListener {
	private ConfigurationProvider model;
	private ConfigurationViewImpl view;
	private VerticalLayout layout;
	private Configuration config;
	
	public ConfigurationPresenter(ConfigurationProvider model, ConfigurationViewImpl view) {
		this.model = model;
		this.view = view;
		
		config = model.getConfig();
		int i = 0;
		
		for(ReportConfig c: config.getReports()){
			ConfigSetImpl configSet = new ConfigSetImpl(c.getReportType(), c.getReportTimeframe());
			configSet.setId(String.valueOf(i));
			((ConfigSet)configSet).addListener(id -> deleteClick(id));
			view.addConfigSet(configSet);
			i++;
		}
		
		view.addListener(this);
	}
	
	
	@Override
	public void addClick() {
		int i=0;
		for(ConfigSetImpl c: view.getConfigSets()){
			i++;
		}	

		ConfigSetImpl configSet = new ConfigSetImpl();
		configSet.setId(String.valueOf(i));
		view.addConfigSet(configSet);	
		((ConfigSet)configSet).addListener(id -> deleteClick(id));
		
	}
	
	@Override
	public void saveClick() {
		
		
		this.view.getNatigationManager().navigateBack();
	}

	@Override
	public void cancelClick() {
		this.view.getNatigationManager().navigateBack();
	}
	
	public Component getView() {
		return (Component) this.view;
	}

	@Override
	public void deleteClick(String id) {
		System.out.println("ID: "+id);
		List<ConfigSetImpl> configSets = view.getConfigSets();
		Iterator<ConfigSetImpl> configSetIterator = configSets.iterator();
		ConfigSetImpl c = null;
		int i = 0;
		while(configSetIterator.hasNext()) {
			c = configSetIterator.next();
			if (c.getId().equals(id)) {
				view.deleteConfigSet(c);
			} 
		}
		while(configSetIterator.hasNext()) {
			c = configSetIterator.next();
			c.setId(String.valueOf(i++));
			
		}
	}


	
}
