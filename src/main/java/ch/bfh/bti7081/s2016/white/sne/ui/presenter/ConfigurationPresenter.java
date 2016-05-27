package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration.DashboardReportConfig;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

public class ConfigurationPresenter implements ConfigurationView.ConfigurationViewListener {
	ConfigurationProvider model;
	ConfigurationView view;
	VerticalLayout layout;
	
	public ConfigurationPresenter(ConfigurationProvider model, ConfigurationView view) {
		this.model = model;
		this.view = view;
		
		Configuration config = model.getConfig();
		
		for(DashboardReportConfig c: config.getReports()){
			ConfigSetImpl configSet = new ConfigSetImpl(c.getDashboardReportType(), c.getDashboardReportTime());
			view.addConfigSet(configSet);
		}
	}

	@Override
	public void saveClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClick(String id) {
		// TODO Auto-generated method stub
		
	}
	
	public Component getView() {
		return (Component) this.view;
	}
	
	
}
