package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public interface ReportFacade {

	public Report getReport(ReportType type, Date from, Date to);
	
	public Report getReport(ReportType type, ReportTimeframe timeframe);
	
	public Report getReport(ReportType type, ReportTimeframe timeframe, boolean calculateSummary) throws Exception;
	
	public Report getReport(ReportType type, Date from, Date to, boolean calculateSummary);
	
	public List<Report> getReports(ReportType[] types, Date from, Date to);
	
	public List<Report> getReports(ReportType[] types, ReportTimeframe timeframe);
	
	public List<Report> getReports(ReportType[] types, ReportTimeframe timeframe, boolean calculateSummary);
	
	public List<Report> getReports(ReportType[] types, Date from, Date to, boolean calculateSummary);
		
}

