package com.sapient.calculator.operations;

import java.util.Calendar;

import com.sapient.calculator.CalculatorDetails;
import com.sapient.calculator.DateTimeCalculator;

public class AddSubDays extends DateTimeCalculator {

	@Override
	public String operation(CalculatorDetails calculatorDetails) {
		Calendar cal = stringToDate(calculatorDetails.getDate1());
		int days = Integer.parseInt(calculatorDetails.getDays());
		if(calculatorDetails.getOperation().equals("-"))
			days = -days;
		cal.add(Calendar.DAY_OF_MONTH, days);
		return dateToString(cal);
	}
}
