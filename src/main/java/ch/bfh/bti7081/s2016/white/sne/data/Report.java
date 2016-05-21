package ch.bfh.bti7081.s2016.white.sne.data;

public class Report {

	private String name;
	
	// FIXME: int?!
	private String summary;

	public Report(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
