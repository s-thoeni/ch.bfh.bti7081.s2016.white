package ch.bfh.bti7081.s2016.white.sne.data;

public class FinancialRecord extends Record {

	private float effort;
	private float returnValue;
	private float cashFlow;

	public float getEffort() {
		return this.effort;
	}

	public void setEffort(float effort) {
		this.effort = effort;
	}

	public float getReturnValue() {
		return this.returnValue;
	}

	public void setReturnValue(float returnValue) {
		this.returnValue = returnValue;
	}

	public float getCashFlow() {
		return this.cashFlow;
	}

	public void setCashFlow(float cashFlow) {
		this.cashFlow = cashFlow;
	}

}
