package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

public class ReportDaoImpl implements ReportDao {

	@Override
	public Report getAvailableEmployee(Date from, Date to) {
		return new Report("Anwesendes Personal");
	}

	@Override
	public Report getSickLeaves(Date from, Date to) {
		return new Report("Abwesendes Personal");
	}

	@Override
	public Report getIncidents(Date from, Date to) {
		Report result = new Report("Notfälle");
		result.setSummary("Anzahl der Notfälle über einen Zeitraum.");
		result.setFrom(from);
		result.setTo(to);
		
		Calendar calendar = Calendar.getInstance();
		
		List<Record> records = new ArrayList<Record>();
		
		for (int i = 0; i < 4; ++i) {
			PatientRecord record = new PatientRecord();
			record.setIncident("Incident" + i);
			record.setDate(calendar.getTime());
			records.add(record);
		}
		result.setRecords(records);
		return result;
	}

	@Override
	public Report getEntriesExits(Date from, Date to) {
		return new Report("Aktuelle Patientanzahl");
	}

	@Override
	public Report getPatientCount(Date from, Date to) {
		return new Report("CashFlow aktuel");
	}

	@Override
	public Report getEffort(Date from, Date to) {
		return new Report("Aufwände aktuelles Quartal");
	}

	@Override
	public Report getCashFlow(Date from, Date to) {
		return new Report("Suizide heute");
	}

}