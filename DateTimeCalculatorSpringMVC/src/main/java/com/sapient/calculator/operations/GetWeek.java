package com.sapient.calculator.operations;

import java.util.Calendar;

import com.sapient.calculator.CalculatorDetails;
import com.sapient.calculator.DateTimeCalculator;

public class GetWeek extends DateTimeCalculator {

	@Override
	public String operation(CalculatorDetails calculatorDetails) {
		Calendar cal = stringToDate(calculatorDetails.getDate1());
		return String.valueOf(cal.get(Calendar.WEEK_OF_YEAR));
	}

}
