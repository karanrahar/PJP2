package com.sapient.db.calculatorOperations;

import java.util.Calendar;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.Read;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorIO;
import com.sapient.db.console.CalculatorRunner;

/**
 * Class to add or subtract years. Returns the resultant date.
 */
public class AddSubYears implements Calculator {
	
	@Override
	public String operation(CalculatorEntity row) {
		Calendar cal = Calendar.getInstance();
		cal = CalculatorIO.read(cal, 1, row);
		
		System.out.println("Enter number of years");
		int years = Read.sc.nextInt();
		CalculatorRunner.currentOperation.add("Input years : " + years);
		row.setYear(String.valueOf(years));
		
		char ch = CalculatorIO.readChoice();
		if(ch == '+')
			return addYears(cal, years, row);
		else
			return subYears(cal, years, row);
	}
	
	public static String addYears(Calendar cal, int years, CalculatorEntity row) {
		row.setOperation("Add years to date");
		cal.add(Calendar.YEAR, years);
		return CalculatorIO.getDate(cal);
	}
	
	public static String subYears(Calendar cal, int years, CalculatorEntity row) {
		row.setOperation("Subtract years from date");
		cal.add(Calendar.YEAR, -1 * years);
		return CalculatorIO.getDate(cal);
	}
}
