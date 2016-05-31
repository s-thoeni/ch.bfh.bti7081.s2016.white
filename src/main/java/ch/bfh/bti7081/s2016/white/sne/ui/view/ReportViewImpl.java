package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

public class ReportViewImpl extends NavigationView implements ReportView {

	// TODO(jan): Verify that this serialVersionUID makes sense
	private static final long serialVersionUID = 2L;

	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	
	public ReportViewImpl(Report report) {
		getNavigationBar().setCaption(report.getName());
		
		Chart chart = new Chart(ChartType.LINE);
		chart.setHeight(50, Unit.PERCENTAGE);

		Grid grid = new Grid();
		grid.setWidth(100, Unit.PERCENTAGE);
		grid.setHeight(50, Unit.PERCENTAGE);
		grid.addColumn("Date", String.class);
		grid.addColumn("Incident", String.class);
		
		Configuration conf = chart.getConfiguration();
		conf.setTitle(report.getName());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");

		Calendar startDayCal = Calendar.getInstance();
		startDayCal.setTime(report.getFrom());
		startDayCal.set(Calendar.HOUR_OF_DAY, startDayCal.getActualMinimum(Calendar.HOUR_OF_DAY));
		startDayCal.set(Calendar.MINUTE, startDayCal.getActualMinimum(Calendar.MINUTE));
		startDayCal.set(Calendar.SECOND, startDayCal.getActualMinimum(Calendar.MINUTE));
		int startDate = startDayCal.get(Calendar.DAY_OF_YEAR);
		int maxDayInStartYear = startDayCal.getActualMaximum(Calendar.DAY_OF_YEAR);
		
		Calendar endDayCal = Calendar.getInstance();
		endDayCal.setTime(report.getTo());
		endDayCal.set(Calendar.HOUR_OF_DAY, endDayCal.getActualMaximum(Calendar.HOUR_OF_DAY));
		endDayCal.set(Calendar.MINUTE, endDayCal.getActualMaximum(Calendar.MINUTE));
		endDayCal.set(Calendar.SECOND, endDayCal.getActualMaximum(Calendar.MINUTE));

		List<Number> values = new ArrayList<Number>();

		XAxis xAxis = new XAxis();
		
		/*
		 * Initializing the values and categories list. For every day in the reports
		 * timespan an entry is added to the values list and a category with the
		 * formated day is added to the categories list.
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(report.getFrom());
		while (calendar.compareTo(endDayCal) <= 0) {
			values.add(0);
			xAxis.addCategory(sdf.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		for (Record record : report.getRecords()) {
			calendar.setTime(record.getDate());
			if ((calendar.compareTo(startDayCal) >= 0) &&
				(calendar.compareTo(endDayCal) <= 0)) {
				int index = calendar.get(Calendar.DAY_OF_YEAR)-startDate;
				
				/*
				 * The index has to be adjusted in case the year changes in the reports
				 * timespan.
				 */
				if (index < 0) {
					index += maxDayInStartYear;
				}
				int value = values.get(index).intValue();
				++value;
				values.set(index, value);
			}

			if (record instanceof PatientRecord) {
				grid.addRow(sdf.format(calendar.getTime()), ((PatientRecord) record).getIncident());
			}
		}
		
		ListSeries series = new ListSeries("Incidents");
		series.setData(values);
		conf.addSeries(series);
		
		conf.addxAxis(xAxis);
		
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(false);
		layout.addComponent(chart);
		layout.addComponent(grid);
		
		super.setContent(layout);
	}

	public void addListener(ReportViewListener listener) {
		this.listeners.add(listener);
	}
}