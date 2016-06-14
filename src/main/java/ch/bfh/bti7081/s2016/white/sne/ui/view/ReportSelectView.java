package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.ReportSelectSetImpl;

/**
 * Displays report select sets and handle the addition and deletion of sets and
 * performs the delivery of the selected reports.
 * 
 * @author mcdizzu
 *
 */
public interface ReportSelectView {

	/**
	 * These listeners will be notified for a certain event.
	 * 
	 * @author mcdizzu
	 */
	public interface ReportSelectViewListener {
		/**
		 * Handles the event for performing the report.
		 * 
		 * @param reportType
		 *            certain report type
		 * @param from
		 *            from Date
		 * @param to
		 *            to Date
		 * @throws SneException
		 */
		void handleGoClick(ReportType reportType, Date from, Date to) throws SneException;

		/**
		 * Handles the event for performing the report.
		 * 
		 * @param reportSelectSets
		 *            list of reports (report type and a date pair)
		 * @throws SneException
		 */
		void handleGoClick(List<ReportSelectSetImpl> reportSelectSets) throws SneException;

		/**
		 * Handles the event for adding an additional report select set
		 */
		void handleAddClick();

	}

	/**
	 * Getter of all report select set.
	 * 
	 * @return
	 */
	public List<ReportSelectSetImpl> getReportSelectSetImpl();

	/**
	 * Add a report select set to the list and the gui.
	 * 
	 * @param rssi
	 *            the new report select set
	 */
	public void addReportSelectSet(ReportSelectSetImpl rssi);

	/**
	 * Delete a report select set from the list and the gui.
	 * 
	 * @param rssi
	 *            the report select set to delete
	 */
	public void deleteReportSelectSet(ReportSelectSetImpl rssi);

	/**
	 * Register a new listener.
	 * 
	 * @see ReportSelectViewListener
	 * @param listener
	 */
	public void addListener(ReportSelectViewListener listener);

}
