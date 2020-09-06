package com.sapient.week2.DateTimeCalculator;

import java.util.Calendar;

/**
 * Class to add or subtract years. Returns the resultant date.
 */
public class AddSubYears implements Calculator {

	@Override
	public Calendar calcMethod(Calendar cal, int choice) {
		System.out.println("Enter number of years");
		int years = Read.sc.nextInt();
		CalculatorHistory.sessionObject.setInput(Integer.toString(years));
		return addSubYears(cal, choice * years);
	}
	
	public static Calendar addSubYears(Calendar cal, int years) {
		cal.add(Calendar.YEAR, years);
		return cal;
	}
}
