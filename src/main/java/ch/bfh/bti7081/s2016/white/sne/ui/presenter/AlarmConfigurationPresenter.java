package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.MyUI;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.model.AlarmConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.AlarmConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.AlarmConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.AlarmSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.AlarmSetImpl;

public class AlarmConfigurationPresenter implements AlarmConfigurationView.AlarmConfigurationViewListener {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AlarmConfigurationPresenter.class);
	
	private AlarmConfigurationProvider model;
	private AlarmConfigurationViewImpl view;

	public AlarmConfigurationPresenter(AlarmConfigurationProvider model, AlarmConfigurationViewImpl view)
			throws SneException {
		this.model = model;
		this.view = view;
		
		int i = 0;

		for (Alarm al :  model.getAlarms()) {			
			AlarmSetImpl configSet = new AlarmSetImpl(al);
			
			configSet.setId(String.valueOf(i));
			((AlarmSet) configSet).addListener(id -> deleteClick(id));
			
			view.addAlarmSet(configSet);
			i++;
		}

		view.addListener(this);
	}

	@Override
	public void addClick() {
		logger.debug("->");
		
		int i = view.getAlarmSets().size() -1;
		AlarmSetImpl alarmSet = new AlarmSetImpl();
		alarmSet.setId(String.valueOf(i));
		view.addAlarmSet(alarmSet);
		((AlarmSet) alarmSet).addListener(id -> deleteClick(id));
		logger.debug("<-");
	}

	@Override
	public void saveClick() throws SneException {
		logger.debug("->");
		
		// DUMMY DATA
		// FIXME: get user from elsewhere... where is it stored?
		User user = new User("lucas.wirtz");
		List<Alarm> configuration = new ArrayList<Alarm>();

		for (AlarmSetImpl conf : this.view.getAlarmSets()) {
			ReportTimeframe timeframe = conf.getReportTimeframe();
			ReportType type = conf.getReportType();			
			ReportConfig repConf = new ReportConfig(type, timeframe);
			
			Operator comp = conf.getComparator();
			int error = conf.getErrorValue();
			int warn = conf.getWarnValue();
			
			Alarm newAlarm = new Alarm(repConf, error, warn, comp);
			configuration.add(newAlarm);
			
		}
		this.model.setAlarms(configuration, user);
		MyUI ui = (MyUI) UI.getCurrent();
		ui.getNavigationManager().navigateBack();
		
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
		
		List<AlarmSetImpl> alarmSets = view.getAlarmSets();
		
		for(int i =0; i<alarmSets.size();i++){
			if(alarmSets.get(i).getId().equals(id)){
				view.deleteAlarmSet(alarmSets.get(i));
			}
		}
		for(int i =0; i<alarmSets.size();i++){
			alarmSets.get(i).setId(String.valueOf(i));
		}
		logger.debug("<-");
	}

}
