package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.List;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;


public class DashboardPresenter implements DashboardView.DashboardViewListener {

	DashboardProvider model;
	DashboardView view;
	
	public DashboardPresenter(DashboardProvider model, DashboardViewImpl view){
		this.model = model;
		this.view  = view;
		
		List<Report> reports = model.getReports();

		for(Report r: reports){
			TileComponentImpl tile = new TileComponentImpl(r.getName(), String.valueOf(r.getSummary()), r.getFrom(), r.getTo(), r.getName(), r.getType());
			view.addTile(tile);
		}
		
		view.addListener(this);
	}
	
	@Override
	public void tileClick(String id) {
		ReportProvider reportModel = new ReportProvider();
		ReportViewImpl reportView = new ReportViewImpl(model.getReportByName(id));
		ReportPresenter reportPresenter = new ReportPresenter(reportModel, reportView);
		this.view.navigateTo(reportPresenter.getView());
	}

	public Component getView() {
		return (DashboardViewImpl) this.view;
	}

	public void refresh(Configuration config) {
		model.setReportConfigurations(config.getReports());
		view.removeAll();
		List<Report> reports = model.getReports();

		for(Report r: reports){
			TileComponentImpl tile = new TileComponentImpl(r.getName(), String.valueOf(r.getSummary()), r.getFrom(), r.getTo(), r.getName(), r.getType());
			view.addTile(tile);
		}		
	}
}
