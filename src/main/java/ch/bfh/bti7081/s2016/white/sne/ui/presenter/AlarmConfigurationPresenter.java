package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.ui.model.AlarmConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ConfigurationProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.AlarmConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.AlarmConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ConfigurationViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.AlarmSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.AlarmSetImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponent;

public class AlarmConfigurationPresenter implements AlarmConfigurationView.AlarmConfigurationViewListener {
	private AlarmConfigurationProvider model;
	private AlarmConfigurationViewImpl view;
	private List<Alarm> alarms;

	public AlarmConfigurationPresenter(AlarmConfigurationProvider model, AlarmConfigurationViewImpl view) {
		this.model = model;
		this.view = view;

		this.alarms = model.getAlarms();
		int i = 0;

		for (Alarm al : alarms) {
			ReportConfig c = al.getAlarmReportConfig();
			
			AlarmSetImpl configSet = new AlarmSetImpl(c.getReportType(), c.getReportTimeframe());
			
			configSet.setId(String.valueOf(i));
			((AlarmSet) configSet).addListener(id -> deleteClick(id));
			
			view.addAlarmSet(configSet);
			i++;
		}

		view.addListener(this);
	}

	@Override
	public void addClick() {
		int i = 0;
		for (AlarmSetImpl c : view.getAlarmSets()) {
			i++;
		}
		AlarmSetImpl alarmSet = new AlarmSetImpl();
		alarmSet.setId(String.valueOf(i));
		view.addAlarmSet(alarmSet);
		((AlarmSet) alarmSet).addListener(id -> deleteClick(id));

	}

	@Override
	public void saveClick() {

		// DUMMY DATA
		// FIXME: get user from elsewhere... where is it stored?
		User user = new User("lucas.wirtz");
		List<Alarm> configuration = new ArrayList<Alarm>();

		for (AlarmSetImpl conf : this.view.getAlarmSets()) {
			ReportTimeframe timeframe = conf.getReportTimeframe();
			ReportType type = conf.getReportType();
			
			ReportConfig repConf = new ReportConfig(type, timeframe);
			Alarm newAlarm = new Alarm(repConf, 0, 0, null);
			configuration.add(newAlarm);
			
		}
		this.model.setAlarms(alarms, user);
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
		System.out.println("ID: " + id);
		List<AlarmSetImpl> configSets = view.getAlarmSets();
		Iterator<AlarmSetImpl> alarmSetIterator = configSets.iterator();
		AlarmSetImpl c = null;
		int i = 0;
		while (alarmSetIterator.hasNext()) {
			c = alarmSetIterator.next();
			if (c.getId().equals(id)) {
				view.deleteAlarmSet(c);
			}
		}
		while (alarmSetIterator.hasNext()) {
			c = alarmSetIterator.next();
			c.setId(String.valueOf(i++));

		}
	}

}
