package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents information form financial, HR and patient data sources.
 * Data in this class is already aggregated.
 * @author team white
 *
 */
public abstract class Record {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Record.class);
	
	/**
	 * Date of a record object
	 */
	private Date date;
	
	/**
	 * Summary value of the record. May be used in the dashboard view to
	 * show a key performance indicator or short summarized status of a
	 * specific report.
	 */
	private int summary;
	
	/**
	 * Default constuctor
	 * Initializes Report object with empty values
	 */
	public Record() {
		this.summary = 0;
		this.date = new Date();
	}

	/**
	 * Returns record date
	 * @return date
	 */
	public Date getDate() {
		logger.debug("->");
		logger.debug("<-");
		return this.date;
	}

	/**
	 * Assigns date to the record
	 * @param date
	 */
	public void setDate(Date date) {
		logger.debug("->");
		
		this.date = date;
		logger.debug("<-");
	}
	
	/**
	 * Returns the record summary
	 * @return summary as int
	 */
	public int getSummary() {
		logger.debug("->");
		logger.debug("<-");
		return this.summary;
	}

	/**
	 * Sets a summary value to the record
	 * @param summary
	 */
	public void setSummary(int summary) {
		logger.debug("->");
		
		this.summary = summary;
		logger.debug("<-");
	}

}
