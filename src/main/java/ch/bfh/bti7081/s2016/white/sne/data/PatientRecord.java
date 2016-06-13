package ch.bfh.bti7081.s2016.white.sne.data;

import ch.bfh.bti7081.s2016.white.sne.data.enums.IncidentType;

/**
 * Class representing information from treatment data sources (e.g. patient management system) 
 * @author team white
 *
 */
public class PatientRecord extends Record {

	/**
	 * Name of patient
	 */
	private String patientName;
	
	/**
	 * Incident which happened during treatment on the specified date 
	 */
	private String incident;
	
	private IncidentType type;

	/**
	 * Returns patient's name
	 * @return name as string
	 */
	public String getPatientName() {
		return this.patientName;
	}

	/**
	 * Assigns patient name to record
	 * @param patientName
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * Returns incident
	 * @return incident as string
	 */
	public String getIncident() {
		return this.incident;
	}

	/**
	 * Sets a incident to record
	 * @param incident
	 */
	public void setIncident(String incident) {
		this.incident = incident;
	}
	
	public void setIncidentType(IncidentType type) {
		this.type = type;
	}
	
	public IncidentType getIncidentType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "PatientRecord [incident=" + this.incident + "]";
	}

}
