package com.sapient.TransactionFeeCalculator;

import java.io.File;
import java.util.List;

public interface TransactionIO {
	public List<TransactionAttribute> read(File file);
	
	public File write(List<TransactionAttribute> transactions);
}
