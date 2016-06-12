package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponent;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;

public class DashboardViewImpl extends SneMenuViewImpl implements DashboardView {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(DashboardViewImpl.class);
	
	/**
	 * CSS style-sheet
	 */
	private CssLayout grid;
	private List<TileComponent> tiles;

	private List<DashboardViewListener> listeners;

	/**
	 * class serial ID
	 */
	private static final long serialVersionUID = 1L;

	public DashboardViewImpl(Configuration config, List<Alarm> alarms) {
		super(config, alarms);

		getNavigationBar().setCaption("Dashboard");

		this.tiles = new ArrayList<TileComponent>();
		this.listeners = new ArrayList<DashboardViewListener>();

		this.grid = new CssLayout();
		
		this.setStyleName("slidemenu");
		grid.setStyleName("responsive-db");

		grid.setSizeFull();

		Responsive.makeResponsive(grid);

		// The composition root MUST be set
		this.setContent(grid);
	}

	@Override
	public void addTile(TileComponentImpl tile) {
		logger.debug("->");
		
		((TileComponent) tile).addListener(id -> handleTileClick(id));
		tiles.add(tile);
		grid.addComponent(tile);
		logger.debug("<-");
	}

	private void handleTileClick(String id) {
		logger.debug("->");
		
		for (DashboardViewListener listener : listeners)
			listener.tileClick(id);
		logger.debug("<-");
	}

	@Override
	public void addListener(DashboardViewListener listener) {
		logger.debug("->");
		
		listeners.add(listener);
		logger.debug("<-");
	}

	@Override
	public void navigateTo(Component component) {
		logger.debug("->");
		
		getNavigationManager().navigateTo(component);
		logger.debug("<-");
	}

	public NavigationManager getNavigationManager() {
		logger.debug("->");
		logger.debug("<-");
		return super.getNavigationManager();
	}

	@Override
	public void removeAll() {
		logger.debug("->");
		
		grid.removeAllComponents();
		tiles.clear();
		logger.debug("<-");
	}
}