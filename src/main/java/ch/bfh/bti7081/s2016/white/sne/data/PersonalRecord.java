package ch.bfh.bti7081.s2016.white.sne.data;

import ch.bfh.bti7081.s2016.white.sne.data.enums.AbsenceReason;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class representing information from human resource data sources
 * @author team white
 *
 */
public class PersonalRecord extends Record {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(PersonalRecord.class);
	
	/**
	 * An employee's name
	 */
	private String personName;
	
	/**
	 * Information about presence of employee
	 */
	private boolean available;
	
	/**
	 * Reason why employee is absent. Only used when variable
	 * "available" is set to false.
	 */
	private AbsenceReason absenceReason;

	/**
	 * Default constructor
	 */
	public PersonalRecord() {
		super();
	}
	
	/**
	 * Get employee's name
	 * @return employee's name as string
	 */
	public String getPersonName() {
		logger.debug("->");
		logger.debug("<-");
		return this.personName;
	}

	/**
	 * Set employee's name
	 * @param personName
	 */
	public void setPersonName(String personName) {
		logger.debug("->");
		
		this.personName = personName;
		logger.debug("<-");
	}

	/**
	 * Returns availability of employee
	 * @return availability as boolean
	 */
	public boolean isAvailable() {
		logger.debug("->");
		logger.debug("<-");
		return this.available;
	}

	/**
	 * Sets availability of employee
	 * @param available
	 */
	public void setAvailable(boolean available) {
		logger.debug("->");
		
		this.available = available;
		logger.debug("<-");
	}

	/**
	 * Returns a text describing why employee is absent.
	 * This value is only set when isAvailable() is set to false.
	 * @return unavailableReason as string
	 */
	public AbsenceReason getAbsenceReason() {
		logger.debug("->");
		logger.debug("<-");
		return this.absenceReason;
	}

	/**
	 * Assigns a text to the record describing why employee is absent
	 * @param unavailableReason
	 */
	public void setAbsenceReason(AbsenceReason absenceReason) {
		logger.debug("->");
		this.absenceReason = absenceReason;
		logger.debug("<-");
	}

	@Override
	public String toString() {
		logger.debug("->");
		logger.debug("<-");
		return "PersonalRecord [personName=" + personName + ", available=" + available + ", unavailableReason="
				+ this.absenceReason + "]";
	}
}
