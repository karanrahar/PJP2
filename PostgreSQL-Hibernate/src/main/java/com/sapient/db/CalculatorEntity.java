package com.sapient.db;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CalculatorEntity {
	@Id
	private String id;
	private String sessionNo;
	private String operation;
	private String startTime, endTime;
	private String days, weeks, months, years;
	private String phrase;
	private String date1, date2;
	private String result;
	private String error;
	
	public CalculatorEntity() {
		this.id = "N.A";
		this.sessionNo = "N.A";
		this.operation = "N.A";
		this.startTime = "N.A";
		this.endTime = "N.A";
		this.days = "N.A";
		this.weeks = "N.A";
		this.months = "N.A";
		this.years = "N.A";
		this.phrase = "N.A";
		this.date1 = "N.A";
		this.date2 = "N.A";
		this.result = "N.A";
		this.error = "N.A";
	}
	
	@Override
	public String toString() {
		return this.sessionNo + "," + this.operation + "," + this.startTime + "," + this.endTime + "," + this.date1
				+ "," + this.date2 + "," + this.days + "," + this.weeks + "," + this.months + "," + this.years
				+ "," + this.phrase + "," + this.result + "," + this.error + "\n";
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSessionNo() {
		return sessionNo;
	}
	
	public void setSessionNo(String sessionNo) {
		this.sessionNo = sessionNo;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getDays() {
		return days;
	}
	
	public void setDays(String days) {
		this.days = days;
	}
	
	public String getWeeks() {
		return weeks;
	}
	
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	
	public String getMonths() {
		return months;
	}
	
	public void setMonths(String months) {
		this.months = months;
	}
	
	public String getYear() {
		return years;
	}
	
	public void setYear(String years) {
		this.years = years;
	}
	
	public String getPhrase() {
		return phrase;
	}
	
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	public String getDate1() {
		return date1;
	}
	
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	
	public String getDate2() {
		return date2;
	}
	
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
}