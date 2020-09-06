package com.sapient.db.calculatorOperations;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.Read;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorIO;
import com.sapient.db.console.CalculatorRunner;

/**
 * Class to implement transformation of a natural language string to its corresponding date.
 */
public class GetDateFromString implements Calculator {
	private static final String TODAY = "today";
	private static final String TOMORROW = "tomorrow";
	private static final String YESTERDAY = "yesterday";
	private static final String AFTER = "after";
	private static final String BEFORE = "before";
	private static final String DAY = "day";
	private static final String WEEK = "week";
	private static final String MONTH = "month";
	private static final String YEAR = "year";
	private static final String LAST = "last";
	private static final String PREVIOUS = "previous";
	private static final String NEXT = "next";
	private static final String EARLIER = "earlier";
	
	@Override
	public String operation(CalculatorEntity row) {
		System.out.println("Enter string from the following options: " 
	+ "\nToday, Tomorrow, Day after tomorrow, yesterday, Day before yesterday, Last week, "
	+ "\nPrevious week, Next week, Next month, Next Year, Last month, Last year, n Month/s after, "
	+ "\nn Month/s before, n week/s from now, n day/s from now, n month/s from now, "
	+ "\nn year/s from now, n day/s earlier, n week/s earlier, n month/s earlier, n year/s earlier");
		
		Read.sc.nextLine();
		String str = Read.sc.nextLine();
		CalculatorRunner.currentOperation.add("Input string : " + str);
		return convert(str, row);
	}

	public static String convert(String str, CalculatorEntity row) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		row.setOperation("Get date from string");
		row.setPhrase(str);
		
		str = str.toLowerCase();
		int amount = 1;
		
		if(str.contains(TODAY))
			return CalculatorIO.getDate(cal);
		else if(str.contains(TOMORROW) || str.contains(YESTERDAY)) {
			if(str.contains(YESTERDAY))
				amount *= -1;
			if(str.contains(AFTER) || str.contains(BEFORE))
				amount *= 2;
			cal.add(Calendar.DAY_OF_MONTH, amount);
		}
		else {
			if(str.contains(LAST) || str.contains(PREVIOUS) || str.contains(NEXT)) {
				if(str.contains(LAST) || str.contains(PREVIOUS))
					amount *= -1;
			}
			else {
				Pattern pattern = Pattern.compile("\\d+");
				Matcher matcher = pattern.matcher(str);
				if(matcher.find())
					amount *= Integer.parseInt(matcher.group(0));
				
				if(str.contains(EARLIER) || str.contains(BEFORE))
					amount *= -1;
			}
			
			if(str.contains(DAY)) {
				cal.add(Calendar.DAY_OF_MONTH, amount);
			} else if(str.contains(WEEK)) {
				cal.add(Calendar.WEEK_OF_YEAR, amount);
			} else if(str.contains(MONTH))
				cal.add(Calendar.MONTH, amount);
			else if(str.contains(YEAR))
				cal.add(Calendar.YEAR, amount);
		}
		return CalculatorIO.getDate(cal);
	}
}
