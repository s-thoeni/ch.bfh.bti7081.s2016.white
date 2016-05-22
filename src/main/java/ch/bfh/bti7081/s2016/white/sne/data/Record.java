package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.Date;

public abstract class Record {

	private Date from;
	private Date to;
	private int summary;

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

	public int getSummary() {
		return this.summary;
	}

	public void setSummary(int summary) {
		this.summary = summary;
	}

}
