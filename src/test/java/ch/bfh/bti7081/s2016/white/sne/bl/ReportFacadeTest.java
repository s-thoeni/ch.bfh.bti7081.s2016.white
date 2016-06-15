package ch.bfh.bti7081.s2016.white.sne.bl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.bti7081.s2016.white.sne.dao.UserDAOImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public class ReportFacadeTest {

	private ReportFacade facade;
	
	@Before
	public void setUp() throws Exception {
		this.facade = new ReportFacadeImpl();
		
	}

	@Test
	public void testGetReport1() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.ABSENT_EMPLOYEES, ReportTimeframe.CURRENT_WEEK, true);			
		} catch (SneException e) {
			fail(e.getMessage());
		}
		Assert.assertEquals(true, (report != null));
		Assert.assertEquals(true, 0 < report.getSummary());
	}
	
	@Test
	public void testGetReport2() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.AVAILABLE_EMPLOYEES, ReportTimeframe.CURRENT_WEEK, false);			
		} catch (SneException e) {
			fail(e.getMessage());
		}
		Assert.assertEquals(true, (report != null));
		Assert.assertEquals(true, 0 == report.getSummary());
	}
	
	@Test
	public void testGetReport3() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.CASHFLOW, ReportTimeframe.CURRENT_WEEK, false);			
		} catch (SneException e) {
			fail(e.getMessage());
		}
		Assert.assertEquals(true, (report != null));
		Assert.assertEquals(true, 0 == report.getSummary());
	}
	
	@Test
	public void testGetReport4() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.EFFORT, ReportTimeframe.CURRENT_WEEK, false);			
		} catch (SneException e) {
			fail(e.getMessage());
		}
		Assert.assertEquals(true, (report != null));
		Assert.assertEquals(true, 0 == report.getSummary());
	}
	
	@Test
	public void testGetReport5() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.INCIDENTS, ReportTimeframe.CURRENT_WEEK, false);			
		} catch (SneException e) {
			fail(e.getMessage());
		}
		Assert.assertEquals(true, (report != null));
		Assert.assertEquals(true, 0 == report.getSummary());
	}
	
	@Test
	public void testGetReport6() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.PATIENTS, ReportTimeframe.CURRENT_WEEK, false);			
		} catch (SneException e) {
			fail(e.getMessage());
		}
		Assert.assertEquals(true, (report != null));
		Assert.assertEquals(true, 0 == report.getSummary());
	}
	
	@Test
	public void testGetReportInvalidType() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(null, ReportTimeframe.CURRENT_WEEK, false);			
			fail("shouldve trown an exception");
		} catch (SneException e) {
			assert(true);
		}
	}
	
	@Test
	public void testGetReportInvalidFromDate() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.PATIENTS, null, new Date(), false);			
			fail("shouldve trown an exception");
		} catch (SneException e) {
			assert(true);			
		}
	}
	
	@Test
	public void testGetReportInvalidToDate() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.PATIENTS, new Date(), null, false);			
			fail("shouldve trown an exception");
		} catch (SneException e) {
			assert(true);
		}
	}
	
	@Test
	public void testGetReportInvalidTimeframe() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.PATIENTS, null, false);			
			fail("shouldve trown an exception");
		} catch (SneException e) {
			assert(true);
		}
	}
	
	@Test
	public void testGetReportWithSummary() {
		Report<? extends Record> report = null; 
		try {
			report = facade.getReport(ReportType.PATIENTS, ReportTimeframe.CURRENT_WEEK, true);			
		} catch (SneException e) {
			fail(e.getMessage());
		}
		Assert.assertEquals(true, (report != null));
		Assert.assertEquals(true, 0 < report.getSummary());
	}
}
