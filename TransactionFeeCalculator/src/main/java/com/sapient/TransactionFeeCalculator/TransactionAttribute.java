package com.sapient.TransactionFeeCalculator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionAttribute {
	private String transactionId;
	private String clientId;
	private String securityId;
	private String transactionType;
	private Date transactionDate;
	private double marketValue;
	private String priorityFlag;
	private int fee;
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getSecurityId() {
		return securityId;
	}
	
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public double getMarketValue() {
		return marketValue;
	}
	
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	
	public String getPriorityFlag() {
		return priorityFlag;
	}
	
	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	
	public int getFee() {
		return fee;
	}
	
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	public String getKey() {
		return this.clientId 
				+ "|" + this.securityId
				+ "|" + getDateAsString();
	}
	
	public String getDateAsString() {
		return new SimpleDateFormat("M/dd/yyyy").format(transactionDate);
	}
}
