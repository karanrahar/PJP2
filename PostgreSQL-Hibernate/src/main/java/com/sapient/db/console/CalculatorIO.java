package com.sapient.db.console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.Read;

/**
 * Class to execute the DateTimeCalculator. Accept inputs from the user and display the result.
 */
public class CalculatorIO {
	private static final String DATEFORMAT = "dd-M-yyyy";
	
	public static Calendar read(Calendar cal, int dateNo, CalculatorEntity row) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT, Locale.US);
    	try {
    		System.out.println("Enter date in the format - dd-m-yyyy");
			cal.setTime(sdf.parse(Read.sc.next()));
			if(dateNo == 1)
				row.setDate1(getDate(cal));
			else 
				row.setDate2(getDate(cal));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	CalculatorRunner.currentOperation.add("Input Date : " + getDate(cal));
    	return cal;
    } 
	
	public static char readChoice() {
		System.out.println("Enter '+' to add and '-' to subtract");
		char ch = Read.sc.next().charAt(0);
		CalculatorRunner.currentOperation.add("Input '+' or '-' : " + Character.toString(ch));
		return ch;
	}
	
	public static String getDate(Calendar cal) {
		return cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.YEAR);
	}
	
	public String dateAsString(Calendar cal) {
		return new SimpleDateFormat("MMMM dd, yyyy").format(cal.getTime());
	}	
}
