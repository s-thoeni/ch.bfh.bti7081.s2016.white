package ch.bfh.bti7081.s2016.white.sne.data;

/**
 * Class representing information from treatment data sources (e.g. patient management system) 
 * @author team white
 *
 */
public class PatientRecord extends Record {

	private String patientName;
	private String incident;

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

	@Override
	public String toString() {
		return "PatientRecord [incident=" + this.incident + "]";
	}

}
