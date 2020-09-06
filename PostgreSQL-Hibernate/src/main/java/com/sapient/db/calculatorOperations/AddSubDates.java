package com.sapient.db.calculatorOperations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorIO;

/**
 * Class to add or subtract dates. Returns the resultant date.
 */
public class AddSubDates implements Calculator {

	@Override
	public String operation(CalculatorEntity row) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1 = CalculatorIO.read(cal1, 1, row);
		cal2 = CalculatorIO.read(cal2, 2, row);
		char ch = CalculatorIO.readChoice();
		if(ch == '+')
			return addDates(cal1, cal2, row);
		else 
			return subDates(cal1, cal2, row);
	}
	
	public static String addDates(Calendar cal1, Calendar cal2, CalculatorEntity row) {
		row.setOperation("Add two dates");
		cal1.add(Calendar.DATE, cal2.get(Calendar.DATE));
		cal1.add(Calendar.MONTH, (cal2.get(Calendar.MONTH) + 1));
		cal1.add(Calendar.YEAR, cal2.get(Calendar.YEAR));
		return CalculatorIO.getDate(cal1);
	}
	
	public static String subDates(Calendar cal1, Calendar cal2, CalculatorEntity row) {
		row.setOperation("Subtact two dates");
		LocalDate date1, date2;
		if(cal1.before(cal2)) {
			date1 = LocalDateTime.ofInstant(cal1.toInstant(), cal1.getTimeZone().toZoneId()).toLocalDate();
			date2 = LocalDateTime.ofInstant(cal2.toInstant(), cal2.getTimeZone().toZoneId()).toLocalDate();
		}
		else {
			date1 = LocalDateTime.ofInstant(cal2.toInstant(), cal2.getTimeZone().toZoneId()).toLocalDate();
			date2 = LocalDateTime.ofInstant(cal1.toInstant(), cal1.getTimeZone().toZoneId()).toLocalDate();
		}
		
		return "Days: " + ChronoUnit.DAYS.between(date1, date2) +
			   "\tWeeks: " + ChronoUnit.WEEKS.between(date1, date2) +
			   "\tMonths: " + ChronoUnit.MONTHS.between(date1, date2);
	}
}
