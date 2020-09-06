package com.sapient.week2.DateTimeCalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to execute the DateTimeCalculator. Accept inputs from the user and display the result.
 */
public class CalculatorRunner {
	private static final String DATEFORMAT = "dd-M-yyyy";

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		CalculatorHistory calculatorSessionHistory = new CalculatorHistory();
		
		char ch = 'y';
		while(ch == 'y' || ch == 'Y') {
			System.out.println("Enter option: ");
			calculatorSessionHistory.perfomOperation(cal);
			System.out.println("Do you want to continue? (y/n)");
			ch = Read.sc.next().charAt(0);
			System.out.println();
		}
	}
	
	public Calendar read(Calendar cal) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT, Locale.US);
    	try {
    		System.out.println("Enter date in the format - dd-m-yyyy");
			cal.setTime(sdf.parse(Read.sc.next()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return cal;
    }
	
	public void printDate(Calendar cal) {
		System.out.println("\n" + cal.get(Calendar.DATE) + "-" + 
		    	(cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.YEAR)
		    	+ "\n" + dateAsString(cal) + "\n");
	} 
	
	public String dateAsString(Calendar cal) {
		return new SimpleDateFormat("MMMM dd, yyyy").format(cal.getTime());
	}

	
}
