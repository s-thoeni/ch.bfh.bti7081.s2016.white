package ch.bfh.bti7081.s2016.white.sne.ui.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.DatePair;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public class ReportProvider {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportProvider.class);
	
	private ReportFacade facade;
	
	public ReportProvider() throws SneException {
		this.facade = new ReportFacadeImpl();
	}
	
	public Report<? extends Record> getReportByTypeAndTimeFrame(ReportType reportType, ReportTimeframe timeFrame) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return this.facade.getReport(reportType, timeFrame);
	}
	
	public Report<? extends Record> getReportByTypeAndDatePair(ReportType reportType, DatePair datePair) throws SneException {
		logger.debug("->");
		logger.debug("<-");
		return this.facade.getReport(reportType, datePair.getFrom(), datePair.getTo());
	}

}
