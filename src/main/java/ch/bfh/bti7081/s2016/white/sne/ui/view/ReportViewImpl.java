package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

public class ReportViewImpl extends NavigationView implements ReportView {

	// TODO(jan): Verify that this serialVersionUID makes sense
	private static final long serialVersionUID = 2L;

	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	
	public ReportViewImpl(Report report) {
		getNavigationBar().setCaption(report.getName());
		
		// TODO(jan): Load data for chart dynamically
		Chart chart = new Chart(ChartType.LINE);
		
		Configuration conf = chart.getConfiguration();
		conf.setTitle(report.getName());
		
		Calendar endTimeCalendar = Calendar.getInstance();
		endTimeCalendar.setTime(report.getTo());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(report.getFrom());

		List<Number> values = new ArrayList<Number>();
		List<String> categories = new ArrayList<String>();
		while (endTimeCalendar.compareTo(calendar) >= 0) {
			values.add(0);
			categories.add(Integer.toString(calendar.get(Calendar.DAY_OF_YEAR)));
			calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		}
		
		for (Record record : report.getRecords()) {
			calendar.setTime(record.getDate());
			int index = endTimeCalendar.get(Calendar.DAY_OF_YEAR)-calendar.get(Calendar.DAY_OF_YEAR);
			int value = values.get(index).intValue();
			++value;
			values.set(index, value);
		}
		
		ListSeries series = new ListSeries("Incidents");
		series.setData(values);
		conf.addSeries(series);
		
		XAxis xAxis = new XAxis();
		for (String category : categories) {
			xAxis.addCategory(category);
		}
		conf.addxAxis(xAxis);
		
		GridLayout layout = new GridLayout(4, 5);
		layout.addComponent(chart);
		
		super.setContent(layout);
	}

	public void addListener(ReportViewListener listener) {
		this.listeners.add(listener);
	}
}