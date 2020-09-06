package com.sapient.calculator.operations;

import java.util.Calendar;

import com.sapient.calculator.CalculatorDetails;
import com.sapient.calculator.DateTimeCalculator;

public class AddSubWeeks extends DateTimeCalculator {

	@Override
	public String operation(CalculatorDetails calculatorDetails) {
		Calendar cal = stringToDate(calculatorDetails.getDate1());
		int weeks = Integer.parseInt(calculatorDetails.getWeeks());
		if(calculatorDetails.getOperation().equals("-"))
			weeks = -weeks;
		cal.add(Calendar.WEEK_OF_YEAR, weeks);
		return dateToString(cal);
	}
}
