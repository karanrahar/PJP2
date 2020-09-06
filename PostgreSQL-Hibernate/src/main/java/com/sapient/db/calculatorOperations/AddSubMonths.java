package com.sapient.db.calculatorOperations;

import java.util.Calendar;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.Read;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorIO;
import com.sapient.db.console.CalculatorRunner;

/**
 * Class to add or subtract months. Returns the resultant date.
 */
public class AddSubMonths implements Calculator {
	
	@Override
	public String operation(CalculatorEntity row) {
		Calendar cal = Calendar.getInstance();
		cal = CalculatorIO.read(cal, 1, row);
		
		System.out.println("Enter number of months");
		int months = Read.sc.nextInt();
		CalculatorRunner.currentOperation.add("Input Months : " + months);
		row.setMonths(String.valueOf(months));
		
		char ch = CalculatorIO.readChoice();
		if(ch == '+')
			return addMonths(cal, months, row);
		else
			return subMonths(cal, months, row);
	}
	
	public static String addMonths(Calendar cal, int months, CalculatorEntity row) {
		row.setOperation("Add months to date");
		cal.add(Calendar.MONTH, months);
		return CalculatorIO.getDate(cal);
	}
	
	public static String subMonths(Calendar cal, int months, CalculatorEntity row) {
		row.setOperation("Subtract months from date");
		cal.add(Calendar.MONTH, -1 * months);
		return CalculatorIO.getDate(cal);
	}
}