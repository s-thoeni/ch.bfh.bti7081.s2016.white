package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.dao.ReportDao;
import ch.bfh.bti7081.s2016.white.sne.dao.ReportDaoImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.enums.DatePair;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class ReportFacadeImpl implements ReportFacade {

	/**
	 * Initialize new logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportFacadeImpl.class);
	
	/**
	 * Data access object for Reports
	 */
	private ReportDao dao;

	/**
	 * Constructor for RepportFaceImpl.
	 * Initializes ReportDao
	 */
	public ReportFacadeImpl() {
		dao = new ReportDaoImpl();
	}

	@Override
	public Report<? extends Record> getReport(ReportType type, Date from, Date to) {
		logger.debug("->");
		logger.debug("<-");
		return getReport(type, from, to, false);
	}
	
	@Override
	public Report<? extends Record> getReport(ReportConfig definition) {
		logger.debug("->");
		logger.debug("<-");
		return getReport(definition.getReportType(), definition.getReportTimeframe(), false);
	}
	
	@Override
	public Report<? extends Record> getReport(ReportConfig definition, boolean calculateSummary) {
		logger.debug("->");
		logger.debug("<-");
		return getReport(definition.getReportType(), definition.getReportTimeframe(), calculateSummary);
	}

	@Override
	public Report<? extends Record> getReport(ReportType type, ReportTimeframe timeframe) {
		logger.debug("->");
		logger.debug("<-");
		return getReport(type, timeframe, false);
	}

	@Override
	public Report<? extends Record> getReport(ReportType type, ReportTimeframe timeframe, boolean calculateSummary) {
		logger.debug("->");
		logger.debug("<-");
		return getReport(type, timeframe.getConcreteDate().getFrom() , timeframe.getConcreteDate().getTo(), calculateSummary);
	}	

	@Override
	public Report<? extends Record> getReport(ReportType type, Date from, Date to, boolean calculateSummary) {
		logger.debug("->");
		
		Report<? extends Record> result = null;
		logger.debug("Switch case");
		switch (type){
		case AVAILABLE_EMPLOYEES:
			logger.debug("AVAILABLE_EMPLOYEES");
			result = dao.getAvailableEmployee(from, to);
			result.setType(ReportType.AVAILABLE_EMPLOYEES);
			break;
		case CASHFLOW:
			logger.debug("CASHFLOW");
			result = dao.getCashFlow(from, to);
			result.setType(ReportType.CASHFLOW);
			break;
		case EFFORT:
			logger.debug("EFFORT");
			result = dao.getEffort(from, to);
			result.setType(ReportType.EFFORT);
			break;
		case INCIDENTS:
			logger.debug("INCIDENTS");
			result =  dao.getIncidents(from, to);
			result.setType(ReportType.INCIDENTS);
			break;
		case PATIENTS:
			logger.debug("PATIENTS");
			result = dao.getPatientCount(from, to);
			result.setType(ReportType.PATIENTS);
			break;
		case SICK_LEAVES:
			logger.debug("SICK_LEAVES");
			result = dao.getSickLeaves(from, to);
			result.setType(ReportType.SICK_LEAVES);
			break;
		}
		
		// FIXME: Add exception handling
		if(result == null) return null;
		result.setFrom(from);
		result.setTo(to);
		
		if(calculateSummary) 
			aggregateSummary(result);
		logger.debug("<-");
		return result;
	}

	@Override
	public List<Report<? extends Record>> getReports(ReportType[] types, Date from, Date to) {
		logger.debug("->");
		
		List<Report<? extends Record>> reports = new ArrayList<Report<? extends Record>>();
		for (ReportType type : types)
			reports.add(getReport(type, from, to));
		return reports;
	}

	@Override
	public List<Report<? extends Record>> getReports(ReportType[] types, ReportTimeframe timeframe) {
		logger.debug("->");
		
		List<Report<? extends Record>> reports = new ArrayList<Report<? extends Record>>();
		for (ReportType type : types)
			reports.add(getReport(type, timeframe));
		logger.debug("<-");
		return reports;
	}

	@Override
	public List<Report<? extends Record>> getReports(ReportType[] types, ReportTimeframe timeframe, boolean calculateSummary) {
		logger.debug("->");
		
		List<Report<? extends Record>> reports = new ArrayList<Report<? extends Record>>();
		for (ReportType type : types)
			reports.add(getReport(type, timeframe, calculateSummary));
		logger.debug("<-");
		return reports;
	}

	@Override
	public List<Report<? extends Record>> getReports(ReportType[] types, Date from, Date to, boolean calculateSummary) {
		logger.debug("->");
		
		List<Report<? extends Record>> reports = new ArrayList<Report<? extends Record>>();
		for (ReportType type : types)
			reports.add(getReport(type, from, to, calculateSummary));
		logger.debug("<-");
		return reports;
	}

	private void aggregateSummary(Report<? extends Record> report) {
		logger.debug("->");
		
		int sum = 0;
		for(Record rec : report.getRecords()){
			sum += rec.getSummary();
		}
		report.setSummary(sum);
		logger.debug("<-");
	}

	@Override
	public Report<? extends Record> getReport(ReportType type, DatePair datePair) {
		logger.debug("->");
		logger.debug("<-");
		return getReport(type, datePair.getFrom(), datePair.getTo());
	}
}