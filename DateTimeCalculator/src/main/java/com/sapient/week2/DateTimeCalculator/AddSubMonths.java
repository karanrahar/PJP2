package com.sapient.week2.DateTimeCalculator;

import java.util.Calendar;

/**
 * Class to add or subtract months. Returns the resultant date.
 */
public class AddSubMonths implements Calculator {

	@Override
	public Calendar calcMethod(Calendar cal, int choice) {
		System.out.println("Enter number of months");
		int months = Read.sc.nextInt();
		CalculatorHistory.sessionObject.setInput(Integer.toString(months));
		return addSubMonths(cal, choice * months);
	}
	
	public static Calendar addSubMonths(Calendar cal, int months) {
		cal.add(Calendar.MONTH, months);
		return cal;
	}
}
