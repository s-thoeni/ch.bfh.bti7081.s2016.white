package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.List;

public class Dashboard {
	private List<Report> summaries;
	private Configuration configuration;

	public Dashboard(List<Report> summaries, Configuration configuration) {
		this.summaries = summaries;
		this.configuration = configuration;
	}

	public void checkAlarms() {

	}

	public void displaySummaries() {

	}

	public void aggregateReports() {

	}

	public List<Report> getSummaries() {
		return this.summaries;
	}

	public void setSummaries(List<Report> summaries) {
		this.summaries = summaries;
	}

	public Configuration getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
}
