package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.thomas.slidemenu.SlideMenuView;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponent;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;


public class DashboardViewImpl extends CustomComponent implements DashboardView{
	
	private GridLayout grid;
	private List<TileComponent> tiles;
	
	private List<DashboardViewListener> listeners;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DashboardViewImpl() {
		this.tiles = new ArrayList<TileComponent>();
		this.listeners = new ArrayList<DashboardViewListener>();
		
		this.grid = new GridLayout(1, 6);
		grid.setMargin(false);
		grid.setSpacing(true);
		grid.setWidth("100%");
		grid.setHeight("100%");

		grid.setStyleName("dashboard");
		
		this.setWidth(null);

		// The composition root MUST be set
		super.setCompositionRoot(grid);
	}	



	@Override
	public void addTile(TileComponentImpl tile) {	
		((TileComponent)tile).addListener(id -> handleTileClick(id));
		tiles.add(tile);
		grid.addComponent(tile);
	}
	
	private void handleTileClick(String id){
		for(DashboardViewListener listener : listeners)
			listener.tileClick(id);
	}

	@Override	
	public void addListener(DashboardViewListener listener) {
		listeners.add(listener);
	}



	@Override
	public void navigateTo(Component component) {
		super.setCompositionRoot(component);
	}
}
