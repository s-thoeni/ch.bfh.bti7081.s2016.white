package ch.bfh.bti7081.s2016.white.sne.bl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ch.bfh.bti7081.s2016.white.sne.dao.UserDAOImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlarmFacadeTest {

	private AlarmFacade facade;
	private Alarm alarm;
	
	@Before
	public void setUp() throws Exception {
		User user = new User("kevin.meier");
		this.facade = new AlarmFacadeImpl(user);		
		
		// the alarm we are testing:
		alarm = new Alarm(new ReportConfig(ReportType.ABSENT_EMPLOYEES, ReportTimeframe.CURRENT_WEEK), 10, 20, Operator.GREATER);
	}

	/**
	 * this needs to be performed first. So we at least have an alarm configured
	 */
	@Test
	public void testAddAlarm() {
		
		List<Alarm> alarms = new ArrayList<Alarm>();
		// This alarm should trigger! 
		alarms.add(alarm);
		try {
			facade.storeAlarms(alarms);
		} catch (SneException e) {
			fail(e.getMessage());
		}		
	}
	
	@Test
	public void testbGetAlarm() {
		
		List<Alarm> alarms = null;
		try {
			alarms = facade.getAlarms();
		} catch (SneException e) {
			fail(e.getMessage());
		}		
		Assert.assertTrue(alarms.get(0) != null);
		Assert.assertTrue(alarms.get(0).getAlarmReportConfig().getReportType() == ReportType.ABSENT_EMPLOYEES);		
	}
	
	@Test
	public void testcRemoveAlarm() {
		
		List<Alarm> alarms = new ArrayList<Alarm>();
	
		try {
			facade.storeAlarms(alarms);
		} catch (SneException e) {
			fail(e.getMessage());
		}		
	}
	
	@Test
	public void testdGetEmptyAlarm() {
		
		List<Alarm> alarms = null;
		try {
			alarms = facade.getAlarms();
		} catch (SneException e) {
			fail(e.getMessage());
		}		
		Assert.assertTrue(alarms.size() == 0);
	}
	
	@Test
	public void testcStoreEmptyAlarms() {
		
		try {
			// Storing null is equivalent to removing all alarms!
			facade.storeAlarms(null);
		
		} catch (SneException e) {
			// Dont throw an exception for that please! 
			fail("Shouldve thrown an exception");
		}		
	}
}
