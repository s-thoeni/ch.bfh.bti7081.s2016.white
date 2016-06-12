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
		this.getNavigationBar().setCaption("Report comparison");
		
		int globalDiffInDays = 0;
		for (Report<? extends Record> report : reports) {
			Calendar startDateCal = Calendar.getInstance();
			Calendar endDateCal = Calendar.getInstance();
			startDateCal.setTime(report.getFrom());
			endDateCal.setTime(report.getTo());
			this.setInsignificantCalendarFieldsToMin(startDateCal);
			this.setInsignificantCalendarFieldsToMax(endDateCal);
			long startDateInMillis = startDateCal.getTimeInMillis();
			long endDateInMillis = endDateCal.getTimeInMillis();
			int potentialDiffInDays = this.getDiffInDays(endDateInMillis, startDateInMillis);
			if (potentialDiffInDays > globalDiffInDays) {
				globalDiffInDays = potentialDiffInDays;
			}
		}

		TabBarView layout = new TabBarView();
		XAxis xAxis = new XAxis();

		Chart lineChart = new Chart(ChartType.LINE);
		lineChart.setHeight(50, Unit.PERCENTAGE);
		Configuration lineChartConf = lineChart.getConfiguration();
		lineChartConf.setTitle("Report comparison");
		lineChartConf.addxAxis(xAxis);
		
		/*
		 * Initializing the values and categories list. For every day in the reports
		 * timespan an entry is added to the values list and a category with the
		 * formated day is added to the categories list.
		 */
		for (int i = 0; i <= globalDiffInDays; ++i) {
			xAxis.addCategory(String.valueOf(i));
		}
		
		for (Report<? extends Record> report : reports) {
			ListSeries lineChartSeries = new ListSeries(report.getName());
			this.createChartsFromReport(report, lineChartSeries, null, null);	
			lineChartConf.addSeries(lineChartSeries);
		}
		
		VerticalLayout lineChartLayout = new VerticalLayout();
		lineChartLayout.addComponent(lineChart);
		layout.addTab(lineChartLayout, "Linechart", FontAwesome.LINE_CHART);
		
		super.setContent(layout);
	}
	
	public ReportViewImpl(Report<? extends Record> report) {
		this.getNavigationBar().setCaption(report.getName());

		Chart lineChart = null;
		Configuration lineChartConf = null;
		ListSeries lineChartSeries = new ListSeries();
		XAxis xAxis = new XAxis();
		if(report.getType().getReportStyles().contains(ReportStyle.LINE_GRAPH)){
			lineChart = new Chart(ChartType.LINE);
			lineChart.setHeight(50, Unit.PERCENTAGE);
			lineChartConf = lineChart.getConfiguration();
			lineChartConf.setTitle(report.getName());
		}

		Chart pieChart = null;
		Configuration pieChartConf = null;
		DataSeries pieChartSeries = new DataSeries(report.getName());
		if(report.getType().getReportStyles().contains(ReportStyle.PIE_CHART)){
			pieChart = new Chart(ChartType.PIE);
			pieChart.setHeight(50, Unit.PERCENTAGE);
			pieChartConf = pieChart.getConfiguration();
			pieChartConf.setTitle(report.getName());
		}

		Grid grid = null;
		if(report.getType().getReportStyles().contains(ReportStyle.TABULAR)){
			grid = new Grid();
			grid.setWidth(100, Unit.PERCENTAGE);
			grid.setHeight(50, Unit.PERCENTAGE);
			grid.addColumn("Date", String.class);
		}

		Calendar startDayCal = Calendar.getInstance();
		startDayCal.setTime(report.getFrom());
		this.setInsignificantCalendarFieldsToMin(startDayCal);
		long startDateInMillis = startDayCal.getTimeInMillis();
		
		Calendar endDayCal = Calendar.getInstance();
		endDayCal.setTime(report.getTo());
		this.setInsignificantCalendarFieldsToMax(endDayCal);
		long endDateInMillis = endDayCal.getTimeInMillis();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(report.getFrom());
		this.setInsignificantCalendarFieldsToMin(calendar);
		int diffInDays = this.getDiffInDays(endDateInMillis, startDateInMillis);

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
		
		for (int i = 0; i <= diffInDays; ++i) {
			xAxis.addCategory(sdf.format(calendar.getTime()));
			calendar.add(1, Calendar.DAY_OF_YEAR);
		}	
		
		this.createChartsFromReport(report, lineChartSeries, pieChartSeries, grid);
		
		TabBarView layout = new TabBarView();
		if (lineChart != null && lineChartConf != null) {
			lineChartConf.addSeries(lineChartSeries);
			lineChartConf.addxAxis(xAxis);
			
			VerticalLayout lineChartLayout = new VerticalLayout();
			lineChartLayout.addComponent(lineChart);
			layout.addTab(lineChartLayout, "Linechart", FontAwesome.LINE_CHART);
		}

		if (pieChart != null && pieChartConf != null) {
			pieChartConf.addSeries(pieChartSeries);
			
			VerticalLayout pieChartLayout = new VerticalLayout();
			pieChartLayout.addComponent(pieChart);
			layout.addTab(pieChartLayout, "Piechart", FontAwesome.PIE_CHART);
		}
		
		if (grid != null) {
			VerticalLayout gridLayout = new VerticalLayout();
			gridLayout.addComponent(grid);
			gridLayout.setSpacing(false);
			layout.addTab(gridLayout, "Records", FontAwesome.TABLE);
		}
		
		super.setContent(layout);
	}
	
	private void createChartsFromReport(Report<? extends Record> report, ListSeries lineChartSeries, DataSeries pieChartSeries, Grid grid) {
		/*
		 * Initializing the values and categories list. For every day in the reports
		 * timespan an entry is added to the values list and a category with the
		 * formated day is added to the categories list.
		 */
		Calendar startDayCal = Calendar.getInstance();
		startDayCal.setTime(report.getFrom());
		this.setInsignificantCalendarFieldsToMin(startDayCal);
		long startDateInMillis = startDayCal.getTimeInMillis();
		
		Calendar endDayCal = Calendar.getInstance();
		endDayCal.setTime(report.getTo());
		this.setInsignificantCalendarFieldsToMax(endDayCal);
		long endDateInMillis = endDayCal.getTimeInMillis();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(report.getFrom());
		this.setInsignificantCalendarFieldsToMin(calendar);
		int diffInDays = this.getDiffInDays(endDateInMillis, startDateInMillis);

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
		
		List<Number> lineChartValues = null;
		if (lineChartSeries != null) {
			lineChartValues = new ArrayList<Number>();
			for (int i = 0; i <= diffInDays; ++i) {
				lineChartValues.add(0);
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			}
		}
		
		List<Number> pieChartValues = null;
		if (pieChartSeries != null) {
			pieChartValues = new ArrayList<Number>();
		}
		
		switch (report.getType()) {
		case EFFORT:
			if (grid != null) {
				grid.addColumn("Effort", String.class);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index, ((FinancialRecord) record).getEffort());
						}
						
						if (grid != null) {
							grid.addRow(sdf.format(record.getDate()), String.valueOf(((FinancialRecord) record).getEffort()));
						}
					}
				}
			}
			break;
		case CASHFLOW:
			if (grid != null) {
				grid.addColumn("Cashflow", String.class);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index, ((FinancialRecord) record).getCashFlow());
						}

						if (grid != null) {
							grid.addRow(sdf.format(record.getDate()), String.valueOf(((FinancialRecord) record).getCashFlow()));
						}
					}
				}
			}
			break;
		case AVAILABLE_EMPLOYEES:
			if (grid != null) {
				grid.addColumn("Employee", String.class);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index);
						}

						if (grid != null) {
							grid.addRow(sdf.format(record.getDate()), ((PersonalRecord) record).getPersonName());
						}
					}
				}
			}
			break;
		case PATIENTS:
			if (grid != null) {
				grid.addColumn("Patient", String.class);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index);
						}

						if (grid != null) {
							grid.addRow(sdf.format(record.getDate()), ((PatientRecord) record).getIncident());
						}
					}
				}
			}
			break;
		case ABSENT_EMPLOYEES:
			if (grid != null) {
				grid.addColumn("Employee", String.class);
				grid.addColumn("Reason", String.class);
			}
			
			if (pieChartValues != null) {
				for (AbsenceReason reason : AbsenceReason.values()) {
					pieChartValues.add(reason.getDbID());
				}
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index);
						}
						
						if (pieChartValues != null) {
							this.updateValue(pieChartValues, ((PersonalRecord) record).getAbsenceReason().getDbID()-1);
						}
						
						if (grid != null) {
							grid.addRow(sdf.format(record.getDate()), ((PersonalRecord) record).getPersonName(), ((PersonalRecord) record).getAbsenceReason().toString());
						}
					}
				}
			}

			if (pieChartValues != null) {
				for (int i = 0; i < AbsenceReason.values().length; ++i) {
					pieChartSeries.add(new DataSeriesItem(AbsenceReason.values()[i].toString(), pieChartValues.get(i)));
				}
			}
			break;
		case INCIDENTS:
		default:
			if (grid != null) {
				grid.addColumn("Incident", String.class);
			}
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index);
						}

						if (grid != null) {
							grid.addRow(sdf.format(record.getDate()), ((PatientRecord) record).getIncident());
						}
					}
				}
			}
		}
		
		if (lineChartSeries != null) {
			lineChartSeries.setData(lineChartValues);
		}
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