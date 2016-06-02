package ch.bfh.bti7081.s2016.white.sne.ui.model;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.bl.AlarmFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.AlarmFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;


public class AlarmConfigurationProvider {
	private AlarmFacade facade;
	
	public AlarmConfigurationProvider(User user) {
		facade = new AlarmFacadeImpl(user);
	}
	
	public List<Alarm> getAlarms() {
		return facade.getAlarms();
	}
	
	public void setAlarms(List<Alarm> alarms, User user) {
		facade.storeAlarms(alarms);
	}
}
