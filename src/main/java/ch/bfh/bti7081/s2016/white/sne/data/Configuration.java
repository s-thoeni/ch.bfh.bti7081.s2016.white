package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.List;

public class Configuration {

	private List<ReportConfig> reports;
	
	/**
	 * Instantiate a empty configuration
	 */
	public Configuration(){
		this.reports = null;
	}
	
	public Configuration(List<ReportConfig> reports) {
		this.reports = reports;
	}

	public void save() {

	}

	public List<ReportConfig> getReports() {
		return this.reports;
	}
	
	public void setReports(List<ReportConfig> reports) {
		this.reports = reports;
	}

}
