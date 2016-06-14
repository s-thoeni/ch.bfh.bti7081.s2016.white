package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ConfigSetImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ReportSelectSet;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ReportSelectSetImpl;

/**
 * This is the main report select view implementation. Shows a report select set
 * and a button for adding new sets.
 * 
 * @author mcdizzu
 *
 */
public class ReportSelectViewImpl extends NavigationView implements ReportSelectView {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -6959208928739241828L;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigSetImpl.class);

	/**
	 * The title to be displayed in the header of the menu
	 */
	private static final String REPORTS_TITLE = "Reports";

	/**
	 * The root layout for this view.
	 */
	private VerticalLayout root;
	/**
	 * The container layout for all report select sets
	 */
	private VerticalLayout container;

	/**
	 * These listeners will be informed when a tile has been clicked.
	 * 
	 * @see ReportSelectViewListener
	 */
	private List<ReportSelectViewListener> listeners = new ArrayList<ReportSelectViewListener>();

	/**
	 * All report select sets
	 * 
	 * @see ReportSelectSet
	 * @see ReportSelectSetImpl
	 */
	private List<ReportSelectSetImpl> reportSelectSet;

	/**
	 * Displays the root layout with the first report select set, button for
	 * adding new sets and a go button to get the selected report.
	 */
	public ReportSelectViewImpl() {
		// Set the caption
		getNavigationBar().setCaption(REPORTS_TITLE);

		// set up root layout
		this.root = new VerticalLayout();
		this.root.setSpacing(true);
		this.root.setMargin(true);

		// set up set lists
		this.reportSelectSet = new ArrayList<ReportSelectSetImpl>();

		// set up container layout and add it
		this.container = new VerticalLayout();
		this.container.setSpacing(true);
		this.container.setMargin(true);
		this.root.addComponent(this.container);

		// build first report select set and add it
		ReportSelectSetImpl rssi = new ReportSelectSetImpl(true);
		this.reportSelectSet.add(rssi);
		this.container.addComponent(rssi);

		// build add button and add it
		Button addBtn = new Button("+");
		addBtn.setHeight("50px");
		addBtn.setWidth("50px");

		// add add click handler
		addBtn.addClickListener(e -> {
			for (ReportSelectViewListener listener : listeners)
				listener.handleAddClick();
		});
		this.root.addComponent(addBtn);

		// build go button and add it
		Button goBtn = new Button("Go");
		// add go click handler
		goBtn.addClickListener(e -> {
			for (ReportSelectViewListener listener : listeners)
				try {
					listener.handleGoClick(this.reportSelectSet);
				} catch (SneException ex) {
					Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
				}
		});
		this.root.addComponent(goBtn);

		// The root element of this component is the root.
		this.setContent(root);
	}

	@Override
	public void addListener(ReportSelectViewListener listener) {
		logger.debug("->");

		this.listeners.add(listener);
		logger.debug("<-");
	}

	@Override
	public List<ReportSelectSetImpl> getReportSelectSetImpl() {
		logger.debug("->");
		logger.debug("<-");
		return this.reportSelectSet;
	}

	@Override
	public void addReportSelectSet(ReportSelectSetImpl rssi) {
		logger.debug("->");

		this.reportSelectSet.add(rssi);
		this.container.addComponent(rssi);
		logger.debug("<-");
	}

	@Override
	public void deleteReportSelectSet(ReportSelectSetImpl rssi) {
		logger.debug("->");

		this.reportSelectSet.remove(rssi);
		this.container.removeComponent(rssi);
		logger.debug("<-");
	}
}
