package com.sapient.TransactionFeeCalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class TransactionRunner implements TransactionIO {
	private static String DATE_FORMAT = "M/dd/yyyy";
	private static String FILE = "transaction_report.csv";
	
	@Override
	public List<TransactionAttribute> read(File file) {
		List<TransactionAttribute> transactionList = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String data;
			int count = 0;
			while((data = br.readLine()) != null) {
				count++;
				if(count == 1)
					continue;
				
				TransactionAttribute transactionAttribute = new TransactionAttribute();
				String [] row = data.split(","); 
				transactionAttribute.setTransactionId(row[0]);
				transactionAttribute.setClientId(row[1]);
				transactionAttribute.setSecurityId(row[2]);
				transactionAttribute.setTransactionType(row[3]);
				transactionAttribute.setTransactionDate(new SimpleDateFormat(DATE_FORMAT)
						.parse(row[4]));
				transactionAttribute.setMarketValue(Double.parseDouble(row[5]));
				transactionAttribute.setPriorityFlag(row[6]);
				transactionList.add(transactionAttribute);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return transactionList;
	}

	@Override
	public File write(List<TransactionAttribute> transactionList) {
		File file = new File(FILE);
		try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {
			transactionList.sort((a,b) -> {
				if(!a.getClientId().equals(b.getClientId()))
					return a.getClientId().compareTo(b.getClientId());
				else if(!a.getTransactionType().equals(b.getTransactionType()))
					return a.getTransactionType().compareTo(b.getTransactionType());
				else if(!a.getTransactionDate().equals(b.getTransactionDate()))
					return a.getTransactionDate().compareTo(b.getTransactionDate());
				else 
					return a.getPriorityFlag().compareTo(b.getPriorityFlag()); 
				});
			String [] list = {"Client ID", "TransactionType", "Transaction Date",
					"Priority Flag", "Fee"};
			csvWriter.writeNext(list);
			for (TransactionAttribute t : transactionList) {
				String [] row = new String[5];
				row[0] = t.getClientId(); 
				row[1] = t.getTransactionType(); 
				row[2] = t.getDateAsString(); 
				row[3] = t.getPriorityFlag();
				row[4] = Integer.toString(t.getFee());
				csvWriter.writeNext(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return file;
	}	
}