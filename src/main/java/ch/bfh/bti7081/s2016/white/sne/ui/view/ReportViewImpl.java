package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

public class ReportViewImpl extends CustomComponent implements ReportView {

	// TODO(jan): Verify that this serialVersionUID makes sense
	private static final long serialVersionUID = 2L;

	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	
	public ReportViewImpl(Report report) {
		Label titleLabel = new Label("Report View");
		titleLabel.setValue(report.getName());
		
		// TODO(jan): Load data for chart dynamically
		Chart chart = new Chart(ChartType.LINE);
		chart.setWidth("500px");
		chart.setHeight("500px");
		
		Configuration conf = chart.getConfiguration();
		conf.setTitle("Incidents");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(report.getFrom());
		int startDate = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(report.getTo());
		int endDate = calendar.get(Calendar.DAY_OF_YEAR);

		List<Number> values = new ArrayList<Number>();
		List<String> categories = new ArrayList<String>();
		for (int i = 0; i <= (endDate-startDate); i++) {
			calendar.setTime(report.getFrom());
			calendar.add(Calendar.DAY_OF_YEAR, i);
			values.add(0);
			categories.add(Integer.toString(calendar.get(Calendar.DAY_OF_YEAR)));
		}
		
		for (Record record : report.getRecords()) {
			calendar.setTime(record.getDate());
			int index = calendar.get(Calendar.DAY_OF_YEAR)-startDate;
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
		layout.addComponent(titleLabel, 0, 0, 3, 0);
		layout.addComponent(chart);
		
		this.setCompositionRoot(layout);
	}

	public void addListener(ReportViewListener listener) {
		this.listeners.add(listener);
	}

}