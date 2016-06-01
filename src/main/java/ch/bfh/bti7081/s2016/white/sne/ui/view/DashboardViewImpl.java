package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponent;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;


public class DashboardViewImpl extends SneMenuViewImpl implements DashboardView{
	
	private CssLayout grid;
	private List<TileComponent> tiles;
	
	private List<DashboardViewListener> listeners;
	
	private static final long serialVersionUID = 1L;

	public DashboardViewImpl(Configuration config) {
		super(config);
		
		getNavigationBar().setCaption("Dashboard");
		
		this.tiles = new ArrayList<TileComponent>();
		this.listeners = new ArrayList<DashboardViewListener>();
		
		this.grid = new CssLayout();
		//grid.setMargin(false);
		//grid.setSpacing(false);
		//grid.setWidth(null);
		//grid.setHeight(null);

		this.setStyleName("slidemenu");
		grid.setStyleName("responsive-db");
		//this.setWidth(null);
		grid.setSizeFull();

		Responsive.makeResponsive(grid);


		// The composition root MUST be set
		this.setContent(grid);
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
		getNavigationManager().navigateTo(component);
	}
	
	public NavigationManager getNavigationManager() {
		 return super.getNavigationManager();
	 }
}
