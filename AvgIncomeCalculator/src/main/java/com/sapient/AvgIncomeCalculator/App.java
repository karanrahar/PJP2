package com.sapient.AvgIncomeCalculator;

import java.nio.file.Path;
import java.util.List;

import com.sapient.AvgIncomeCalculator.io.AvgIncomeIO;
import com.sapient.AvgIncomeCalculator.io.AvgIncomeUtil;
import com.sapient.AvgIncomeCalculator.process.AvgIncomeProcess;

public class App {
	private static final String FILE = "sample_input.csv";

	public static void main( String[] args ) {
        AvgIncomeIO avgIncomeIO = new AvgIncomeUtil();
        List<AvgIncomeAttr> avgIncomeList = avgIncomeIO.read(Path.of(FILE).toFile());
        AvgIncomeProcess avgIncomeProcess = new AvgIncomeProcess();
        avgIncomeIO.write(avgIncomeProcess.process(avgIncomeList));
    }
}
