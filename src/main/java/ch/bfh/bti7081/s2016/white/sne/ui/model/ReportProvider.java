package ch.bfh.bti7081.s2016.white.sne.ui.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.DatePair;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class ReportProvider {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportProvider.class);
	
	private ReportFacade facade;
	
	public ReportProvider() {
		this.facade = new ReportFacadeImpl();
	}
	
	public Report getReportByTypeAndTimeFrame(ReportType reportType, ReportTimeframe timeFrame) {
		logger.debug("->");
		logger.debug("<-");
		return this.facade.getReport(reportType, timeFrame);
	}
	
	public Report getReportByTypeAndDatePair(ReportType reportType, DatePair datePair) {
		logger.debug("->");
		logger.debug("<-");
		return this.facade.getReport(reportType, datePair);
	}

}
