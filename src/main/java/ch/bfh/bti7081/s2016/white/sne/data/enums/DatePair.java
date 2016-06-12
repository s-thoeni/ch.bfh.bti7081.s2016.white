package ch.bfh.bti7081.s2016.white.sne.data.enums;

import java.util.Date;

/**
 * Class representing a date range
 * @author team white
 *
 */
public class DatePair {
	
	/**
	 * From date
	 */
	private Date from;

	/**
	 * To date
	 */
	private Date to;

	/**
	 * Initializes a new date range based on two given dates
	 * @param from
	 * @param to
	 */
	public DatePair(Date from, Date to) {
		super();
		this.setFrom(from);
		this.setTo(to);
	}

	/**
	 * Default constructor
	 * Initializes an empty date pair
	 */
	public DatePair() {
	}

	/**
	 * Get end of date range
	 * @return to - end of date range
	 */
	public Date getTo() {
		return to;
	}

	/**
	 * Set end of date range
	 * @param to - end of date range
	 */
	public void setTo(Date to) {
		this.to = to;
	}

	/**
	 * Get start of date range
	 * @return from - start of date range
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * Set start of date range
	 * @param to - start of date range
	 */
	public void setFrom(Date from) {
		this.from = from;
	}

}
