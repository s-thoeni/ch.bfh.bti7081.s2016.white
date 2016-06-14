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
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportStyle;

/**
 * Takes reports and displays them as charts and tables.
 * 
 * @author jdellsperger
 *
 */
public class ReportViewImpl extends NavigationView implements ReportView {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportViewImpl.class);
	
	private static final long serialVersionUID = 2L;

	private List<ReportViewListener> listeners = new ArrayList<ReportViewListener>();
	
	/**
	 * Small structure to save a boolean, which indicates whether a given record is in range
	 * or not, and the index of said record.
	 * 
	 * @author jdellsperger
	 *
	 */
	private class RecordInRange {
		public boolean isInRange;
		public int index;
	}
	
	/**
	 * Takes multiple reports and displays them in a line-chart.
	 * 
	 * @param List<Report<? extends Record>> reports
	 */
	public ReportViewImpl(List<Report<? extends Record>> reports) {
		super();
		// If the list of reports contains a single item, we can handle it as single report.
		if (reports.size() == 1) {
			this.handleSingleReport(reports.get(0));
		} else {
			this.getNavigationBar().setCaption("Report comparison");
			
			// Setting up and styling the line-chart and adding an x-axis.
			XAxis xAxis = new XAxis();
			Chart lineChart = new Chart(ChartType.LINE);
			lineChart.setHeight(50, Unit.PERCENTAGE);
			Configuration lineChartConf = lineChart.getConfiguration();
			lineChartConf.setTitle("Report comparison");
			lineChartConf.addxAxis(xAxis);
			
			// Looping over the reports and adding the data-series to the line-chart.
			for (Report<? extends Record> report : reports) {
				ListSeries lineChartSeries = new ListSeries(report.getName());
				this.createChartsFromReport(report, xAxis, lineChartSeries, null, null);	
				lineChartConf.addSeries(lineChartSeries);
			}

			// Creating a tab-bar-view and adding the line-chart to a tab.
			TabBarView layout = new TabBarView();
			VerticalLayout lineChartLayout = new VerticalLayout();
			lineChartLayout.addComponent(lineChart);
			layout.addTab(lineChartLayout, "Linechart", FontAwesome.LINE_CHART);
			
			super.setContent(layout);
		}
	}
	
	/**
	 * Takes a single report and creates and displays different charts,
	 * depending on the report type.
	 * @param Report<? extends Record> report
	 */
	public ReportViewImpl(Report<? extends Record> report) {
		super();
		this.handleSingleReport(report);
	}
	
	/**
	 * Takes a single report and creates and displays charts from it.
	 * @param report
	 */
	private void handleSingleReport(Report<? extends Record> report) {
		this.getNavigationBar().setCaption(report.getName());

		// Creating and styling a line-chart, if the report can be rendered thusly.
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

		// Creating and styling a pie-chart, if the report can be rendered thusly.
		Chart pieChart = null;
		Configuration pieChartConf = null;
		DataSeries pieChartSeries = new DataSeries(report.getName());
		if(report.getType().getReportStyles().contains(ReportStyle.PIE_CHART)){
			pieChart = new Chart(ChartType.PIE);
			pieChart.setHeight(50, Unit.PERCENTAGE);
			pieChartConf = pieChart.getConfiguration();
			pieChartConf.setTitle(report.getName());
		}

		// Creating and styling a table, if the report can be rendered thusly.
		Table table = null;
		if(report.getType().getReportStyles().contains(ReportStyle.TABULAR)){
			table = new Table();
			table.addContainerProperty("Date", String.class, null);
			table.setWidth(100, Unit.PERCENTAGE);
			table.addStyleName(Reindeer.TABLE_BORDERLESS);
		}
		
		// Creating the appropriate charts from the report.
		this.createChartsFromReport(report, xAxis, lineChartSeries, pieChartSeries, table);
		
		// Creating a tab-bar-view and adding the existing charts.
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
		
		if (table != null) {
			VerticalLayout tableLayout = new VerticalLayout();
			tableLayout.addComponent(table);
			tableLayout.setSpacing(false);
			layout.addTab(tableLayout, "Records", FontAwesome.TABLE);
		}
		
		super.setContent(layout);
	}
	
	private void createChartsFromReport(Report<? extends Record> report, XAxis xAxis, ListSeries lineChartSeries, DataSeries pieChartSeries, Table table) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
		
		// Calculating the number of days between the start- and the end-date of the report.
		Calendar startDateCal = Calendar.getInstance();
		Calendar endDateCal = Calendar.getInstance();
		startDateCal.setTime(report.getFrom());
		endDateCal.setTime(report.getTo());
		this.setInsignificantCalendarFieldsToMin(startDateCal);
		this.setInsignificantCalendarFieldsToMax(endDateCal);
		long startDateInMillis = startDateCal.getTimeInMillis();
		long endDateInMillis = endDateCal.getTimeInMillis();
		int diffInDays = this.getDiffInDays(endDateInMillis, startDateInMillis);
		
		/*
		 * Initializing the values and categories list. For every day in the reports
		 * time-span an entry is added to the values list and a category with the
		 * formated day is added to the categories list.
		 */
		List<Number> lineChartValues = new ArrayList<Number>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(report.getFrom());
		this.setInsignificantCalendarFieldsToMin(cal);
		for (int i = 0; i <= diffInDays; ++i) {
			lineChartValues.add(0);
			String categoryString = sdf.format(cal.getTime());
			try {
				String[] categories = xAxis.getCategories();
				categories[i] += (", " + categoryString);
				xAxis.setCategories(categories);
			} catch (NullPointerException e) {
				xAxis.addCategory(categoryString);
			} catch (IndexOutOfBoundsException e) {
				xAxis.addCategory(categoryString);
			}
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		// Updating line-chart and pie-chart values and adding table-rows, depending on the reports type.
		logger.debug("switch case report type");
		int index = 1;
		switch (report.getType()) {
		case EFFORT:
			logger.debug("type: EFFORT");
			if (table != null) {
				table.addContainerProperty("Effort", String.class, null);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index, ((FinancialRecord) record).getEffort());
						}
						
						if (table != null) {
							table.addItem(new Object[]{sdf.format(record.getDate()), String.valueOf(((FinancialRecord) record).getEffort())}, index++);
						}
					}
				}
			}
			break;
		case CASHFLOW:
			logger.debug("type: CASHFLOW");
			if (table != null) {
				table.addContainerProperty("Cashflow", String.class, null);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof FinancialRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index, ((FinancialRecord) record).getCashFlow());
						}

						if (table != null) {
							table.addItem(new Object[]{sdf.format(record.getDate()), String.valueOf(((FinancialRecord) record).getCashFlow())}, index++);
						}
					}
				}
			}
			break;
		case AVAILABLE_EMPLOYEES:
			logger.debug("type: AVAILABLE_EMPLOYEES");
			if (table != null) {
				table.addContainerProperty("Employee", String.class, null);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index);
						}

						if (table != null) {
							table.addItem(new Object[]{sdf.format(record.getDate()), ((PersonalRecord) record).getPersonName()}, index++);
						}
					}
				}
			}
			break;
		case PATIENTS:
			logger.debug("type: PATIENTS");
			if (table != null) {
				table.addContainerProperty("Patient", String.class, null);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index);
						}

						if (table != null) {
							table.addItem(new Object[]{sdf.format(record.getDate()), ((PatientRecord) record).getPatientName()}, index++);
						}
					}
				}
			}
			break;
		case ABSENT_EMPLOYEES:
			logger.debug("type: ABSENT_EMPLOYEES");
			if (table!= null) {
				table.addContainerProperty("Employee", String.class, null);
				table.addContainerProperty("Reason", String.class, null);
			}
			
			for (Record record : report.getRecords()) {
				if (record instanceof PersonalRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index);
						}

						if (pieChartSeries != null) {
							String reason = ((PersonalRecord) record).getAbsenceReason().toString();
							try {
								DataSeriesItem incidentItem = pieChartSeries.get(reason);
								incidentItem.setY(incidentItem.getY().intValue()+1);
							} catch (NullPointerException e) {
								pieChartSeries.add(new DataSeriesItem(reason, 1));
							}
						}
						
						if (table != null) {
							table.addItem(new Object[]{sdf.format(record.getDate()), ((PersonalRecord) record).getPersonName(), ((PersonalRecord) record).getAbsenceReason().toString()}, index++);
						}
					}
				}
			}
			break;
		case INCIDENTS:
			logger.debug("type: INCIDENTS");
		default:
			logger.debug("default case");
			if (table != null) {
				table.addContainerProperty("Patient", String.class, null);
				table.addContainerProperty("Incident", String.class, null);
			}

			for (Record record : report.getRecords()) {
				if (record instanceof PatientRecord) {
					RecordInRange rir = this.checkIfRecordIsInRange(record, startDateInMillis, diffInDays);
					if (rir.isInRange) {
						if (lineChartValues != null) {
							this.updateValue(lineChartValues, rir.index);
						}

						String typeName = ((PatientRecord) record).getIncidentType().toString();
						if (table != null) {
							table.addItem(new Object[]{sdf.format(record.getDate()), ((PatientRecord) record).getPatientName(), typeName}, index++);
						}
						
						if (pieChartSeries != null) {
							try {
								DataSeriesItem incidentItem = pieChartSeries.get(typeName);
								incidentItem.setY(incidentItem.getY().intValue()+1);
							} catch (NullPointerException e) {
								pieChartSeries.add(new DataSeriesItem(typeName, 1));
							}
						}
					}
				}
			}
		}
		
		// If applicable, setting the data of the line-chart data-series.
		if (lineChartSeries != null) {
			lineChartSeries.setName(report.getName() + ": " + sdf.format(report.getFrom()) + " - " + sdf.format(report.getTo()));
			lineChartSeries.setData(lineChartValues);
		}
	}
	
	/**
	 * Adds value to the position indicated by index in the values list.
	 * @param List<Number> values The list of values in which the position index should be updated.
	 * @param int index The position in the list of values that should be updated.
	 * @param Number newValue The value to be added to the position indicated by index in the list of values.
	 */
	private void updateValue(List<Number> values, int index, Number newValue) {
		logger.debug("->");
		
		float value = values.get(index).floatValue();
		value += newValue.floatValue();
		values.set(index, value);
		logger.debug("<-");
	}
	
	/**
	 * Adds one to the position indicated by index in the values list.
	 * @param List<Number> values The list of values in which the position index should be updated.
	 * @param int index The position in the list of values that should be updated.
	 */
	private void updateValue(List<Number> values, int index) {
		logger.debug("->");
		
		this.updateValue(values, index, 1);
		logger.debug("<-");
	}
	
	/**
	 * Checks if an index, calculated by getting the number of days in between the passed records date and
	 * a passed start date, is lower than the passed maximum index and higher then zero.
	 * @param record The record to be checked.
	 * @param startDateInMillis The start date in milliseconds to calculate the index from.
	 * @param maxIndex The maximum valid index.
	 * @return RecordInRange A structure that holds a boolean, indicating whether the passed record is in range 
	 * and the calculated index.
	 */
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
	
	/**
	 * Calculated the difference in days between two dates passed in milliseconds.
	 * @param endDateInMillis The end date in milliseconds.
	 * @param startDateInMillis The start date in milliseconds.
	 * @return int The number of days between start- and end-date.
	 */
	private int getDiffInDays(long endDateInMillis, long startDateInMillis) {
		logger.debug("->");
		
		long diffInMillis = endDateInMillis-startDateInMillis;
		long longResult = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
		int result = (int)longResult;
		
		logger.debug("<-");
		return result;
	}
	
	/**
	 * Sets hours, minutes, seconds and milliseconds of a passed calendar to
	 * the minimum value.
	 * @param cal The calendar whose values should be changed.
	 */
	private void setInsignificantCalendarFieldsToMax(Calendar cal) {
		logger.debug("->");
		
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		logger.debug("<-");
	}

	/**
	 * Sets hours, minutes, seconds and milliseconds of a passed calendar to
	 * the maximum value.
	 * @param cal The calendar whose values should be changed.
	 */
	private void setInsignificantCalendarFieldsToMin(Calendar cal) {
		logger.debug("->");
		
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
		logger.debug("<-");
	}

	/**
	 * Adds a passed listener to the listeners attribute.
	 */
	public void addListener(ReportViewListener listener) {
		logger.debug("->");
		
		this.listeners.add(listener);
		logger.debug("<-");
	}
}