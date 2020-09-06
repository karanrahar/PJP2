package com.sapient.db.calculatorOperations;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorIO;

/**
 * Class to get the day of the week for a given date.
 */
public class GetDay implements Calculator {

	@Override
	public String operation(CalculatorEntity row) {
		Calendar cal = Calendar.getInstance();
		cal = CalculatorIO.read(cal, 1, row);
		return getDay(cal, row);
		
	}

	public static String getDay(Calendar cal, CalculatorEntity row) {
		row.setOperation("Get day of the week");
		return new SimpleDateFormat("EEEE").format(cal.getTime());
	}

}
