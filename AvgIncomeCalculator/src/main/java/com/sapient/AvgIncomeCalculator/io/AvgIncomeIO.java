package com.sapient.AvgIncomeCalculator.io;

import java.io.File;
import java.util.List;

import com.sapient.AvgIncomeCalculator.AvgIncomeAttr;
import com.sapient.AvgIncomeCalculator.Location;

public interface AvgIncomeIO {
	public List<AvgIncomeAttr> read(File file);
	
	public File write(List<Location> avgIncomeList);
}
