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
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Implementation of ReportFacade. Aggregates reports and summaries of reports
 * from data retrieved from a ReportDao.
 * 
 * @author thons1
 */
public class ReportFacadeImpl implements ReportFacade {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportFacadeImpl.class);

	/**
	 * Data access object for Reports
	 */
	private ReportDao dao;

	/**
	 * Constructor for RepportFaceImpl. Initializes ReportDao
	 * @throws SneException 
	 */
	public ReportFacadeImpl() throws SneException {
		dao = new ReportDaoImpl();
	}

	@Override
	public Report<? extends Record> getReport(ReportType type, Date from, Date to) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return getReport(type, from, to, false);
	}

	@Override
	public Report<? extends Record> getReport(ReportConfig definition) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return getReport(definition.getReportType(), definition.getReportTimeframe(), false);
	}

	@Override
	public Report<? extends Record> getReport(ReportConfig definition, boolean calculateSummary) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return getReport(definition.getReportType(), definition.getReportTimeframe(), calculateSummary);
	}

	@Override
	public Report<? extends Record> getReport(ReportType type, ReportTimeframe timeframe) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return getReport(type, timeframe, false);
	}

	@Override
	public Report<? extends Record> getReport(ReportType type, ReportTimeframe timeframe, boolean calculateSummary)
			throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return getReport(type, timeframe.getConcreteDate().getFrom(), timeframe.getConcreteDate().getTo(),
				calculateSummary);
	}

	@Override
	public Report<? extends Record> getReport(ReportType type, Date from, Date to, boolean calculateSummary)
			throws SneException {
		logger.debug("->");
		Report<? extends Record> result = null;
		try {
			logger.debug("Switch case report type");
			logger.debug("Switch case report type");
			switch (type){
			case AVAILABLE_EMPLOYEES:
				logger.debug("type: AVAILABLE_EMPLOYEES");
				result = dao.getAvailableEmployee(from, to);
				result.setType(ReportType.AVAILABLE_EMPLOYEES);
				break;
			case CASHFLOW:
				logger.debug("type: CASHFLOW");
				result = dao.getCashFlow(from, to);
				result.setType(ReportType.CASHFLOW);
				break;
			case EFFORT:
				logger.debug("type: EFFORT");
				result = dao.getEffort(from, to);
				result.setType(ReportType.EFFORT);
				break;
			case INCIDENTS:
				logger.debug("type: INCIDENTS");
				result =  dao.getIncidents(from, to);
				result.setType(ReportType.INCIDENTS);
				break;
			case PATIENTS:
				logger.debug("type: PATIENTS");
				result = dao.getPatientCount(from, to);
				result.setType(ReportType.PATIENTS);
				break;
			case ABSENT_EMPLOYEES:
				logger.debug("type: ABSENT_EMPLOYEES");
				result = dao.getAbsentEmployees(from, to);
				result.setType(ReportType.ABSENT_EMPLOYEES);
				break;
			}
			// FIXME: Add exception handling
			if (result == null)
				throw new SneException("Report not found in database! ", null);
			result.setFrom(from);
			result.setTo(to);

			if (calculateSummary)
				aggregateSummary(result);
		} catch (SneException up) {
			logger.error("error occured during report aggregation");
			throw up;
		}
		logger.debug("<-");
		return result;
	}

	@Override
	public List<Report<? extends Record>> getReports(ReportType[] types, Date from, Date to) throws SneException {
		logger.debug("->");

		List<Report<? extends Record>> reports = new ArrayList<Report<? extends Record>>();
		for (ReportType type : types)
			reports.add(getReport(type, from, to));
		return reports;
	}

	@Override
	public List<Report<? extends Record>> getReports(ReportType[] types, ReportTimeframe timeframe)
			throws SneException {
		logger.debug("->");

		List<Report<? extends Record>> reports = new ArrayList<Report<? extends Record>>();
		for (ReportType type : types)
			reports.add(getReport(type, timeframe));
		logger.debug("<-");
		return reports;
	}

	@Override
	public List<Report<? extends Record>> getReports(ReportType[] types, ReportTimeframe timeframe,
			boolean calculateSummary) throws SneException {
		logger.debug("->");

		List<Report<? extends Record>> reports = new ArrayList<Report<? extends Record>>();
		for (ReportType type : types)
			reports.add(getReport(type, timeframe, calculateSummary));
		logger.debug("<-");
		return reports;
	}

	@Override
	public List<Report<? extends Record>> getReports(ReportType[] types, Date from, Date to, boolean calculateSummary)
			throws SneException {
		logger.debug("->");

		List<Report<? extends Record>> reports = new ArrayList<Report<? extends Record>>();
		for (ReportType type : types)
			reports.add(getReport(type, from, to, calculateSummary));
		logger.debug("<-");
		return reports;
	}

	/**
	 * Aggregates a summary for a given report.
	 * 
	 * @param report
	 */
	private void aggregateSummary(Report<? extends Record> report) {
		logger.debug("->");

		int sum = 0;
		for (Record rec : report.getRecords()) {
			sum += rec.getSummary();
		}
		report.setSummary(sum);
		logger.debug("<-");
	}
}