package com.sapient.db.calculatorOperations;

import java.util.Calendar;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.Read;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorIO;
import com.sapient.db.console.CalculatorRunner;

/**
 * Class to add or subtract weeks. Returns the resultant date.
 */
public class AddSubWeeks implements Calculator {

	@Override
	public String operation(CalculatorEntity row) {
		Calendar cal = Calendar.getInstance();
		cal = CalculatorIO.read(cal, 1, row);

		System.out.println("Enter number of weeks");
		int weeks = Read.sc.nextInt();
		CalculatorRunner.currentOperation.add("Input Weeks : " + weeks);
		row.setWeeks(String.valueOf(weeks));

		char ch = CalculatorIO.readChoice();
		if (ch == '+')
			return addWeeks(cal, weeks, row);
		else
			return subWeeks(cal, weeks, row);
	}

	public static String addWeeks(Calendar cal, int weeks, CalculatorEntity row) {
		row.setOperation("Add weeks to date");
		cal.add(Calendar.WEEK_OF_YEAR, weeks);
		return CalculatorIO.getDate(cal);
	}
	
	public static String subWeeks(Calendar cal, int weeks, CalculatorEntity row) {
		row.setOperation("Subtract weeks from date");
		cal.add(Calendar.WEEK_OF_YEAR, -1 * weeks);
		return CalculatorIO.getDate(cal);
	}
}
