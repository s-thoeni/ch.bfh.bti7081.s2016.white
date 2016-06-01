package ch.bfh.bti7081.s2016.white.sne.data;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class ReportConfig {
	private ReportType reportType;
	private ReportTimeframe reportTimeframe;

	public ReportConfig(ReportType reportType,ReportTimeframe reportTimeframe) {
		this.reportType = reportType;
		this.reportTimeframe = reportTimeframe;
	}

	public ReportType getReportType() {
		return this.reportType;
	}

	public void setReportType(ReportType dashboardReport) {
		this.reportType = dashboardReport;
	}

	public ReportTimeframe getReportTimeframe() {
		return this.reportTimeframe;
	}

	public void setReportTimeframe(ReportTimeframe reportTimeframe) {
		this.reportTimeframe = reportTimeframe;
	}
}