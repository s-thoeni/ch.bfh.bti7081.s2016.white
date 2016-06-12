package ch.bfh.bti7081.s2016.white.sne.ui.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Provider for dashboard functionality. Needs to be initialized with a
 * Configuration. Reads configured reports with their summary
 * 
 * @author thons1
 *
 */
public class DashboardProvider {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(DashboardProvider.class);

	/**
	 * business layer from the reports
	 */
	private ReportFacade repFac;

	/**
	 * The configuration of the reports requested by the user
	 */
	private List<ReportConfig> reportConfigurations;

	/**
	 * Instanciate a new Provider
	 * 
	 * @param config
	 *            - the userconfig
	 */
	public DashboardProvider(Configuration config, User user) {
		this.setReportConfigurations(config.getReports());
		this.repFac = new ReportFacadeImpl();
	}

	/**
	 * Retrieve configured reports from the business logic.
	 * 
	 * @return List<Report> - all reports the user asked for
	 */
	public List<Report> getReports() {
		logger.debug("->");
		List<Report> reports = new ArrayList<Report>();
		for (ReportConfig conf : getReportConfigurations()) {
			// System.out.println(conf.getDashboardReportType());
			try {
				reports.add(repFac.getReport(conf.getReportType(), conf.getReportTimeframe(), true));
			} catch (SneException e) {
				logger.error("Error reading report! ", e);
				Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
			}
		}
		logger.debug("<-");
		return reports;
	}

	/**
	 * returns the report corresponding to the given name. Only checks reports
	 * that are configured by the user.
	 * 
	 * @param name
	 * @return Report or null if not found
	 */
	public Report getReportByName(String name) {
		logger.debug("->");
		for (Report report : getReports()) {
			// System.out.println(report.getName());
			if (report.getName().equals(name))
				return report;
		}
		logger.debug("<-");
		return null;
	}

	/**
	 * 
	 * @return the configuration that is currently in use
	 */
	public List<ReportConfig> getReportConfigurations() {
		logger.debug("->");
		logger.debug("<-");
		return reportConfigurations;
	}

	/**
	 * set the config to be used
	 * 
	 * @param reportConfigurations
	 */
	public void setReportConfigurations(List<ReportConfig> reportConfigurations) {
		logger.debug("->");
		logger.debug("<-");
		this.reportConfigurations = reportConfigurations;
	}
}