package com.sapient.week2.DateTimeCalculator;

import java.util.Calendar;

/**
 * Class to add or subtract days. Returns the resultant date.
 */
public class AddSubDays implements Calculator {

	@Override
	public Calendar calcMethod(Calendar cal, int choice) {
		System.out.println("Enter number of days");
		int days = Read.sc.nextInt();
		CalculatorHistory.sessionObject.setInput(Integer.toString(days));
		return addSubDays(cal, choice * days);
	}
	
	public static Calendar addSubDays(Calendar cal, int days) {
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal;
	}
}
