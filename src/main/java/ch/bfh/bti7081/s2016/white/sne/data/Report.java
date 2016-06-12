package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * This class represents a report. A report is basically an aggregated set of records.
 * All records are within a specific date range, belong a specific type. The report's
 * summary is calculated out of the record values. 
 * @author team white
 *
 * @param <T> have to be a subclass of Record
 */
public class Report<T extends Record> {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Report.class);
	
	/**
	 * Set of records
	 */
	private List<T> records;
	
	/**
	 * report summary (performance indicator)
	 */
	private int summary;
	
	/**
	 * Name of report
	 */
	private String name;
	
	/**
	 * Start of date range
	 */
	private Date from;
	
	/**
	 * End of date range
	 */
	private Date to;
	
	/**
	 * Report type
	 */
	private ReportType type;
	
	/**
	 * Constructs new object with given name
	 * @param name
	 */
	public Report(String name) {
		logger.debug("->");
		
		this.name = name;
		logger.debug("<-");
	}

	public List<T> getRecords() {
		logger.debug("->");
		logger.debug("<-");
		return this.records;
	}

	public void setRecords(List<T> reports) {
		logger.debug("->");
		
		this.records = reports;
		logger.debug("<-");
	}

	public int getSummary() {
		logger.debug("->");
		logger.debug("<-");
		return this.summary;
	}

	public void setSummary(int summary) {
		logger.debug("->");
		
		this.summary = summary;
		logger.debug("<-");
	}
	
	public String getName() {
		logger.debug("->");
		logger.debug("<-");
		return name;
	}

	public void setName(String name) {
		logger.debug("->");
		
		this.name = name;
		logger.debug("<-");
	}

	public Date getFrom() {
		logger.debug("->");
		logger.debug("<-");
		return this.from;
	}

	public void setFrom(Date from) {
		logger.debug("->");
		
		this.from = from;
		logger.debug("<-");
	}

	public Date getTo() {
		logger.debug("->");
		logger.debug("<-");
		return this.to;
	}

	public void setTo(Date to) {
		logger.debug("->");
		
		this.to = to;
		logger.debug("<-");
	}

	public ReportType getType() {
		logger.debug("->");
		logger.debug("<-");
		return type;
	}

	public void setType(ReportType type) {
		logger.debug("->");
		
		this.type = type;
		logger.debug("<-");
	}
}

