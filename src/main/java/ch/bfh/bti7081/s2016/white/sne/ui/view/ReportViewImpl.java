package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2016.white.sne.data.FinancialRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PersonalRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.AbsenceReason;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportStyle;

public class ReportViewImpl extends NavigationView implements ReportView {
	
	// TODO(jan): Verify that this serialVersionUID makes sense
	private static final long serialVersionUID = 2L;

	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	
	private class RecordInRange {
		public boolean isInRange;
		int index;
	}
	
	public ReportViewImpl(List<Report<? extends Record>> reports) {
		// TODO(jan): implement this constructor
		
	}
	
	public ReportViewImpl(Report<? extends Record> report) {
		this.getNavigationBar().setCaption(report.getName());
		
		Grid grid = new Grid();
		grid.setWidth(100, Unit.PERCENTAGE);
		grid.setHeight(50, Unit.PERCENTAGE);
		grid.addColumn("Date", String.class);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");

		Calendar startDayCal = Calendar.getInstance();
		startDayCal.setTime(report.getFrom());
		this.setInsignificantCalendarFieldsToMin(startDayCal);
		long startDateInMillis = startDayCal.getTimeInMillis();
		
		Calendar endDayCal = Calendar.getInstance();
		endDayCal.setTime(report.getTo());
		this.setInsignificantCalendarFieldsToMax(endDayCal);
		long endDateInMillis = endDayCal.getTimeInMillis();

		List<Number> values = new ArrayList<Number>();

		XAxis xAxis = new XAxis();
		
		/*
		 * Initializing the values and categories list. For every day in the reports
		 * timespan an entry is added to the values list and a category with the
		 * formated day is added to the categories list.
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(report.getFrom());
		this.setInsignificantCalendarFieldsToMin(calendar);
		int diffInDays = this.getDiffInDays(endDateInMillis, startDateInMillis);
		for (int i = 0; i <= diffInDays; ++i) {
			values.add(0);
			xAxis.addCategory(sdf.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		List<Number> pieChartValues = new ArrayList<Number>();
		
		String seriesIndicator = report.getType().getSeriesIndicator();
		
		switch (report.getType()) {
		case EFFORT:
			grid.addColumn("Effort", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index, ((FinancialRecord) record).getEffort());
						grid.addRow(sdf.format(record.getDate()), String.valueOf(((FinancialRecord) record).getEffort()));
					}
				}
			}
			break;
		case CASHFLOW:
			grid.addColumn("Cashflow", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index, ((FinancialRecord) record).getCashFlow());
						grid.addRow(sdf.format(record.getDate()), String.valueOf(((FinancialRecord) record).getCashFlow()));
					}
				}
			}
			break;
		case AVAILABLE_EMPLOYEES:
			grid.addColumn("Employee", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index);
						grid.addRow(sdf.format(record.getDate()), ((PersonalRecord) record).getPersonName());
					}
				}
			}
			break;
		case PATIENTS:
			grid.addColumn("Patient", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index);
						grid.addRow(sdf.format(record.getDate()), ((PatientRecord) record).getIncident());
					}
				}
			}
			break;
		case ABSENT_EMPLOYEES:
			grid.addColumn("Employee", String.class);
			grid.addColumn("Reason", String.class);
			
			for (AbsenceReason reason : AbsenceReason.values()) {
				pieChartValues.add(reason.getDbID());
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index);
						this.updateValue(pieChartValues, ((PersonalRecord) record).getAbsenceReason().getDbID()-1);
						grid.addRow(sdf.format(record.getDate()), ((PersonalRecord) record).getPersonName(), ((PersonalRecord) record).getAbsenceReason().toString());
					}
				}
			}
			break;
		case INCIDENTS:
		default:
			grid.addColumn("Incident", String.class);
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index);
						grid.addRow(sdf.format(record.getDate()), ((PatientRecord) record).getIncident());
					}
				}
			}
		}
		
		TabBarView layout = new TabBarView();
		
		if(report.getType().getReportStyles().contains(ReportStyle.LINE_GRAPH)){
			Chart chart = new Chart(ChartType.LINE);
			chart.setHeight(50, Unit.PERCENTAGE);

			Configuration conf = chart.getConfiguration();
			conf.setTitle(report.getName());

			ListSeries series = new ListSeries(seriesIndicator);
			series.setData(values);
			conf.addSeries(series);
			
			conf.addxAxis(xAxis);
			
			VerticalLayout lineChartLayout = new VerticalLayout();
			lineChartLayout.addComponent(chart);
			
			layout.addTab(lineChartLayout, "Linechart", FontAwesome.LINE_CHART);
		}
		
		if(report.getType().getReportStyles().contains(ReportStyle.PIE_CHART)){
			Chart chart = new Chart(ChartType.PIE);
			chart.setHeight(50, Unit.PERCENTAGE);

			Configuration conf = chart.getConfiguration();
			conf.setTitle(report.getName());

			DataSeries series = new DataSeries(seriesIndicator);
			for (int i = 0; i < AbsenceReason.values().length; ++i) {
				series.add(new DataSeriesItem(AbsenceReason.values()[i].toString(), pieChartValues.get(i)));
			}
			conf.addSeries(series);
			
			VerticalLayout pieChartLayout = new VerticalLayout();
			pieChartLayout.addComponent(chart);
			
			layout.addTab(pieChartLayout, "Piechart", FontAwesome.PIE_CHART);
		}
		
		if(report.getType().getReportStyles().contains(ReportStyle.TABULAR)){
			VerticalLayout gridLayout = new VerticalLayout();
			gridLayout.addComponent(grid);
			gridLayout.setSpacing(false);
		
			layout.addTab(gridLayout, "Records", FontAwesome.TABLE);
		}
		
		super.setContent(layout);
	}
	
	private void updateValue(List<Number> values, int index, Number newValue) {
		float value = values.get(index).floatValue();
		value += newValue.floatValue();
		values.set(index, value);
	}
	
	private void updateValue(List<Number> values, int index) {
		this.updateValue(values, index, 1);
	}
	
	private RecordInRange checkIfRecordIsInRange(Record record, long startDateInMillis, int maxIndex) {
		RecordInRange result = new RecordInRange();
		Calendar cal = Calendar.getInstance();
		cal.setTime(record.getDate());
		this.setInsignificantCalendarFieldsToMax(cal);
		long recordDateInMillis = cal.getTimeInMillis();
		int index = this.getDiffInDays(recordDateInMillis, startDateInMillis);
		if ((index >= 0) &&
			(index <= maxIndex)) {
			result.isInRange = true;
		} else {
			result.isInRange = false;
		}
		result.index = index;
		return result;
	}
	
	private int getDiffInDays(long recordDateInMillis, long startDateInMillis) {
		long diffInMillis = recordDateInMillis-startDateInMillis;
		long longResult = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
		int result = (int)longResult;
		return result;
	}
	
	private void setInsignificantCalendarFieldsToMax(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
	}
	
	private void setInsignificantCalendarFieldsToMin(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));	
	}

	public void addListener(ReportViewListener listener) {
		this.listeners.add(listener);
	}
}