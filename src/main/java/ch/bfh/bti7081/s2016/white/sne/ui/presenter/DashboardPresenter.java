package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.List;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;


public class DashboardPresenter implements DashboardView.DashboardViewListener {

	DashboardProvider model;
	DashboardView view;
	VerticalLayout layout;
	
	public DashboardPresenter(DashboardProvider model, DashboardView view, VerticalLayout layout){

		this.model = model;
		this.view  = view;
		this.layout = layout;
		
		List<Report> reports = model.getReports();

		for(Report r: reports){
			TileComponentImpl tile = new TileComponentImpl(r.getName(), r.getSummary(), r.getName());
			view.addTile(tile);
		}
		
		view.addListener(this);
	}
	
	@Override
	public void tileClick(String id) {
		System.out.println("Tile clicked with id: "+id);
		ReportProvider reportModel = new ReportProvider();
		ReportView reportView = new ReportViewImpl(reportModel.getReportByTypeAndTimeFrame(ReportType.INCIDENTS, ReportTimeframe.LAST_WEEK));
		ReportPresenter reportPresenter = new ReportPresenter(reportModel, reportView);
		
		this.layout.removeAllComponents();
		this.layout.addComponent(reportPresenter.getView());
	}

	public Component getView() {
		return (DashboardViewImpl) this.view;
	}
}
