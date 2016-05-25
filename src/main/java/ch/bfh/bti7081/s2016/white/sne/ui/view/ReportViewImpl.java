package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
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
		
		List<Number> values = new ArrayList<Number>();
		List<String> categories = new ArrayList<String>();
		for (Record record : report.getRecords()) {
			int value = values.get(record.getDate().getDate()).intValue();
			++value;
			values.add(record.getDate().getDate(), value);
			categories.add(record.getDate().getDate(), record.getDate().toString());
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
