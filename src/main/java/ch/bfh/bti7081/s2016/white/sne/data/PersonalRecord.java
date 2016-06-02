package ch.bfh.bti7081.s2016.white.sne.data;

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
	private String unavailableReason;

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getUnavailableReason() {
		return this.unavailableReason;
	}

	public void setUnavailableReason(String unavailableReason) {
		this.unavailableReason = unavailableReason;
	}

}
