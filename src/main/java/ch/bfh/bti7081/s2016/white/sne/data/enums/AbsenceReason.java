package ch.bfh.bti7081.s2016.white.sne.data.enums;

public enum AbsenceReason {

	ILLNESS(1, "Illness"),
	VACATION(2, "Vacation"),
	OVERSLEPT(3, "Overslept"),
	COMPENSATION(4, "Compensation"),
	APPOINTMENT(5, "Appointment"),
	EDUCATION(6, "Education"),
	PREGNANCY(7, "Pregnancy"),
	OTHER(8, "Other");
	
	private String reason;
	private int dbID;
	
	private AbsenceReason(int dbID, String reason) {
		this.dbID = dbID;
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return this.reason;
	}
	
	public int getDbID() {
		return this.dbID;
	}
	
}
