package com.sapient.calculator.operations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import com.sapient.calculator.CalculatorDetails;
import com.sapient.calculator.DateTimeCalculator;

public class AddSubDates extends DateTimeCalculator {

	@Override
	public String operation(CalculatorDetails calculatorDetails) {
		Calendar cal1 = stringToDate(calculatorDetails.getDate1());
		Calendar cal2 = stringToDate(calculatorDetails.getDate2());
		if(calculatorDetails.getOperation().equals("+"))
			return addDates(cal1, cal2);
		else
			return subDates(cal1, cal2);
	}

	public String addDates(Calendar cal1, Calendar cal2) {
		cal1.add(Calendar.DATE, cal2.get(Calendar.DATE));
		cal1.add(Calendar.MONTH, (cal2.get(Calendar.MONTH) + 1));
		cal1.add(Calendar.YEAR, cal2.get(Calendar.YEAR));
		return dateToString(cal1);
	}

	public String subDates(Calendar cal1, Calendar cal2) {
		LocalDate date1, date2;
		if (cal1.before(cal2)) {
			date1 = LocalDateTime.ofInstant(cal1.toInstant(), cal1.getTimeZone().toZoneId()).toLocalDate();
			date2 = LocalDateTime.ofInstant(cal2.toInstant(), cal2.getTimeZone().toZoneId()).toLocalDate();
		} else {
			date1 = LocalDateTime.ofInstant(cal2.toInstant(), cal2.getTimeZone().toZoneId()).toLocalDate();
			date2 = LocalDateTime.ofInstant(cal1.toInstant(), cal1.getTimeZone().toZoneId()).toLocalDate();
		}

		return "Days: " + ChronoUnit.DAYS.between(date1, date2) + "\tWeeks: " + ChronoUnit.WEEKS.between(date1, date2)
				+ "\tMonths: " + ChronoUnit.MONTHS.between(date1, date2);
	}
}
