package com.sapient.AvgIncomeCalculator;

public class Location {
	private String name;
	private int genderCount;
	private char gender;
	private double avgIncome;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGenderCount() {
		return genderCount;
	}
	
	public void setGenderCount() {
		this.genderCount++;
	}
	
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public double getAvgIncome() {
		return avgIncome;
	}
	
	public void setAvgIncome(double avgIncome) {
		this.avgIncome += avgIncome;
	}
}
