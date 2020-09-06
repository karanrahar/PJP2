package com.sapient.db.calculatorOperations;

import java.util.Calendar;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.Read;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorIO;
import com.sapient.db.console.CalculatorRunner;

/**
 * Class to add or subtract days. Returns the resultant date.
 */
public class AddSubDays implements Calculator {
	
	@Override
	public String operation(CalculatorEntity row) {
		Calendar cal = Calendar.getInstance();
		cal = CalculatorIO.read(cal, 1, row);
		
		System.out.println("Enter number of days");
		int days = Read.sc.nextInt();
		CalculatorRunner.currentOperation.add("Input Days : " + days);
		row.setDays(String.valueOf(days));
		
		char ch = CalculatorIO.readChoice();
		if(ch == '+')
			return addDays(cal, days, row);
		else
			return subDays(cal, days, row);
	}
	
	public static String addDays(Calendar cal, int days, CalculatorEntity row) {
		row.setOperation("Add days to date");
		cal.add(Calendar.DAY_OF_MONTH, days);
		return CalculatorIO.getDate(cal);
	}
	
	public static String subDays(Calendar cal, int days, CalculatorEntity row) {
		row.setOperation("Subtract days from date");
		cal.add(Calendar.DAY_OF_MONTH, -1 * days);
		return CalculatorIO.getDate(cal);
	}
}
