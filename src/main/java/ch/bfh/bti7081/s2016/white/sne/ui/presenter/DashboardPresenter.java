package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.ReportProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.ReportViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;

/**
 * Presents the dashboard. Contains all UI logic from the dashboard such as
 * displaying the report summaries in the correct format and handle events
 * triggered by the dashboard view.
 * 
 * @see DashboardView
 * @author thons1
 *
 */
public class DashboardPresenter implements DashboardView.DashboardViewListener {

	/**
	 * Logger for this class
	 */
	private transient static final Logger logger = LogManager.getLogger(DashboardPresenter.class);

	/**
	 * The model used in this presenter. Responsible for the persistance and the
	 * business logic of the dashboard.
	 */
	private DashboardProvider model;

	/**
	 * The view used in this presenter. Repsponsible for displaying the given
	 * data in a fancy way.
	 */
	private DashboardView view;

	/**
	 * initialize the presenter using a DashBoardProbider and a DashboardView.
	 * 
	 * @param model
	 *            - the model instance.
	 * @param view
	 *            - the view instance.
	 */
	public DashboardPresenter(DashboardProvider model, DashboardViewImpl view) {
		this.model = model;
		this.view = view;

		// Ask the model to kindly return the reports to be used
		List<Report> reports = model.getReports();

		// add a tile for each report
		addReportTiles(view, reports);

		// register myself as a listener.
		view.addListener(this);
	}

	/**
	 * Adds a tile to the view for each given report.
	 * 
	 * @param view
	 *            - the view which shall display the report.
	 * @param reports
	 *            - the reports that shall be displayed
	 */
	private void addReportTiles(DashboardView view, List<Report> reports) {
		for (Report r : reports) {
			// the summary might need a bit of optimization depending on the
			// report type:
			String summary = handleSummary(r.getType(), r.getSummary());

			// gimme that new tile!
			TileComponentImpl tile = new TileComponentImpl(r.getName(), summary, r.getFrom(), r.getTo(), r.getName());

			view.addTile(tile);
		}
	}

	/**
	 * The summary might need a bit of extra "fancyness" depending on the report
	 * type. Curently the only reason is to decorate currency with a '.-' as we
	 * do in switzerland. (no i18n... YET! Maybe in sprint #1337)
	 * 
	 * @param type
	 *            - the report type
	 * @param sum
	 *            - the summary as it is retrived from business logic
	 * @return - the new fancyfied summary
	 */
	private String handleSummary(ReportType type, int sum) {
		String summary = String.valueOf(sum);
		if (type == ReportType.CASHFLOW || type == ReportType.EFFORT) {
			summary = summary.concat(" .-");
		}
		return summary;
	}

	@Override
	public void tileClick(String id) {
		logger.debug("->");

		ReportProvider reportModel = new ReportProvider();
		ReportViewImpl reportView = new ReportViewImpl(model.getReportByName(id));
		ReportPresenter reportPresenter = new ReportPresenter(reportModel, reportView);
		this.view.navigateTo(reportPresenter.getView());
		logger.debug("<-");
	}

	/**
	 * retrun the dashboard view
	 * 
	 * @return dasboard view
	 */
	public Component getView() {
		logger.debug("->");
		logger.debug("<-");
		return (DashboardViewImpl) this.view;
	}

	/**
	 * sometimes a new configuration arises. Then we need to refresh the
	 * dashboard. Removes all current tiles and adds them new according to the
	 * reports given by the model
	 * 
	 * @param config
	 */
	public void refresh(Configuration config) {
		logger.debug("->");

		// set the new config
		model.setReportConfigurations(config.getReports());

		// remove all current tiles
		view.removeAll();
		List<Report> reports = model.getReports();

		// add them newly
		addReportTiles(view, reports);
		logger.debug("<-");
	}
}
