package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordDAO {

	public List<Record> getIncidents(Date from, Date to) {
		// TODO(jan): Implement this method
		List<Record> list = new ArrayList<Record>();
		
		for (int i = 0; i < 10; ++i) {
			PatientRecord record = new PatientRecord();
			record.setIncident("Incident " + i);
			
			list.add(record);
		}
		
		return list;
	}

}
