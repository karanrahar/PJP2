package com.sapient.TransactionFeeCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionProcessor extends TransactionAttribute {
	private static final int INTRADAY_FEE  = 10;
	private static final int _50 = 50;
	private static final int _100 = 100;
	private static final int _500 = 500;
	private static final String WITHDRAW = "WITHDRAW";
	private static final String DEPOSIT = "DEPOSIT";
	private static final String BUY = "BUY";
	private static final String SELL = "SELL";
	private static final String Y = "Y";
	
	private final Map<String, List<TransactionAttribute>> buyMap;
	private final Map<String, List<TransactionAttribute>> sellMap;
	
	public TransactionProcessor() {
		this.buyMap = new HashMap<>();
		this.sellMap = new HashMap<>();
	}
	
	public void process(List<TransactionAttribute> transactionList) {
		for (TransactionAttribute t : transactionList) {
			if(t.getTransactionType().equals(WITHDRAW) 
					|| t.getTransactionType().equals(DEPOSIT)) 
				setNominalValue(t);
			else if(t.getTransactionType().equals(BUY)) {
				if(check(t, sellMap)) 
					continue;
				List<TransactionAttribute> temp = 
						buyMap.getOrDefault(t.getKey(), new ArrayList<>());
				temp.add(t);
				if(!buyMap.containsKey(t.getKey()))
					buyMap.put(t.getKey(), temp);
			} else {
				if(check(t, buyMap)) 
					continue;
				List<TransactionAttribute> temp = 
						sellMap.getOrDefault(t.getKey(), new ArrayList<>());
				temp.add(t);
				if(!sellMap.containsKey(t.getKey()))
					sellMap.put(t.getKey(), temp);
			}
		}
		processRest(buyMap);
		processRest(sellMap);
	}
	
	private void processRest(Map<String, List<TransactionAttribute>> map) {
		for (String key : map.keySet()) {
			map.get(key).forEach(this::setNominalValue);
		}
	}

	private boolean check(TransactionAttribute t, Map<String, List<TransactionAttribute>> map) {
		if(map.containsKey(t.getKey())) {
			TransactionAttribute temp = map.get(t.getKey()).remove(0);
			if(map.get(t.getKey()).isEmpty())
				map.remove(t.getKey());
			temp.setFee(INTRADAY_FEE);
			t.setFee(INTRADAY_FEE);
			setNominalValue(temp);
			setNominalValue(t);
		}
		return false;
	}

	private void setNominalValue(TransactionAttribute t) {
		if(t.getPriorityFlag().equals(Y))
			t.setFee(_500 + t.getFee());
		else 
			if(t.getTransactionType().equals(SELL) || t.getTransactionType().equals(WITHDRAW))
				t.setFee(_100 + t.getFee());
			else
				t.setFee(_50 + t.getFee());
	}
}
