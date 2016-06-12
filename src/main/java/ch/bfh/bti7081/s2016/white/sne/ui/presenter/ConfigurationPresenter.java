package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.MyUI;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;

public class ConfigurationPresenter implements ConfigurationView.ConfigurationViewListener {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigurationPresenter.class);

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
		logger.debug("->");
		
		int i = 0;
		for (ConfigSetImpl c : view.getConfigSets()) {
			i++;
		}
		ConfigSetImpl configSet = new ConfigSetImpl();
		configSet.setId(String.valueOf(i));
		view.addConfigSet(configSet);
		((ConfigSet) configSet).addListener(id -> deleteClick(id));
		
		logger.debug("<-");
	}

	@Override
	public void saveClick() throws SneException {
		logger.debug("->");
		
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

		UI ui = UI.getCurrent();
		if(ui instanceof MyUI){
			MyUI sneui = (MyUI) ui;
			DashboardPresenter db = sneui.getDashboard();
			db.refresh(this.model.getConfig());
			sneui.getNavigationManager().navigateTo(db.getView());
		}else{
			Notification.show("We could not navigate corretly, please refresh the page manually");
		}
		logger.debug("<-");
	}

	@Override
	public void cancelClick() {
		logger.debug("->");
		
		MyUI ui = (MyUI) UI.getCurrent();
		ui.getNavigationManager().navigateBack();
		logger.debug("<-");
	}

	public Component getView() {
		logger.debug("->");
		logger.debug("<-");
		return (Component) this.view;
	}

	@Override
	public void deleteClick(String id) {
		logger.debug("->");
		List<ConfigSetImpl> configSets = view.getConfigSets();
		
		for(int i =0; i<configSets.size();i++){
			if(configSets.get(i).getId().equals(id)){
				view.deleteConfigSet(configSets.get(i));
			}
		}
		for(int i =0; i<configSets.size();i++){
			configSets.get(i).setId(String.valueOf(i));
		}
		logger.debug("<-");
	}

}
