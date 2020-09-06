package com.sapient.week2.DateTimeCalculator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * Class to add or subtract dates. Returns the resultant date.
 */
public class AddSubDates implements Calculator {
	private CalculatorRunner calculatorRunner = new CalculatorRunner();

	@Override
	public Calendar calcMethod(Calendar cal, int choice) {
		Calendar cal2 = Calendar.getInstance();
		cal2 = calculatorRunner.read(cal2);
		CalculatorHistory.sessionObject.setInput(calculatorRunner.dateAsString(cal2));
		if(choice == -1) {
			subtract(cal, cal2);
			return null;
		}
		
		//Add date
		cal.add(Calendar.DATE, cal2.get(Calendar.DATE));
		cal.add(Calendar.MONTH, (cal2.get(Calendar.MONTH) + 1));
		cal.add(Calendar.YEAR, cal2.get(Calendar.YEAR));
		return cal;
	}
	
	public void subtract(Calendar cal1, Calendar cal2) {
		LocalDate date1, date2;
		if(cal1.before(cal2)) {
			date1 = LocalDateTime.ofInstant(cal1.toInstant(), cal1.getTimeZone().toZoneId()).toLocalDate();
			date2 = LocalDateTime.ofInstant(cal2.toInstant(), cal2.getTimeZone().toZoneId()).toLocalDate();
		}
		else {
			date1 = LocalDateTime.ofInstant(cal2.toInstant(), cal2.getTimeZone().toZoneId()).toLocalDate();
			date2 = LocalDateTime.ofInstant(cal1.toInstant(), cal1.getTimeZone().toZoneId()).toLocalDate();
		}
		
		String res = "Days: " + ChronoUnit.DAYS.between(date1, date2) +
						 "\tWeeks: " + ChronoUnit.WEEKS.between(date1, date2) +
						 "\tMonths: " + ChronoUnit.MONTHS.between(date1, date2);
		System.out.println(res);
		CalculatorHistory.sessionObject.setOutput(res);
	}
}
