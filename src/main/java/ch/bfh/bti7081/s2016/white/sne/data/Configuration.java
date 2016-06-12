package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing configuration for dashboard
 * @author team white
 *
 */
public class Configuration {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Configuration.class);

	/**
	 * Report configurations
	 */
	private List<ReportConfig> reports;
	
	/**
	 * Default Constructor
	 * Instantiate a empty configuration
	 */
	public Configuration(){
		this.reports = null;
	}
	
	/**
	 * Instantiate a configuration based on a list of ReportConfig objects
	 * @param reports - List of ReportConfig-objects
	 */
	public Configuration(List<ReportConfig> reports) {
		this.reports = reports;
	}

	
	public void save() {
		logger.debug("->");
		logger.debug("<-");
	}

	/**
	 * Get current configuration
	 * @return reports as List<ReportConfig>
	 */
	public List<ReportConfig> getReports() {
		logger.debug("->");
		logger.debug("<-");
		return this.reports;
	}
	
	/**
	 * Set configuration
	 * @param reports
	 */
	public void setReports(List<ReportConfig> reports) {
		logger.debug("->");
		
		this.reports = reports;
		logger.debug("<-");
	}

}
