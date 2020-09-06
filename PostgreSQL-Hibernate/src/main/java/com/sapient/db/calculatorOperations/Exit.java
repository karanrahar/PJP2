package com.sapient.db.calculatorOperations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.console.Calculator;
import com.sapient.db.console.CalculatorRunner;

public class Exit implements Calculator {
	
	@Override
	public String operation(CalculatorEntity row) {
		row.setOperation("Exit Session");
		try (BufferedWriter  writer = new BufferedWriter(new FileWriter(CalculatorRunner.FILE, true))) {
			writer.write("-".repeat(7) + "Session Started" + "-".repeat(7) + "\n");
			CalculatorRunner.history.stream().forEach((str) -> {
				try {
					writer.write(str + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			writer.write("-".repeat(7) + "Session Ended" + "-".repeat(7) + "\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "Session Exited!";
	}

}
