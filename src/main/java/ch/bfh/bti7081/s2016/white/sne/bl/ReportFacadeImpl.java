package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.dao.ReportDao;
import ch.bfh.bti7081.s2016.white.sne.dao.ReportDaoImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.enums.DatePair;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class ReportFacadeImpl implements ReportFacade {

	private ReportDao dao;

	public ReportFacadeImpl() {
		dao = new ReportDaoImpl();
	}

	@Override
	public Report getReport(ReportType type, Date from, Date to) {
		return getReport(type, from, to, false);
	}
	
	@Override
	public Report getReport(ReportConfig definition) {
		return getReport(definition.getReportType(), definition.getReportTimeframe(), false);
	}
	
	@Override
	public Report getReport(ReportConfig definition, boolean calculateSummary) {
		return getReport(definition.getReportType(), definition.getReportTimeframe(), calculateSummary);
	}

	@Override
	public Report getReport(ReportType type, ReportTimeframe timeframe) {
		return getReport(type, timeframe, false);
	}

	@Override
	public Report getReport(ReportType type, ReportTimeframe timeframe, boolean calculateSummary) {
		return getReport(type, timeframe.getConcreteDate().getFrom() , timeframe.getConcreteDate().getTo(), calculateSummary);
	}	

	@Override
	public Report getReport(ReportType type, Date from, Date to, boolean calculateSummary) {
		Report result = null;
		switch (type){
		case AVAILABLE_EMPLOYEES:
			result = dao.getAvailableEmployee(from, to);
			result.setType(ReportType.AVAILABLE_EMPLOYEES);
			break;
		case CASHFLOW:
			result = dao.getCashFlow(from, to);
			result.setType(ReportType.CASHFLOW);
			break;
		case EFFORT:
			result = dao.getEffort(from, to);
			result.setType(ReportType.EFFORT);
			break;
		case ENTRIES_EXITS:
			result = dao.getEntriesExits(from, to);
			result.setType(ReportType.ENTRIES_EXITS);
			break;
		case INCIDENTS:
			result =  dao.getIncidents(from, to);
			result.setType(ReportType.INCIDENTS);
			break;
		case PATIENS:
			result = dao.getPatientCount(from, to);
			result.setType(ReportType.PATIENS);
			break;
		case SICK_LEAVES:
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
		return result;
	}

	@Override
	public List<Report> getReports(ReportType[] types, Date from, Date to) {
		List<Report> reports = new ArrayList<Report>();
		for (ReportType type : types)
			reports.add(getReport(type, from, to));
		return reports;
	}

	@Override
	public List<Report> getReports(ReportType[] types, ReportTimeframe timeframe) {
		List<Report> reports = new ArrayList<Report>();
		for (ReportType type : types)
			reports.add(getReport(type, timeframe));
		return reports;
	}

	@Override
	public List<Report> getReports(ReportType[] types, ReportTimeframe timeframe, boolean calculateSummary) {
		List<Report> reports = new ArrayList<Report>();
		for (ReportType type : types)
			reports.add(getReport(type, timeframe, calculateSummary));
		return reports;
	}

	@Override
	public List<Report> getReports(ReportType[] types, Date from, Date to, boolean calculateSummary) {
		List<Report> reports = new ArrayList<Report>();
		for (ReportType type : types)
			reports.add(getReport(type, from, to, calculateSummary));
		return reports;
	}

	private void aggregateSummary(Report report) {
		int sum = 0;
		for(Record rec : report.getRecords()){
			sum += rec.getSummary();
		}
		report.setSummary(sum);
	}

	@Override
	public Report getReport(ReportType type, DatePair datePair) {
		return getReport(type, datePair.getFrom(), datePair.getTo());
	}
}