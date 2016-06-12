package ch.bfh.bti7081.s2016.white.sne.data.enums;

import java.util.Calendar;
import java.util.Date;

/**
 * ENUM for predefined time ranges
 * @author team white
 *
 */
public enum ReportTimeframe {
YESTERDAY{
	@Override
	public DatePair getConcreteDate() {
		DatePair dp = new DatePair();
		Calendar c = Calendar.getInstance();
		
		c.add(Calendar.DATE, -1);

		dp.setTo(new Date(c.getTimeInMillis()));
		dp.setFrom(new Date(c.getTimeInMillis()));
		return dp;
	}
},
 LAST_WEEK{

	@Override
	public DatePair getConcreteDate() {
		DatePair dp = new DatePair();
		Calendar c = Calendar.getInstance();
		
		dp.setTo(new Date(c.getTimeInMillis()));
		
		c.add(Calendar.WEEK_OF_YEAR, -1);
		
		dp.setFrom(new Date(c.getTimeInMillis()));
		return dp;
	}
	
},
 CURRENT_WEEK{

	@Override
	public DatePair getConcreteDate() {
		DatePair dp = new DatePair();
		Calendar c = Calendar.getInstance();
		
		dp.setTo(new Date(c.getTimeInMillis()));
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		dp.setFrom(new Date(c.getTimeInMillis()));
		return dp;
	}
	
},
 TODAY{
	@Override
	public DatePair getConcreteDate() {
		DatePair dp = new DatePair();
		Calendar c = Calendar.getInstance();
		
		dp.setTo(new Date(c.getTimeInMillis()));
		dp.setFrom(new Date(c.getTimeInMillis()));
		return dp;
	}
	
};

/**
 * returns the concrete time frame corresponding to the ReportTimeFrame
 * 
 * @return
 */
public abstract DatePair getConcreteDate();
	
}
