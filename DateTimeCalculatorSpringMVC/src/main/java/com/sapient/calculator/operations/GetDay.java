package com.sapient.calculator.operations;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sapient.calculator.CalculatorDetails;
import com.sapient.calculator.DateTimeCalculator;

public class GetDay extends DateTimeCalculator {

	@Override
	public String operation(CalculatorDetails calculatorDetails) {
		Calendar cal = stringToDate(calculatorDetails.getDate1());
		return new SimpleDateFormat("EEEE").format(cal.getTime());
	}
}
