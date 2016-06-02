package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.dao.AlarmDao;
import ch.bfh.bti7081.s2016.white.sne.dao.AlarmDaoImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;

public class AlarmFacadeImpl implements AlarmFacade{
	private AlarmDao dao;
	private User user;
	
	public AlarmFacadeImpl(User user){
		this.user = user;
		dao = new AlarmDaoImpl();
	}
	
	@Override
	public List<Alarm> getAlarms() {
		return dao.getAlarms(this.user);
	}

	@Override
	public void storeAlarms(List<Alarm> alarms) {
		dao.storeAlarms(alarms, this.user);		
	}
}