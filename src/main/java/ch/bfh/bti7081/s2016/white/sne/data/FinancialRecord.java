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
	 * Financial cashflow
	 * (the simple way: return - effort + write-off)
	 * 
	 */
	private float cashFlow;
	
	/**
	 * Returns effort of the specified date 
	 * @return effort as int
	 */
	public float getEffort() {
		return this.effort;
	}

	/**
	 * Assigns effort value to the record
	 * @param effort
	 */
	public void setEffort(float effort) {
		this.effort = effort;
	}

	/**
	 * Returns financial return of the specified date 
	 * @return return as int
	 */
	public float getReturnValue() {
		return this.returnValue;
	}

	/**
	 * Assigns financial return value to the record
	 * @param return
	 */
	public void setReturnValue(float returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * Returns cash flow of the specified date 
	 * @return cash flow as int
	 */
	public float getCashFlow() {
		return this.cashFlow;
	}

	/**
	 * Assigns cash flow to the record
	 * @param cash flow
	 */
	public void setCashFlow(float cashFlow) {
		this.cashFlow = cashFlow;
	}

	@Override
	public String toString() {
		return "FinancialRecord [effort=" + effort + ", returnValue=" + returnValue + ", cashFlow=" + cashFlow + "]";
	}
	
}
