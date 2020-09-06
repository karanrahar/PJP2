package com.sapient.week2.DateTimeCalculator;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
	public Calendar calcMethod(Calendar cal, int choice) {
		System.out.println("Enter string from the following options: " 
	+ "\nToday, Tomorrow, Day after tomorrow, yesterday, Day before yesterday, Last week, "
	+ "\nPrevious week, Next week, Next month, Next Year, Last month, Last year, n Month/s after, "
	+ "\nn Month/s before, n week/s from now, n day/s from now, n month/s from now, "
	+ "\nn year/s from now, n day/s earlier, n week/s earlier, n month/s earlier, n year/s earlier");
		Date date = new Date();
		cal.setTime(date);
		
		Read.sc.nextLine();
		String str = Read.sc.nextLine().toLowerCase();
		CalculatorHistory.sessionObject.setInput(str);
		
		System.out.print("\nCurrent Date: ");
		new CalculatorRunner().printDate(cal);
		int amount = 1;
		
		if(str.contains(TODAY))
			return cal;
		else if(str.contains(TOMORROW) || str.contains(YESTERDAY)) {
			if(str.contains(YESTERDAY))
				amount *= -1;
			if(str.contains(AFTER) || str.contains(BEFORE))
				amount *= 2;
			cal = AddSubDays.addSubDays(cal, amount);
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
				cal = AddSubDays.addSubDays(cal, amount);
			} else if(str.contains(WEEK)) {
				cal = AddSubWeeks.addSubWeeks(cal, amount);
			} else if(str.contains(MONTH))
				cal = AddSubMonths.addSubMonths(cal, amount);
			else if(str.contains(YEAR))
				cal = AddSubYears.addSubYears(cal, amount);
		}
		
		System.out.print("\nDate " + str +" : ");
		return cal;
	}
}
