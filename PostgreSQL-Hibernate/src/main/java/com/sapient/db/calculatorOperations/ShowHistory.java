package com.sapient.db.calculatorOperations;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorRunner;

public class ShowHistory implements Calculator {

	@Override
	public String operation(CalculatorEntity row) {
		if(CalculatorRunner.history.isEmpty())
			System.out.println("No history");
		else 
			CalculatorRunner.history.forEach(System.out::println);
		
		return null;
	}
}
