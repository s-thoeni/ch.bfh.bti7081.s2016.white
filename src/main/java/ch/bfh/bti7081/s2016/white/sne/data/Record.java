package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.Date;

/**
 * This class represents information form financial, HR and patient data sources.
 * Data in this class is already aggregated.
 * @author team white
 *
 */
public abstract class Record {

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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getSummary() {
		return this.summary;
	}

	public void setSummary(int summary) {
		this.summary = summary;
	}

}
