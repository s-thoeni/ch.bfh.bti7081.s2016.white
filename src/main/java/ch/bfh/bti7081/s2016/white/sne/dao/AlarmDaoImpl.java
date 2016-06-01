package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;

public class AlarmDaoImpl implements AlarmDao{
	
	public List<Alarm> getAlarms(User user){
		// return alarms
		List<Alarm> results = new ArrayList<Alarm>();
		Alarm al = new Alarm(null, 15, 10, Operator.GREATER_EQUAL );
		results.add(al);
		return results;
	}
	
	public void setAlarms(List<Alarm> alarms){
		//TODO: Doit! Messi!
	}
}