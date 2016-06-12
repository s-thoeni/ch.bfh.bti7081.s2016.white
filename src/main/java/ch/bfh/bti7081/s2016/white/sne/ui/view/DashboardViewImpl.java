package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponent;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;

/**
 * This is the main dashboard implementation. Shows Tiles which corresponds to
 * reports which are specified in the user configuration. A click on a tile will
 * redirect to the ReportView to display the respective report.
 * 
 * @see SneMenuViewImpl
 * @author thons1
 */
public class DashboardViewImpl extends SneMenuViewImpl implements DashboardView {

	/**
	 * The title to be displayed in the header of the menu
	 */
	private static final String DASHBOARD_TITLE = "Dashboard";

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 5928512204631333779L;

	/**
	 * Logger for this class
	 */
	private transient static final Logger logger = LogManager.getLogger(DashboardViewImpl.class);

	/**
	 * Layout solely styled by style sheet.
	 */
	private CssLayout layout;

	/**
	 * The tiles to be displayed.
	 */
	private List<TileComponent> tiles;

	/**
	 * Listeners for this view. Will not be serialized
	 * 
	 * @see DashboardViewListener
	 */
	private transient List<DashboardViewListener> listeners;

	/**
	 * Initialize the dashboard with a config and alarms. The config will be
	 * used to load the reports from the business logic. The alarms will solely
	 * be used in SneMenuView which is responisible to display the alarms
	 * correctly.
	 * 
	 * @see SneMenuView
	 * @param config
	 * @param alarms
	 */
	public DashboardViewImpl(Configuration config, User user, List<Alarm> alarms) {
		// call super constructor
		super(config, user, alarms);
		logger.debug("->");
		logger.debug("Initializing new dashboard using: [config = " + config.toString() + ", alarms = "
				+ alarms.toString() + "]");

		// Set the caption
		getNavigationBar().setCaption(DASHBOARD_TITLE);
		
		// initialize a list of new tiles
		this.tiles = new ArrayList<TileComponent>();

		// initialize a list of new Listeners
		this.listeners = new ArrayList<DashboardViewListener>();

		// this is the root of the layout
		this.layout = new CssLayout();

		// styles to be used as reference in css
		this.setStyleName("slidemenu");
		layout.setStyleName("responsive-db");

		// allow the layout to use all the space it can
		layout.setSizeFull();

		// The composition root MUST be set
		this.setContent(layout);
		logger.debug("<-");
	}

	@Override
	public void addTile(TileComponentImpl tile) {
		logger.debug("->");

		// i ain't particularly proud of this cast...
		((TileComponent) tile).addListener(id -> handleTileClick(id));
		tiles.add(tile);
		layout.addComponent(tile);
		logger.debug("<-");
	}

	/**
	 * Notifies all registered listeners that a tile has been clicked.
	 * 
	 * @param id
	 */
	private void handleTileClick(String id) {
		logger.debug("->");

		for (DashboardViewListener listener : listeners) {
			try {
				listener.tileClick(id);
			} catch (SneException e) {
				Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
			}
		}
		logger.debug("<-");
	}

	@Override
	public void addListener(DashboardViewListener listener) {
		logger.debug("->");

		listeners.add(listener);
		logger.debug("<-");
	}

	@Override
	public void removeAll() {
		logger.debug("->");

		layout.removeAllComponents();
		tiles.clear();
		logger.debug("<-");
	}
}