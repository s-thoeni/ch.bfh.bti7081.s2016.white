package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class AlarmDaoImpl implements AlarmDao{
	
	public List<Alarm> getAlarms(User user){
		// return alarms
		List<Alarm> results = new ArrayList<Alarm>();
		ReportConfig repcon = new ReportConfig(ReportType.AVAILABLE_EMPLOYEES, ReportTimeframe.CURRENT_WEEK);
		Alarm al = new Alarm(repcon, 15, 10, Operator.GREATER_EQUAL );
		results.add(al);
		return results;
	}
	
	@Override
	public void storeAlarms(List<Alarm> alarms, User user) {
		// TODO Auto-generated method stub
	}
}