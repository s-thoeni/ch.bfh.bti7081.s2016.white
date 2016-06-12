package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.DatePair;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportSelectView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportSelectViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ReportSelectSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ReportSelectSetImpl;

/**
 * 
 * @author mcdizzu
 *
 */
public class ReportSelectPresenter implements ReportSelectView.ReportSelectViewListener, ReportSelectSet.ReportSelectSetListener {

	private ReportSelectViewImpl view;

	public ReportSelectPresenter(ReportSelectViewImpl view) {
		this.view = view;

		this.view.addListener(this);
	}

	public Component getView() {
		return (ReportSelectViewImpl) this.view;
	}

	@Override
	public void handleGoClick(ReportType reportType, Date from, Date to) {
		DatePair datePair = new DatePair(from, to);

		ReportProvider reportModel = new ReportProvider();
		ReportViewImpl reportView = new ReportViewImpl(reportModel.getReportByTypeAndDatePair(reportType, datePair));
		ReportPresenter reportPresenter = new ReportPresenter(reportModel, reportView);

		this.view.getNatigationManager().navigateTo(reportPresenter.getView());

	}

	@Override
	public void handleGoClick(List<ReportSelectSetImpl> reportSelectSets) {
		List<Report> reports = new ArrayList<Report>();
		
		ReportProvider reportModel = new ReportProvider();
		
		for(ReportSelectSetImpl rssi : reportSelectSets) {
			DatePair datePair = new DatePair(rssi.getFromDate(), rssi.getToDate());
			reports.add(reportModel.getReportByTypeAndDatePair(rssi.getReportType(), datePair));
		}
		ReportViewImpl reportView = new ReportViewImpl(reports);
		ReportPresenter reportPresenter = new ReportPresenter(reportModel, reportView);
		
		this.view.getNatigationManager().navigateTo(reportPresenter.getView());
		
	}

	@Override
	public void handleAddClick() {
		int i = view.getReportSelectSetImpl().size();
		ReportSelectSetImpl rssi = new ReportSelectSetImpl(false);
		rssi.setId(String.valueOf(i));
		view.addReportSelectSet(rssi);
		((ReportSelectSet) rssi).addListener(id -> handleDeleteClick(rssi));

	}

	@Override
	public void handleDeleteClick(ReportSelectSetImpl rssi) {
		view.deleteReportSelectSet(rssi);
	}
}
