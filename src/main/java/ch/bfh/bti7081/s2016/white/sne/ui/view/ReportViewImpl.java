package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.FinancialRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PersonalRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

public class ReportViewImpl extends NavigationView implements ReportView {
	
	// TODO(jan): Verify that this serialVersionUID makes sense
	private static final long serialVersionUID = 2L;

	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	
	private class RecordInRange {
		public boolean isInRange;
		public Calendar cal;
	}
	
	public ReportViewImpl(Report<? extends Record> report) {
		this.getNavigationBar().setCaption(report.getName());
		
		Chart chart = new Chart(ChartType.LINE);
		chart.setHeight(50, Unit.PERCENTAGE);

		Grid grid = new Grid();
		grid.setWidth(100, Unit.PERCENTAGE);
		grid.setHeight(50, Unit.PERCENTAGE);
		grid.addColumn("Date", String.class);
		
		Configuration conf = chart.getConfiguration();
		conf.setTitle(report.getName());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");

		Calendar startDayCal = Calendar.getInstance();
		startDayCal.setTime(report.getFrom());
		startDayCal.set(Calendar.HOUR_OF_DAY, startDayCal.getActualMinimum(Calendar.HOUR_OF_DAY));
		startDayCal.set(Calendar.MINUTE, startDayCal.getActualMinimum(Calendar.MINUTE));
		startDayCal.set(Calendar.SECOND, startDayCal.getActualMinimum(Calendar.SECOND));
		startDayCal.set(Calendar.MILLISECOND, startDayCal.getActualMinimum(Calendar.MILLISECOND));
		long startDateInMillis = startDayCal.getTimeInMillis();
		
		Calendar endDayCal = Calendar.getInstance();
		endDayCal.setTime(report.getTo());
		endDayCal.set(Calendar.HOUR_OF_DAY, endDayCal.getActualMaximum(Calendar.HOUR_OF_DAY));
		endDayCal.set(Calendar.MINUTE, endDayCal.getActualMaximum(Calendar.MINUTE));
		endDayCal.set(Calendar.SECOND, endDayCal.getActualMaximum(Calendar.SECOND));
		endDayCal.set(Calendar.MILLISECOND, endDayCal.getActualMaximum(Calendar.MILLISECOND));

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
		
		String seriesIndicator = report.getType().getSeriesIndicator();
		
		switch (report.getType()) {
		case EFFORT:
			grid.addColumn("Effort", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDayCal, endDayCal);
					if (rir.isInRange) {
						this.updateValue(rir.cal, startDateInMillis, values, ((FinancialRecord) record).getEffort());
						grid.addRow(sdf.format(rir.cal.getTime()), String.valueOf(((FinancialRecord) record).getEffort()));
					}
				}
			}
			break;
		case CASHFLOW:
			grid.addColumn("Cashflow", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDayCal, endDayCal);
					if (rir.isInRange) {
						this.updateValue(rir.cal, startDateInMillis, values, ((FinancialRecord) record).getCashFlow());
						grid.addRow(sdf.format(rir.cal.getTime()), String.valueOf(((FinancialRecord) record).getCashFlow()));
					}
				}
			}
			break;
		case AVAILABLE_EMPLOYEES:
			grid.addColumn("Employee", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDayCal, endDayCal);
					if (rir.isInRange) {
						this.updateValue(rir.cal, startDateInMillis, values);
						grid.addRow(sdf.format(rir.cal.getTime()), ((PersonalRecord) record).getPersonName());
					}
				}
			}
			break;
		case PATIENTS:
			grid.addColumn("Patient", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDayCal, endDayCal);
					if (rir.isInRange) {
						this.updateValue(rir.cal, startDateInMillis, values);
						grid.addRow(sdf.format(rir.cal.getTime()), ((PatientRecord) record).getIncident());
					}
				}
			}
			break;
		case SICK_LEAVES:
			grid.addColumn("Employee", String.class);
			grid.addColumn("Reason", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDayCal, endDayCal);
					if (rir.isInRange) {
						this.updateValue(rir.cal, startDateInMillis, values);
						grid.addRow(sdf.format(rir.cal.getTime()), ((PersonalRecord) record).getPersonName(), ((PersonalRecord) record).getUnavailableReason());
					}
				}
			}
			break;
		case INCIDENTS:
		default:
			grid.addColumn("Incident", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDayCal, endDayCal);
					if (rir.isInRange) {
						this.updateValue(rir.cal, startDateInMillis, values);
						grid.addRow(sdf.format(rir.cal.getTime()), ((PatientRecord) record).getIncident());
					}
				}
			}
		}
		
		ListSeries series = new ListSeries(seriesIndicator);
		series.setData(values);
		conf.addSeries(series);
		
		conf.addxAxis(xAxis);
		
		VerticalLayout lineChartLayout = new VerticalLayout();
		lineChartLayout.addComponent(chart);

		VerticalLayout gridLayout = new VerticalLayout();
		gridLayout.addComponent(grid);
		gridLayout.setSpacing(false);
		
		TabBarView layout = new TabBarView();
		layout.addTab(lineChartLayout, "Linechart");
		layout.addTab(gridLayout, "Records");
		
		super.setContent(layout);
	}
	
	private void updateValue(Calendar cal, long startDateInMillis, List<Number> values, Number newValue) {
		long recordDateInMillis = cal.getTimeInMillis();
		int index = (int)TimeUnit.DAYS.convert(recordDateInMillis-startDateInMillis, TimeUnit.MILLISECONDS);
		
		float value = values.get(index).floatValue();
		value += newValue.floatValue();
		values.set(index, value);
	}
	
	private void updateValue(Calendar cal, long startDateInMillis, List<Number> values) {
		this.updateValue(cal, startDateInMillis, values, 1);
	}
	
	private RecordInRange checkIfRecordIsInRange(Record record, Calendar startDayCal, Calendar endDayCal) {
		RecordInRange result = new RecordInRange();
		Calendar cal = Calendar.getInstance();
		cal.setTime(record.getDate());
		if ((cal.compareTo(startDayCal) >= 0) &&
			(cal.compareTo(endDayCal) <= 0)) {
			result.isInRange = true;
		} else {
			result.isInRange = false;
		}
		result.cal = cal;
		return result;
	}

	public void addListener(ReportViewListener listener) {
		this.listeners.add(listener);
	}
}