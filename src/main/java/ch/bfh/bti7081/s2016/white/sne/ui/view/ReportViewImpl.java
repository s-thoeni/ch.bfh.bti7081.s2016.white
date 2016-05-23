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
		
		ListSeries series = new ListSeries("Incidents");
		series.setData(2700, 3400, 12000, 4000, 2, 891, 25, 6798, 1337, 7331, 7, 11);
		conf.addSeries(series);
		
		XAxis xAxis = new XAxis();
		xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		conf.addxAxis(xAxis);
		
		YAxis yAxis = new YAxis();
		conf.addyAxis(yAxis);
		
		GridLayout layout = new GridLayout(4, 5);
		layout.addComponent(titleLabel, 0, 0, 3, 0);
		layout.addComponent(chart);
		
		this.setCompositionRoot(layout);
	}

	public void addListener(ReportViewListener listener) {
		this.listeners.add(listener);
	}

}
