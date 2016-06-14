package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.Date;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * A ReportSelectSet consists of a ReportType select, a from date picker and a
 * to date picker for choosing a certain report in a certain period.
 *
 * @author mcdizzu
 * 
 */
public interface ReportSelectSet {

	/**
	 * These listeners will be notified for a certain event.
	 * 
	 * @author mcdizzu
	 *
	 */
	public interface ReportSelectSetListener {

		/**
		 * Handles the event for deleting a report select set
		 * 
		 * @param rssi
		 *            the report select set to delete
		 */
		void handleDeleteClick(ReportSelectSetImpl rssi);

	}

	/**
	 * Register a new listener.
	 * 
	 * @see ReportSelectSetListener
	 * @param listener
	 */
	public void addListener(ReportSelectSetListener listener);

	/**
	 * Getter for the selected report type
	 * 
	 * @return report type
	 */
	public ReportType getReportType();

	/**
	 * Getter for the selected from date
	 * 
	 * @return from date
	 */
	public Date getFromDate();

	/**
	 * Getter for the selected to date
	 * 
	 * @return to date
	 */
	public Date getToDate();
}
