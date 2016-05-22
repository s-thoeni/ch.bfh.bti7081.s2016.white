package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.Date;

public class PatientRecord extends Record {

	private String patientName;
	private String incident;
	private Date entryDate;
	private Date exitDate;

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getIncident() {
		return this.incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getExitDate() {
		return this.exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	@Override
	public String toString() {
		return "PatientRecord [incident=" + this.incident + ", exitDate=" + this.exitDate + "]";
	}

}
