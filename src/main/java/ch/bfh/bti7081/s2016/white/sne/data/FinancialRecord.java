package ch.bfh.bti7081.s2016.white.sne.data;

/**
 * class representing information from financial data sources (e.g. accounting)  
 * @author team white
 *
 */
public class FinancialRecord extends Record {

	/**
	 * Financial effort (germ. = "Aufwand")
	 * key performance indicator
	 */
	private float effort;
	/**
	 * Financial return (germ. = "Ertrag")
	 * key performance indicator
	 */
	private float returnValue;
	/**
	 * Financial cashflow (
	 * 
	 */
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
