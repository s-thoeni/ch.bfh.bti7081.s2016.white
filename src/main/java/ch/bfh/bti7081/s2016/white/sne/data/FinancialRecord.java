package ch.bfh.bti7081.s2016.white.sne.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class representing information from financial data sources (e.g. accounting)  
 * @author team white
 *
 */
public class FinancialRecord extends Record {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(FinancialRecord.class);
	
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
		logger.debug("->");
		logger.debug("<-");
		return this.effort;
	}

	/**
	 * Assigns effort value to the record
	 * @param effort
	 */
	public void setEffort(float effort) {
		logger.debug("->");
		
		this.effort = effort;
		logger.debug("<-");
	}

	/**
	 * Returns financial return of the specified date 
	 * @return return as int
	 */
	public float getReturnValue() {
		logger.debug("->");
		logger.debug("<-");
		return this.returnValue;
	}

	/**
	 * Assigns financial return value to the record
	 * @param return
	 */
	public void setReturnValue(float returnValue) {
		logger.debug("->");
		
		this.returnValue = returnValue;
		logger.debug("<-");
	}

	/**
	 * Returns cash flow of the specified date 
	 * @return cash flow as int
	 */
	public float getCashFlow() {
		logger.debug("->");
		logger.debug("<-");
		return this.cashFlow;
	}

	/**
	 * Assigns cash flow to the record
	 * @param cash flow
	 */
	public void setCashFlow(float cashFlow) {
		logger.debug("->");
		
		this.cashFlow = cashFlow;
		logger.debug("<-");
	}

	@Override
	public String toString() {
		logger.debug("->");
		logger.debug("<-");
		return "FinancialRecord [effort=" + effort + ", returnValue=" + returnValue + ", cashFlow=" + cashFlow + "]";
	}
	
}
