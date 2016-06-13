package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.Date;
import java.util.List;

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
		this.name = name;
	}

	public List<T> getRecords() {
		return this.records;
	}

	public void setRecords(List<T> reports) {
		this.records = reports;
	}

	public int getSummary() {
		return this.summary;
	}

	public void setSummary(int summary) {
		this.summary = summary;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFrom() {
		return this.from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return this.to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public ReportType getType() {
		return type;
	}

	public void setType(ReportType type) {
		this.type = type;
	}
}

