package com.sapient.week2.DateTimeCalculator;

import java.util.ArrayList;

/**
 * Class to store the current operation, input and output in calculator. 
 */
public class SessionObject {
	private String operation;
	private ArrayList<String> input = new ArrayList<>();;
	private String output; 
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public ArrayList<String> getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input.add(input);
	}
	
	public String getOutput() {
		return output;
	}
	
	public void setOutput(String output) {
		this.output = output;
	}
	
	public void clearInput() {
		this.input.clear();
	}
	
	@Override
	public String toString() {
		return "Operation: " + this.getOperation()
			+ "\nInput: " + this.getInput()
		    + "\nOutput: " + this.getOutput() + "\n";
	}
}
