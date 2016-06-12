package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * This is the main implementation uf the TileComponent. It is used to
 * "beautifully" display the summary of a report.
 * 
 * @see TileComponent
 * @author thons1
 *
 */
public class TileComponentImpl extends CustomComponent implements TileComponent {

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = 8332250225071412954L;

	/**
	 * Logger for this class
	 */
	private transient static final Logger logger = LogManager.getLogger(TileComponentImpl.class);

	/**
	 * simple date formatter Initialized in short swiss date format
	 */
	private transient final SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");

	/**
	 * Label for report name
	 */
	private Label reportName;

	/**
	 * Label for report quick summary (performance indicator)
	 */
	private Label reportSummary;

	/**
	 * This id will be return in the case of a tileClick event. It is used to
	 * identify which tile has been clicked in case that more than one tile is
	 * used.
	 */
	private String id;

	/**
	 * These listeners will be informed when a tile has been clicked.
	 * 
	 * @see TileClickListener
	 */
	private List<TileClickListener> listeners = new ArrayList<TileClickListener>();

	/**
	 * Label for time range
	 */
	private Label timeframe;

	/**
	 * Displays a rectangle with a title and a value. The event when clicked on
	 * a tile consist of only the id of the clicked tile.
	 * 
	 * 
	 * @param title
	 *            - the title to be displayed
	 * @param value
	 *            - the value to be displayed
	 * @param id
	 *            - the id returned when the tile is clicked
	 */
	public TileComponentImpl(String title, String value, Date from, Date to, String id) {
		this.listeners = new ArrayList<TileClickListener>();
		this.id = id;

		// Need to have concrete width/height:
		int width = UI.getCurrent().getPage().getBrowserWindowWidth();
		int height = UI.getCurrent().getPage().getBrowserWindowHeight();

		// init all labels:
		reportName = new Label("<b>" + title + "</b>", ContentMode.HTML);
		reportSummary = new Label(value);
		timeframe = new Label(sf.format(from) + " - " + sf.format(to));

		// null width so thei inherit the width property auto
		reportName.setWidth(null);
		reportSummary.setWidth(null);
		timeframe.setWidth(null);

		// position and add relevant components:
		AbsoluteLayout container = new AbsoluteLayout();

		// this id will be returned when event is triggered
		container.setId(id);

		// add the components at an arbitrary position. Change it however you
		// want but make sure it looks decent!
		container.addComponent(reportName, "left: 5px; top: 7px");
		container.addComponent(reportSummary, "right: 10px; top: 7px");
		container.addComponent(timeframe, "right: 10px; bottom: 10px");

		// used later on
		container.addLayoutClickListener(e -> tileClicked(e));

		// Set size of the tile:
		container.setWidth(width - 20 + "px");
		container.setHeight(height / 6 - 15 + "px");

		// The root element of this component is the container. duh!
		setCompositionRoot(container);
	}

	/**
	 * Handle click events and notify listeners about the clicked tiles.
	 * 
	 * @param e
	 *            - the event triggered from the vaadin clicklistener.
	 */
	private void tileClicked(LayoutClickEvent e) {
		logger.debug("->");

		for (TileClickListener listener : listeners) {
			listener.tileClick(id);
		}
		logger.debug("<-");
	}

	@Override
	public void setTitle(String title) {
		logger.debug("->");

		reportName.setValue("<b>" + title + "</b>");
		logger.debug("<-");
	}

	@Override
	public void setValue(String value) {
		logger.debug("->");

		reportSummary.setValue(value);
		logger.debug("<-");
	}

	@Override
	public void setTimeframe(Date from, Date to) {
		logger.debug("->");

		timeframe.setValue(sf.format(from) + "-" + sf.format(from));
		logger.debug("<-");
	}

	@Override
	public void addListener(TileClickListener listener) {
		logger.debug("->");

		listeners.add(listener);
		logger.debug("<-");
	}
}
