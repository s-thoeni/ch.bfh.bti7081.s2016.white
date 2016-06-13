package ch.bfh.bti7081.s2016.white.sne.data;

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
	 * Sets a indicent to record
	 * @param incident
	 */
	public void setIncident(String incident) {
		this.incident = incident;
	}

	@Override
	public String toString() {
		return "PatientRecord [incident=" + this.incident + "]";
	}

}
