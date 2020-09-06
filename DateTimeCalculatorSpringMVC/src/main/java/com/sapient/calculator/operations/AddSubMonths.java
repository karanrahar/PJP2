package com.sapient.calculator.operations;

import java.util.Calendar;

import com.sapient.calculator.CalculatorDetails;
import com.sapient.calculator.DateTimeCalculator;

public class AddSubMonths extends DateTimeCalculator {

	@Override
	public String operation(CalculatorDetails calculatorDetails) {
		Calendar cal = stringToDate(calculatorDetails.getDate1());
		int months = Integer.parseInt(calculatorDetails.getMonths());
		if(calculatorDetails.getOperation().equals("-"))
			months = -months;
		cal.add(Calendar.MONTH, months);
		return dateToString(cal);
	}
}
