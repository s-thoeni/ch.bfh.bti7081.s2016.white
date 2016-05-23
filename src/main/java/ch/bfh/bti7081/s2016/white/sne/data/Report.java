package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.List;
import java.util.Date;

public class Report {

	private List<Record> records;
	private String summary;
	private String name;
	private Date from;
	private Date to;
	
	public Report(String name) {
		this.name = name;
	}

	public List<Record> getRecords() {
		return this.records;
	}

	public void setRecords(List<Record> reports) {
		this.records = reports;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
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

}

