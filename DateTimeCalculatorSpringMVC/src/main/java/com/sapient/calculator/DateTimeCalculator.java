package com.sapient.calculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class DateTimeCalculator {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	
	public abstract String operation(CalculatorDetails calculatorDetails);
	
	public Calendar stringToDate(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}

	protected String dateToString(Calendar cal) {
		return (cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR));
	}
}
