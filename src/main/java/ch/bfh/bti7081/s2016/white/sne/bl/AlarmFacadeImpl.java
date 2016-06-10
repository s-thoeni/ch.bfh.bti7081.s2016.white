package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.dao.AlarmDao;
import ch.bfh.bti7081.s2016.white.sne.dao.AlarmDaoImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;

/**
 * Reads and stores alarms from the respective data access object (DAO)XS
 * 
 * @author thons1
 */
public class AlarmFacadeImpl implements AlarmFacade{
	
	/**
	 * Data access object
	 */
	private AlarmDao dao;
	
	/**
	 * Current user information
	 */
	private User user;
	
	/**
	 * Create new facade instance. user is the mandatory user configuration
	 * 
	 * @param user
	 */
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