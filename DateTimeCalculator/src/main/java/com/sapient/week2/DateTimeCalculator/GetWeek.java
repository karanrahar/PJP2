package com.sapient.week2.DateTimeCalculator;

import java.util.Calendar;

/**
 * Class to get the week number of the year for a given date.
 */
public class GetWeek implements Calculator {

	@Override
	public Calendar calcMethod(Calendar cal, int choice) {
		int number = cal.get(Calendar.WEEK_OF_YEAR);
		System.out.println("\nWeek number for the given date: " + number);
		CalculatorHistory.sessionObject.setOutput(Integer.toString(number));
		return null;
	}

}
