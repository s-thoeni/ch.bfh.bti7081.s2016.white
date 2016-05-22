package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.List;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;


public class DashboardPresenter implements DashboardView.DashboardViewListener {

	DashboardProvider model;
	DashboardView view;
	
	public DashboardPresenter(DashboardProvider model, DashboardView view){

		this.model = model;
		this.view  = view;
		
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
	}

	public Component getView() {
		return (DashboardViewImpl) this.view;
	}
}
