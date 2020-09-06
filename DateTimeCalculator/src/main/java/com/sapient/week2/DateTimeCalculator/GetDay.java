package com.sapient.week2.DateTimeCalculator;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to get the day of the week for a given date.
 */
public class GetDay implements Calculator {

	@Override
	public Calendar calcMethod(Calendar cal, int choice) {
		String day = new SimpleDateFormat("EEEE").format(cal.getTime());
		System.out.println("\nDay for date is: " + day);
		CalculatorHistory.sessionObject.setOutput(day);
		return null;
	}

}
