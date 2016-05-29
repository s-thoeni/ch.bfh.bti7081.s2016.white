package ch.bfh.bti7081.s2016.white.sne.ui.model;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;

/**
 * Really necessary???
 * @author mcdizzu
 *
 */
public class ReportSelectProvider {
private ReportFacade facade;
	
	public ReportSelectProvider() {
		this.facade = new ReportFacadeImpl();
	}

}
