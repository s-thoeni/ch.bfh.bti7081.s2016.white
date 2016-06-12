package ch.bfh.bti7081.s2016.white.sne.data.enums;

import java.util.Arrays;
import java.util.List;

/**
 * All available reports should be registered here.
 * 
 * @author thons1
 *
 */
public enum ReportType {
	AVAILABLE_EMPLOYEES(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.TABULAR},"Available Employees"),
	ABSENT_EMPLOYEES(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.PIE_CHART,ReportStyle.TABULAR},"Absences"),
	INCIDENTS(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.PIE_CHART,ReportStyle.TABULAR},"Incidents"),
	PATIENTS(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.TABULAR},"Patients"),
	EFFORT(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.TABULAR},"Effort"), 
	CASHFLOW(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.TABULAR},"Cashflow");
	
	private ReportType(ReportStyle[] reportStyles, String seriesIndicator) {
		this.reportStyles = Arrays.asList(reportStyles);
		this.seriesIndicator = seriesIndicator;
	}

	private final List<ReportStyle> reportStyles;
	private String seriesIndicator;
	
	public List<ReportStyle> getReportStyles(){
		return this.reportStyles;
	}
	
	public String getSeriesIndicator() {
		return this.seriesIndicator;
	}
	
}