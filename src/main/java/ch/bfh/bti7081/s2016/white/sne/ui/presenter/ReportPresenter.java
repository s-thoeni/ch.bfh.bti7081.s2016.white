package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.Date;

import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportViewListener;

public class ReportPresenter implements ReportViewListener {

	private ReportProvider provider;
	private ReportView view;

	public ReportPresenter(ReportProvider provider, ReportView view) {
		this.provider = provider;
		this.view = view;
		
		this.view.addListener(this);
	}

	public void filterClicked() {
		// TODO(jan): implement this method
		Report report = this.provider.getIncidents(new Date(), new Date());
		this.view.setReport(report);
	}

}
