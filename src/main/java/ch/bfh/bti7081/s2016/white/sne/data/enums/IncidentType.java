package ch.bfh.bti7081.s2016.white.sne.data.enums;

public enum IncidentType {

	VULGARITY(1, "Vulgarity"),
	VIOLENCE(2, "Violence"),
	DROP(3, "Drop"),
	DRUNKENNESS(4, "Drunkenness"),
	DRUG_CONSUMPTION(5, "Drug Consumption"),
	DRUG_ABUSE(6, "Drug Abuse"),
	SEXUAL_ABUSE(7, "Sexual Abuse"),
	SUICIDE(8, "Suicide");

	private String typeName;
	private int dbID;
	
	private IncidentType(int dbID, String typeName) {
		this.dbID = dbID;
		this.typeName = typeName;
	}
	
	@Override
	public String toString() {
		return this.typeName;
	}
	
	public int getDbID() {
		return this.dbID;
	}
	
}
