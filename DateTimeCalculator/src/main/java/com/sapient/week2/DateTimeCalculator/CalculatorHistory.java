package com.sapient.week2.DateTimeCalculator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class to perform the calculator operation selected by the user 
 * and store the history of operations in a file.
 */
public class CalculatorHistory extends CalculatorRunner{
	private static final String file = "DateTimeCalculator_History";
	private static Map<Integer, String> menuOptions;
	static SessionObject sessionObject;
	
//	for session history
//	private static ArrayList<SessionObject> history = new ArrayList<>(); ;
	
	public CalculatorHistory() {
		menuOptions = new HashMap<>();
		menuOptions.put(0, "View History");
		menuOptions.put(1, "Add Dates");
		menuOptions.put(2, "Add Days");
		menuOptions.put(3, "Add Weeks");
		menuOptions.put(4, "Add Months");
		menuOptions.put(5, "Add Years");
		menuOptions.put(6, "Subtract Dates");
		menuOptions.put(7, "Subtract Days");
		menuOptions.put(8, "Subtract Weeks");
		menuOptions.put(9, "Subtract Months");
		menuOptions.put(10, "Subtract Years");
		menuOptions.put(11, "Get day of the week for a date");
		menuOptions.put(12, "Get week number for a date");
		menuOptions.put(13, "Get date from a string");
		
		sessionObject = new SessionObject();
	}
	
	public void perfomOperation(Calendar cal) {
//		for session history
//		sessionObject = new SessionObject();
		printMenuOption();
		int choice = Read.sc.nextInt();
		sessionObject.setOperation(menuOptions.get(choice));
		
		if(choice == 0) {
//			for session history
//			showSessionHistory();
			showHistory();
			sessionObject.setOutput("History displayed");
		}
		else {
			Calculator targetOperation = FactoryClass
				      .getOperation(choice)
				      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
			if(choice != 13) {
				cal = read(cal);
				sessionObject.setInput(dateAsString(cal));
			}
			
			choice = setChoice(choice);
			cal = targetOperation.calcMethod(cal, choice);
			if(cal != null) {
				printDate(cal);
				sessionObject.setOutput(dateAsString(cal));
			}
		}
//		for session history 
//		history.add(sessionObject);
		
		addHistory(sessionObject);
		sessionObject.clearInput();
	}
	
//	for session history
//	private void showSessionHistory() {
//		history.forEach(System.out::println);
//	}
		
	private void addHistory(SessionObject sessionObject) {
		try (FileOutputStream fout = new FileOutputStream(file, true)) {
			fout.write((sessionObject.getOperation() + "#" + sessionObject.getInput() + "#"
					+ sessionObject.getOutput() + "\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private void showHistory() {
		try {
			Scanner sc = new Scanner(new FileReader(file));
			while(sc.hasNext()) {
				String [] str = sc.nextLine().split("#");
				System.out.println("Operation: " + str[0]
								 + "\nInput: " + str[1]
								 + "\nOutput: " + str[2] + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
}

	private static void printMenuOption() {
		menuOptions.forEach((key, value) -> {
		System.out.print(key);
		System.out.println(") " + value);
		});
	}
	
	private int setChoice(int choice) {
		return choice >= 6 ? -1 : 1; 
	}
}
