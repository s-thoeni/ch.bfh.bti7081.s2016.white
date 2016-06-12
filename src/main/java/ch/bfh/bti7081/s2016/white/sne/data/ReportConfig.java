package ch.bfh.bti7081.s2016.white.sne.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * represents a report configuration as a set of ReportType and ReportTimeframe. 
 * @author team white
 *
 */
public class ReportConfig {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportConfig.class);
	
	/**
	 * type of report
	 */
	private ReportType reportType;
	
	/**
	 * time range for report
	 */
	private ReportTimeframe reportTimeframe;

	/**
	 * Instantiates ReportConfig objects for given reportType and reportTimeFrame
	 * @param reportType
	 * @param reportTimeframe
	 */
	public ReportConfig(ReportType reportType, ReportTimeframe reportTimeframe) {
		this.reportType = reportType;
		this.reportTimeframe = reportTimeframe;
	}

	public ReportType getReportType() {
		logger.debug("->");
		logger.debug("<-");
		return this.reportType;
	}

	public void setReportType(ReportType dashboardReport) {
		logger.debug("->");
		
		this.reportType = dashboardReport;
		logger.debug("<-");
	}

	public ReportTimeframe getReportTimeframe() {
		logger.debug("->");
		logger.debug("<-");
		return this.reportTimeframe;
	}

	public void setReportTimeframe(ReportTimeframe reportTimeframe) {
		logger.debug("->");
		
		this.reportTimeframe = reportTimeframe;
		logger.debug("<-");
	}
}