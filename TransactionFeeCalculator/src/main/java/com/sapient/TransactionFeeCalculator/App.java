package com.sapient.TransactionFeeCalculator;

import java.nio.file.Path;
import java.util.List;

public class App {

	private static final String FILE = "transaction.csv";

	public static void main(String[] args) {
		TransactionIO transactionIO = new TransactionRunner();
		List<TransactionAttribute> list = transactionIO.read(Path.of(FILE).toFile());
		TransactionProcessor transactionProcessor = new TransactionProcessor();
		transactionProcessor.process(list);
		transactionIO.write(list);
	}

}                                                                                                  
