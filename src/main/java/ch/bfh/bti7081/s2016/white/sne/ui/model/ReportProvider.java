package ch.bfh.bti7081.s2016.white.sne.ui.model;

import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;

public class ReportProvider {

	private ReportFacade facade;
	
	public ReportProvider() {
		this.facade = new ReportFacadeImpl();
	}
	
	public Report getReportByTypeAndTimeFrame(ReportType type, ReportTimeframe timeFrame) {
		return this.facade.getReport(type, timeFrame);
	}

}
