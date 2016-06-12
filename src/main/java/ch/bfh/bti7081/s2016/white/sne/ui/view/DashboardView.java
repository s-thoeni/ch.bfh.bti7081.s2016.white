package ch.bfh.bti7081.s2016.white.sne.ui.view;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;

/**
 * Displays Tiles and triggers tileClick event when one of the tiles has been
 * clicked.
 * 
 * @author thons1
 */
public interface DashboardView {

	/**
	 * The listener which can be used to retrieve aforementioned tileClick
	 * event. The event consists of the id from the tile.
	 * 
	 * @author thons1
	 */
	public interface DashboardViewListener {
		void tileClick(String id) throws SneException;
	}

	/**
	 * Add a new listener to be used to evaluate tileClick events.
	 * 
	 * @param listener
	 */
	public void addListener(DashboardViewListener listener);

	/**
	 * Add a TileComponent and register listener
	 * 
	 * @param tile
	 */
	void addTile(TileComponentImpl tile);

	/**
	 * remove all tiles currently displayed. This does not have an effect on the
	 * persisted data nor the configuration.
	 */
	public void removeAll();

}