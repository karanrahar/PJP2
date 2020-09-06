package com.sapient.calculator.operations;

import java.util.Calendar;

import com.sapient.calculator.CalculatorDetails;
import com.sapient.calculator.DateTimeCalculator;

public class AddSubYears extends DateTimeCalculator {

	@Override
	public String operation(CalculatorDetails calculatorDetails) {
		Calendar cal = stringToDate(calculatorDetails.getDate1());
		int years = Integer.parseInt(calculatorDetails.getYears());
		if(calculatorDetails.getOperation().equals("-"))
			years = -years;
		cal.add(Calendar.YEAR, years);
		return dateToString(cal);
	}
}
