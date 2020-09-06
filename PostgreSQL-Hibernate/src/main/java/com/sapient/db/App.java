package com.sapient.db;

import java.io.IOException;
import java.text.ParseException;

import com.sapient.db.bulkprocess.CalculatorBulkProcessRunner;
import com.sapient.db.bulkprocess.RetrieveData;
import com.sapient.db.console.CalculatorRunner;

public class App {
	
    public static void main( String[] args ) throws IOException, ParseException {
        System.out.println("Press 1 to perform operations from console"
        		+ "\nPress 2 to perform bulk operations from data file");
        int n = Read.sc.nextInt();
        if(n == 1) {
        	CalculatorRunner runner = new CalculatorRunner();
        	runner.run();
        } else {
        	CalculatorBulkProcessRunner bulkProcessRunner = new CalculatorBulkProcessRunner();
        	bulkProcessRunner.run();
        	System.out.println("Enter session number for retrieval");
        	int no = Read.sc.nextInt();
        	new RetrieveData().retrieveLastNSessions("RetrievedData.csv", no);
        }
    }
}
