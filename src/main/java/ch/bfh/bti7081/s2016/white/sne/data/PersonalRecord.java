package ch.bfh.bti7081.s2016.white.sne.data;

import ch.bfh.bti7081.s2016.white.sne.data.enums.AbsenceReason;

/**
 * Class representing information from human resource data sources
 * @author team white
 *
 */
public class PersonalRecord extends Record {
	
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
		return this.personName;
	}

	/**
	 * Set employee's name
	 * @param personName
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * Returns availability of employee
	 * @return availability as boolean
	 */
	public boolean isAvailable() {
		return this.available;
	}

	/**
	 * Sets availability of employee
	 * @param available
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * Returns a text describing why employee is absent.
	 * This value is only set when isAvailable() is set to false.
	 * @return unavailableReason as string
	 */
	public AbsenceReason getAbsenceReason() {
		return this.absenceReason;
	}

	/**
	 * Assigns a text to the record describing why employee is absent
	 * @param unavailableReason
	 */
	public void setAbsenceReason(AbsenceReason absenceReason) {
		this.absenceReason = absenceReason;
	}

	@Override
	public String toString() {
		return "PersonalRecord [personName=" + personName + ", available=" + available + ", unavailableReason="
				+ this.absenceReason + "]";
	}
}
