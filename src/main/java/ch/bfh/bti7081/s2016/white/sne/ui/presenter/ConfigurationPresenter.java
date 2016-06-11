package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.MyUI;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

public class ConfigurationPresenter implements ConfigurationView.ConfigurationViewListener {
	private ConfigurationProvider model;
	private ConfigurationViewImpl view;
	private Configuration config;

	public ConfigurationPresenter(ConfigurationProvider model, ConfigurationViewImpl view) {
		this.model = model;
		this.view = view;

		config = model.getConfig();
		int i = 0;

		for (ReportConfig c : config.getReports()) {
			ConfigSetImpl configSet = new ConfigSetImpl(c.getReportType(), c.getReportTimeframe());
			configSet.setId(String.valueOf(i));
			((ConfigSet) configSet).addListener(id -> deleteClick(id));
			view.addConfigSet(configSet);
			i++;
		}

		view.addListener(this);
	}

	@Override
	public void addClick() {
		int i = 0;
		for (ConfigSetImpl c : view.getConfigSets()) {
			i++;
		}
		ConfigSetImpl configSet = new ConfigSetImpl();
		configSet.setId(String.valueOf(i));
		view.addConfigSet(configSet);
		((ConfigSet) configSet).addListener(id -> deleteClick(id));

	}

	@Override
	public void saveClick() {

		// DUMMY DATA
		// FIXME: get user from elsewhere... where is it stored?
		User user = new User("lucas.wirtz");
		List<ReportConfig> configuration = new ArrayList<ReportConfig>();

		for (ConfigSetImpl conf : this.view.getConfigSets()) {
			if (conf.getReportType() != null && conf.getReportTimeframe() != null) {
				configuration.add(new ReportConfig(conf.getReportType(), conf.getReportTimeframe()));
			}
		}
		this.model.setConfig(new Configuration(configuration), user);		

		UI ui = view.getNatigationManager().getUI();
		if(ui instanceof MyUI){
			MyUI sneui = (MyUI) ui;
			DashboardPresenter db = sneui.getDashboard();
			db.refresh(this.model.getConfig());
			view.getNavigationManager().navigateTo(db.getView());
		}else{
			Notification.show("We could not navigate corretly, please refresh the page manually");
		}
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
		List<ConfigSetImpl> configSets = view.getConfigSets();
		
		for(int i =0; i<configSets.size();i++){
			if(configSets.get(i).getId().equals(id)){
				view.deleteConfigSet(configSets.get(i));
			}
		}
		for(int i =0; i<configSets.size();i++){
			configSets.get(i).setId(String.valueOf(i));
		}
	}

}
