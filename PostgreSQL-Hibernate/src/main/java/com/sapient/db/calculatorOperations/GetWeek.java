package com.sapient.db.calculatorOperations;

import java.util.Calendar;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorIO;

/**
 * Class to get the week number of the year for a given date.
 */
public class GetWeek implements Calculator {

	@Override
	public String operation(CalculatorEntity row) {
		Calendar cal = Calendar.getInstance();
		cal = CalculatorIO.read(cal, 1, row);
		return getWeekNo(cal, row);
	}

	public static String getWeekNo(Calendar cal, CalculatorEntity row) {
		row.setOperation("Get week number");
		return Integer.toString(cal.get(Calendar.WEEK_OF_YEAR));
	}
}
