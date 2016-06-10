package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.enums.DatePair;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public interface ReportFacade {

	public Report<? extends Record> getReport(ReportType type, Date from, Date to);
	
	public Report<? extends Record> getReport(ReportConfig definition);
	
	public Report<? extends Record> getReport(ReportConfig definition, boolean calculateSummary);
	
	public Report<? extends Record> getReport(ReportType type, ReportTimeframe timeframe);
	
	public Report<? extends Record> getReport(ReportType type, ReportTimeframe timeframe, boolean calculateSummary) throws Exception;
	
	public Report<? extends Record> getReport(ReportType type, Date from, Date to, boolean calculateSummary);
	
	public List<Report<? extends Record>> getReports(ReportType[] types, Date from, Date to);
	
	public List<Report<? extends Record>> getReports(ReportType[] types, ReportTimeframe timeframe);
	
	public List<Report<? extends Record>> getReports(ReportType[] types, ReportTimeframe timeframe, boolean calculateSummary);
	
	public List<Report<? extends Record>> getReports(ReportType[] types, Date from, Date to, boolean calculateSummary);

	public Report<? extends Record> getReport(ReportType type, DatePair datePair);
		
}

