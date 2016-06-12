package ch.bfh.bti7081.s2016.white.sne.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing information from treatment data sources (e.g. patient management system) 
 * @author team white
 *
 */
public class PatientRecord extends Record {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(PatientRecord.class);
	
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
		logger.debug("->");
		logger.debug("<-");
		return this.patientName;
	}

	/**
	 * Assigns patient name to record
	 * @param patientName
	 */
	public void setPatientName(String patientName) {
		logger.debug("->");
		
		this.patientName = patientName;
		logger.debug("<-");
	}

	/**
	 * Returns incident
	 * @return incident as string
	 */
	public String getIncident() {
		logger.debug("->");
		logger.debug("<-");
		return this.incident;
	}

	/**
	 * Sets a indicent to record
	 * @param incident
	 */
	public void setIncident(String incident) {
		logger.debug("->");
		
		this.incident = incident;
		logger.debug("<-");
	}

	@Override
	public String toString() {
		logger.debug("->");
		logger.debug("<-");
		return "PatientRecord [incident=" + this.incident + "]";
	}

}
