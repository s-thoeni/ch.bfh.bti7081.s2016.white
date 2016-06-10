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
	AVAILABLE_EMPLOYEES(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.TABULAR}),
	SICK_LEAVES(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.PIE_CHART,ReportStyle.TABULAR}),
	INCIDENTS(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.PIE_CHART,ReportStyle.TABULAR}),
	PATIENTS(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.TABULAR}),
	EFFORT(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.TABULAR}), 
	CASHFLOW(new ReportStyle[]{ReportStyle.LINE_GRAPH,ReportStyle.TABULAR});
	
	private ReportType(ReportStyle[] reportStyles) {
		this.reportStyles = Arrays.asList(reportStyles);
	}

	private final List<ReportStyle> reportStyles;
	
	public List<ReportStyle> getReportStyles(){
		return this.reportStyles;
	}
}