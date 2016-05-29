package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ch.bfh.bti7081.s2016.white.sne.dao.ReportDao;
import ch.bfh.bti7081.s2016.white.sne.dao.ReportDaoImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
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
			break;
		case CASHFLOW:
			result = dao.getCashFlow(from, to);
			break;
		case EFFORT:
			result = dao.getEffort(from, to);
			break;
		case ENTRIES_EXITS:
			result = dao.getEntriesExits(from, to);
			break;
		case INCIDENTS:
			result =  dao.getIncidents(from, to);
			break;
		case PATIENS:
			result = dao.getPatientCount(from, to);
			break;
		case SICK_LEAVES:
			result = dao.getSickLeaves(from, to);
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
		//TODO: this is random stuff for now:
		Random rnd = new Random();
		report.setSummary("" + rnd.nextInt(10000));
	}

	@Override
	public Report getReport(ReportType type, DatePair datePair) {
		return getReport(type, datePair.getFrom(), datePair.getTo());
	}
}