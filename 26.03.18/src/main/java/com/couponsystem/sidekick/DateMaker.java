package com.couponsystem.sidekick;

import java.util.Calendar;
import java.util.Date;

/***
 * Date helper
 * @author מיתר
 *
 */
public class DateMaker {

	/***
	 * Providing date by parameters
	 * @param year
	 * @param month
	 * @param day
	 * @return Date - util
	 */
	public static Date fixDate(int year , int month , int day)
	{
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(year, month , day);
		date = cal.getTime();
		
		return date;
	}
}
