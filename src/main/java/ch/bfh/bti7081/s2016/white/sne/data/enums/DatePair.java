package ch.bfh.bti7081.s2016.white.sne.data.enums;

import java.util.Date;

public class DatePair {
	private Date from;

	private Date to;

	public DatePair(Date from, Date to) {
		super();
		this.setFrom(from);
		this.setTo(to);
	}

	public DatePair() {
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

}
