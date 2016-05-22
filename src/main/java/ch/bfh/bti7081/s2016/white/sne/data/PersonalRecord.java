package ch.bfh.bti7081.s2016.white.sne.data;

public class PersonalRecord extends Record {

	private String personName;
	private boolean available;
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
