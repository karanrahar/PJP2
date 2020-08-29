package com.sapient.AvgIncomeCalculator;

public class AvgIncomeAttr {
	private String city, country;
	private char gender;
	private String currency;
	private double avgIncome;
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public double getAvgIncome() {
		return avgIncome;
	}
	
	public void setAvgIncome(double avgIncome) {
		this.avgIncome = avgIncome;
	}
}
