package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.enums.DatePair;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Retrieve and aggregate a report from a datasource. May also calculate the
 * summary.
 * 
 * @author thons1
 *
 */
public interface ReportFacade {

	/**
	 * @see getReport(ReportType type, Date from, Date to, boolean
	 *      calculateSummary);
	 * 
	 * @param type
	 *            - type of the report.
	 * @param from
	 *            - from date from wich the report starts
	 * @param to
	 *            - to date where the report ends
	 * @return Report
	 */
	public Report<? extends Record> getReport(ReportType type, Date from, Date to) throws SneException;

	/**
	 * @see getReport(ReportConfig definition, boolean calculateSummary)
	 * 
	 * @param definition
	 *            - definition of the report to be loaded and aggregated
	 * @return Report
	 */
	public Report<? extends Record> getReport(ReportConfig definition) throws SneException;

	/**
	 * Uses a reportConfig object do decide what report to load.
	 * 
	 * @param definition
	 *            - definition of the report to be loaded and aggregated.
	 * @param calculateSummary
	 *            - specifies weather or not a summary should be calculated
	 * @return Report
	 */
	public Report<? extends Record> getReport(ReportConfig definition, boolean calculateSummary) throws SneException;

	/**
	 * @see getReport(ReportType type, Date from, Date to, boolean
	 *      calculateSummary);
	 * 
	 * @param type
	 *            - ReportType to be loaded and aggregated
	 * @param timeframe
	 *            - the time frame (from and to) of the report
	 * @return Report
	 */
	public Report<? extends Record> getReport(ReportType type, ReportTimeframe timeframe) throws SneException;

	/**
	 * @see getReport(ReportType type, Date from, Date to, boolean
	 *      calculateSummary);
	 * 
	 * @param type
	 *            - Report type to be loaded and aggregated
	 * @param timeframe
	 *            - the time frame (from and to) of the report
	 * @param calculateSummary
	 *            - specifies whether or not a summary should be calculated for
	 *            this report.
	 * 
	 * @return Report
	 * @throws Exception
	 */
	public Report<? extends Record> getReport(ReportType type, ReportTimeframe timeframe, boolean calculateSummary)
			throws SneException;

	/**
	 * Loads and return the report identifies by the method signature.
	 * CalculateSummary will determine whether or not a summary shall be
	 * calculated. Defaults to false.
	 * 
	 * @param type
	 *            - Report type to be loaded and aggregated
	 * @param from
	 *            - Date from when the report starts
	 * @param to
	 *            - When the report ends.
	 * @param calculateSummary
	 *            - whether or not a summary should be calculated.
	 * @return Report<Record>
	 * @throws SneException
	 */
	public Report<? extends Record> getReport(ReportType type, Date from, Date to, boolean calculateSummary)
			throws SneException;

	/**
	 * @see getReports(ReportType[] types, Date from, Date to, boolean
	 *      calculateSummary)
	 * 
	 * @param types
	 *            - list of report types
	 * @param from
	 *            - from then the report starts
	 * @param to
	 *            - when the report ends p
	 * @return List<Report>
	 */
	public List<Report<? extends Record>> getReports(ReportType[] types, Date from, Date to) throws SneException;

	/**
	 * * @see getReports(ReportType[] types, Date from, Date to, boolean
	 * calculateSummary)
	 * 
	 * @param types
	 *            - list of report types
	 * @param timeframe
	 *            - time frame of the report. (will be translated to Date from
	 *            and Date to)
	 * @return List<Report>
	 */
	public List<Report<? extends Record>> getReports(ReportType[] types, ReportTimeframe timeframe) throws SneException;

	/**
	 * @see getReports(ReportType[] types, Date from, Date to, boolean
	 *      calculateSummary)
	 * @param types
	 *            - list of ReportType
	 * @param timeframe
	 *            - timeframe of the report
	 * @param calculateSummary
	 *            - whether or not a summary should be calculated. This will be
	 *            applied to all reports.
	 * @return List<Report>
	 */
	public List<Report<? extends Record>> getReports(ReportType[] types, ReportTimeframe timeframe,
			boolean calculateSummary) throws SneException;

	/**
	 * Call @see getReport(ReportType type, Date from, Date to, boolean
	 * calculateSummary) for each ReportType in types.
	 * 
	 * @param types
	 *            - list of ReportType
	 * @param from
	 *            - from when the report should start
	 * @param to
	 *            - when the report should end
	 * @param calculateSummary
	 *            - whether or not a summary should be calculated
	 * @return List<Report>
	 */
	public List<Report<? extends Record>> getReports(ReportType[] types, Date from, Date to, boolean calculateSummary)
			throws SneException;

}