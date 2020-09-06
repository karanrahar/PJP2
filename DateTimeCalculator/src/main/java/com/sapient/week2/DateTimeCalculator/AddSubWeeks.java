package com.sapient.week2.DateTimeCalculator;

import java.util.Calendar;

/**
 * Class to add or subtract weeks. Returns the resultant date.
 */
public class AddSubWeeks implements Calculator {

	@Override
	public Calendar calcMethod(Calendar cal, int choice) {
		System.out.println("Enter number of weeks");
		int weeks = Read.sc.nextInt();
		CalculatorHistory.sessionObject.setInput(Integer.toString(weeks));
		return addSubWeeks(cal, choice * weeks);
	}

	public static Calendar addSubWeeks(Calendar cal, int weeks) {
		cal.add(Calendar.WEEK_OF_YEAR, weeks);
		return cal;
	}

}
