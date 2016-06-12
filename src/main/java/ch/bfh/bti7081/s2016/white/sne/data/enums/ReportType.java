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

	/**
	 * list of available report styles for specific report type
	 */
	private final List<ReportStyle> reportStyles;
	
	/**
	 * Friendly name
	 */
	private String seriesIndicator;
	
	/**
	 * Returns available report styles of ENUM
	 * @return list of report styles
	 */
	public List<ReportStyle> getReportStyles(){
		return this.reportStyles;
	}
	
	/**
	 * Returns friendly name
	 * @return seriesIndicator as string
	 */
	public String getSeriesIndicator() {
		return this.seriesIndicator;
	}
	
}