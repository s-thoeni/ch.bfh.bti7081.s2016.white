package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import ch.bfh.bti7081.s2016.white.sne.data.FinancialRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PersonalRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.AbsenceReason;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportStyle;

public class ReportViewImpl extends NavigationView implements ReportView {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportViewImpl.class);
	
	// TODO(jan): Verify that this serialVersionUID makes sense
	private static final long serialVersionUID = 2L;

	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	
	private class RecordInRange {
		public boolean isInRange;
		int index;
	}
	
	public ReportViewImpl(List<Report<? extends Record>> reports) {
		// TODO(jan): implement this constructor
		super();
		this.setContent(null);
	}
	
	public ReportViewImpl(Report<? extends Record> report) {
		super();
		this.getNavigationBar().setCaption(report.getName());
		
		
		Table table = new Table();
		table.addContainerProperty("Date", String.class, null);
		table.setWidth(100, Unit.PERCENTAGE);
		table.addStyleName(Reindeer.TABLE_BORDERLESS);
		
		
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
		
		logger.debug("switch case report type");
		int index = 1;
		switch (report.getType()) {
		case EFFORT:
			logger.debug("type: EFFORT");
			table.addContainerProperty("Effort", String.class, null);
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index, ((FinancialRecord) record).getEffort());
						table.addItem(new Object[]{sdf.format(record.getDate()), String.valueOf(((FinancialRecord) record).getEffort())}, index++);
					}
				}
			}
			break;
		case CASHFLOW:
			logger.debug("type: CASHFLOW");
			table.addContainerProperty("Cashflow", String.class, null);
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index, ((FinancialRecord) record).getCashFlow());
						table.addItem(new Object[]{sdf.format(record.getDate()), String.valueOf(((FinancialRecord) record).getCashFlow())}, index++);
					}
				}
			}
			break;
		case AVAILABLE_EMPLOYEES:
			logger.debug("type: AVAILABLE_EMPLOYEES");
			table.addContainerProperty("Employee", String.class, null);
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index);
						table.addItem(new Object[]{sdf.format(record.getDate()), ((PersonalRecord) record).getPersonName()}, index++);
					}
				}
			}
			break;
		case PATIENTS:
			logger.debug("type: PATIENTS");
			table.addContainerProperty("Patient", String.class, null);
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index);
						table.addItem(new Object[]{sdf.format(record.getDate()), ((PatientRecord) record).getIncident()}, index++);
					}
				}
			}
			break;
		case ABSENT_EMPLOYEES:
			logger.debug("type: ABSENT_EMPLOYEES");
			table.addContainerProperty("Employee", String.class, null);
			table.addContainerProperty("Reason", String.class, null);
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(pieChartValues, ((PersonalRecord) record).getAbsenceReason().getDbID()-1);
						table.addItem(new Object[]{sdf.format(record.getDate()), ((PersonalRecord) record).getPersonName(), ((PersonalRecord) record).getAbsenceReason().toString()}, index++);
					}
				}
			}
			break;
		case INCIDENTS:
			logger.debug("type: INCIDENTS");
		default:
			logger.debug("default case");
			table.addContainerProperty("Incident", String.class, null);
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						this.updateValue(values, rir.index);
						table.addItem(new Object[]{sdf.format(record.getDate()), ((PatientRecord) record).getIncident()}, index++);
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
			table.setPageLength(table.size());
			VerticalLayout gridLayout = new VerticalLayout();
			gridLayout.addComponent(table);
			gridLayout.setSpacing(false);
		
			layout.addTab(gridLayout, "Records", FontAwesome.TABLE);
		}
		
		this.setContent(layout);
	}
	
	private void updateValue(List<Number> values, int index, Number newValue) {
		logger.debug("->");
		
		float value = values.get(index).floatValue();
		value += newValue.floatValue();
		values.set(index, value);
		logger.debug("<-");
	}
	
	private void updateValue(List<Number> values, int index) {
		logger.debug("->");
		
		this.updateValue(values, index, 1);
		logger.debug("<-");
	}
	
	private RecordInRange checkIfRecordIsInRange(Record record, long startDateInMillis, int maxIndex) {
		logger.debug("->");
		
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
		logger.debug("<-");
		return result;
	}
	
	private int getDiffInDays(long recordDateInMillis, long startDateInMillis) {
		logger.debug("->");
		
		long diffInMillis = recordDateInMillis-startDateInMillis;
		long longResult = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
		int result = (int)longResult;
		
		logger.debug("<-");
		return result;
	}
	
	private void setInsignificantCalendarFieldsToMax(Calendar cal) {
		logger.debug("->");
		
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		logger.debug("<-");
	}
	
	private void setInsignificantCalendarFieldsToMin(Calendar cal) {
		logger.debug("->");
		
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
		logger.debug("<-");
	}

	public void addListener(ReportViewListener listener) {
		logger.debug("->");
		
		this.listeners.add(listener);
		logger.debug("<-");
	}
}