package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.Date;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.enums.DatePair;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportSelectView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportSelectViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportViewImpl;

/**
 * 
 * @author mcdizzu
 *
 */
public class ReportSelectPresenter implements ReportSelectView.ReportSelectViewListener {

	private ReportSelectView view;

	public ReportSelectPresenter(ReportSelectView view) {
		this.view = view;

		this.view.addListener(this);
	}

	public Component getView() {
		return (ReportSelectViewImpl) this.view;
	}

	@Override
	public void go(ReportType reportType, Date from, Date to) {
		DatePair datePair = new DatePair(from, to);
		
		ReportProvider reportModel = new ReportProvider();
		ReportViewImpl reportView = new ReportViewImpl(reportModel.getReportByTypeAndDatePair(reportType, datePair));
		ReportPresenter reportPresenter = new ReportPresenter(reportModel, reportView);
		
		this.view.navigateTo(reportPresenter.getView());	
		
	}

}
