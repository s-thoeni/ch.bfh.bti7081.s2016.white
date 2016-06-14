package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * This is the main implementation of the ReportSelectSet. It is used to select
 * a certain report for a certain period.
 * 
 * @see ReportSelectSet
 * @author mcdizzu
 *
 */
public class ReportSelectSetImpl extends CustomComponent implements ReportSelectSet {

	/**
	 * Generated serial id.
	 */
	private static final long serialVersionUID = 8789355305461622455L;

	/**
	 * Logger for this class
	 */
	private transient static final Logger logger = LogManager.getLogger(TileComponentImpl.class);

	/**
	 * These listeners will be informed when a tile has been clicked.
	 * 
	 * @see ReportSelectSetListener
	 */
	private List<ReportSelectSetListener> listeners = new ArrayList<ReportSelectSetListener>();

	/**
	 * The root layout for the set
	 */
	private FormLayout root;

	/**
	 * Native select for all report types
	 */
	private NativeSelect reportSelect;

	/**
	 * Date picker for the from date
	 */
	private DatePicker fromDate;

	/**
	 * Date picker for the to date
	 */
	private DatePicker toDate;

	/**
	 * Displays a form layout with a native select and two date picker. Optional
	 * a button can be displayed, to delete the set.
	 * 
	 * @param first
	 *            when true the delete button will be displayed
	 */
	public ReportSelectSetImpl(boolean first) {

		// set up root layout
		this.root = new FormLayout();
		this.root.setSpacing(true);
		this.root.setMargin(true);

		// build report selection and add it
		this.reportSelect = new NativeSelect("Report: ");
		ReportType[] types = ReportType.values();
		for (ReportType type : types) {
			this.reportSelect.addItem(type);
		}
		this.reportSelect.setNullSelectionAllowed(true);
		this.reportSelect.setStyleName("configselect");

		this.root.addComponent(this.reportSelect);

		// build from date picker and add it
		this.fromDate = new DatePicker("From: ");
		this.root.addComponent(this.fromDate);

		// build to date picker and add it
		this.toDate = new DatePicker("To: ");
		this.root.addComponent(this.toDate);

		// build remove button, when its the first one
		if (!first) {
			Button removeBtn = new Button("-");
			removeBtn.setHeight("50px");
			removeBtn.setWidth("50px");

			// add delete click handler
			removeBtn.addClickListener(e -> handleDeleteClick());
			this.root.addComponent(removeBtn);
		}

		// The root element of this component is the root.
		setCompositionRoot(this.root);
	}

	@Override
	public void addListener(ReportSelectSetListener listener) {
		logger.debug("->");
		this.listeners.add(listener);
		logger.debug("<-");
	}

	/**
	 * Handle click event and notify the listeners.
	 */
	public void handleDeleteClick() {
		logger.debug("->");
		for (ReportSelectSetListener listener : listeners)
			listener.handleDeleteClick(this);
		logger.debug("<-");
	}

	@Override
	public ReportType getReportType() {
		logger.debug("->");
		if (this.reportSelect.getValue() instanceof ReportType) {
			logger.debug("<-");
			return (ReportType) this.reportSelect.getValue();
		} else {
			logger.debug("<-");
			return null;
		}
	}

	@Override
	public Date getFromDate() {
		logger.debug("->");
		if (this.fromDate.getValue() instanceof Date) {
			logger.debug("<-");
			return (Date) this.fromDate.getValue();
		} else {
			logger.debug("<-");
			return null;
		}
	}

	@Override
	public Date getToDate() {
		logger.debug("->");
		if (this.toDate.getValue() instanceof Date) {
			logger.debug("<-");
			return (Date) this.toDate.getValue();
		} else {
			logger.debug("<-");
			return null;
		}
	}

}
