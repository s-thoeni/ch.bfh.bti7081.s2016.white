package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ReportSelectSetImpl;

/**
 * 
 * @author mcdizzu
 *
 */
public class ReportSelectViewImpl extends NavigationView implements ReportSelectView {

	private VerticalLayout root;
	private VerticalLayout container;

	private List<ReportSelectViewListener> listeners = new ArrayList<ReportSelectViewListener>();
	private List<ReportSelectSetImpl> reportSelectSet;

	public ReportSelectViewImpl() {
		this.root = new VerticalLayout();
		this.root.setSpacing(true);
		this.root.setMargin(true);
		this.reportSelectSet = new ArrayList<ReportSelectSetImpl>();
		
		this.container = new VerticalLayout();
		this.container.setSpacing(true);
		this.container.setMargin(true);
		this.root.addComponent(this.container);
		
		// first report select set
		ReportSelectSetImpl rssi = new ReportSelectSetImpl(true); 
		this.reportSelectSet.add(rssi);
		this.container.addComponent(rssi);
		
		// add button
		Button addBtn = new Button("+");
		addBtn.setHeight("50px");
		addBtn.setWidth("50px");

		addBtn.addClickListener(e -> {
			for (ReportSelectViewListener listener : listeners)				
				listener.handleAddClick();
		});
		this.root.addComponent(addBtn);
		
		//go button
		// TODO : Errorhandling...
		Button goBtn = new Button("Go");
		goBtn.addClickListener(e -> {
			for (ReportSelectViewListener listener : listeners)
				listener.handleGoClick(this.reportSelectSet);
				
				//listener.handleGoClick((ReportType) this.reportSelectSet.get(0).getReportType(), this.reportSelectSet.get(0).getFromDate(), this.reportSelectSet.get(0).getToDate());
		});
		this.root.addComponent(goBtn);

		this.setContent(root);
	}

	@Override
	public void addListener(ReportSelectViewListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void navigateTo(Component component) {
		getNavigationManager().navigateTo(component);
	}

	public NavigationManager getNatigationManager() {
		return super.getNavigationManager();
		
	}

	@Override
	public List<ReportSelectSetImpl> getReportSelectSetImpl() {
		return this.reportSelectSet;
	}

	@Override
	public void addReportSelectSet(ReportSelectSetImpl rssi) {
		this.reportSelectSet.add(rssi);
		this.container.addComponent(rssi);
	}

	@Override
	public void deleteReportSelectSet(ReportSelectSetImpl rssi) {
		this.reportSelectSet.remove(rssi);
		this.container.removeComponent(rssi);
		
	}
}
