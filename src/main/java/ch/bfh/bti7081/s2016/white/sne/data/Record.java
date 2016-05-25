package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.Date;

public abstract class Record {

	private Date date;
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
