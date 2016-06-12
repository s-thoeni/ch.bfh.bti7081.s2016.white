package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.Date;

/**
 * A TileComponent consists of a title, a value and a time frame. It is used to
 * display the summary of a given report in a somewhat beautiful way. When
 * clicking on it, it triggers a tileClick event.
 *
 * <ul>
 * <li>title: will be displayed in bold on the upper left corner.</li>
 * <li>value: will be displayed in the lower right corner. It may also be
 * formatted into a currency depending on the reportType used.</li>
 * <li>timeframe: will be displayed in the upper right corner.</li>
 * </ul>
 * 
 * 
 * @author thons1
 */
public interface TileComponent {

	/**
	 * Set the title of this tile
	 * 
	 * @param title
	 *            - the title to be displayed
	 */
	public void setTitle(String title);

	/**
	 * Set the value of this tile. The value will be displayed in the lower
	 * right corner.
	 * 
	 * @param value
	 */
	public void setValue(String value);

	/**
	 * These listeners will be notified when a tile has been clicked.
	 * 
	 * @author thons1
	 *
	 */
	interface TileClickListener {
		void tileClick(String title);
	}

	/**
	 * Register a new listener.
	 * 
	 * @see TileClickListener
	 * @param listener
	 */
	public void addListener(TileClickListener listener);

	/**
	 * Set the time frame which shall be displayed
	 * 
	 * @param from
	 * @param to
	 */
	void setTimeframe(Date from, Date to);
}