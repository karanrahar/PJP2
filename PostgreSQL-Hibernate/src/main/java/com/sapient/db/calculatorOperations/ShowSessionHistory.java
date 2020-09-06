package com.sapient.db.calculatorOperations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorRunner;

public class ShowSessionHistory implements Calculator {

	@Override
	public String operation(CalculatorEntity row) {
		//Previous sessions
		try {
			Scanner sc = new Scanner(new FileReader(CalculatorRunner.FILE));
			while(sc.hasNext()) {
				System.out.println(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Current session
		System.out.println("\n" + "-".repeat(7) + "Session Started" + "-".repeat(7) + "\n");
		if(CalculatorRunner.history.isEmpty())
			System.out.println("No operations in current session");
		else
			CalculatorRunner.history.forEach(System.out::println);
		
		return null;
	}
}
