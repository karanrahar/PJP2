package com.sapient.db.console;

import java.util.HashMap;
import java.util.Map;

import com.sapient.db.calculatorOperations.AddSubDates;
import com.sapient.db.calculatorOperations.AddSubDays;
import com.sapient.db.calculatorOperations.AddSubMonths;
import com.sapient.db.calculatorOperations.AddSubWeeks;
import com.sapient.db.calculatorOperations.AddSubYears;
import com.sapient.db.calculatorOperations.Exit;
import com.sapient.db.calculatorOperations.GetDateFromString;
import com.sapient.db.calculatorOperations.GetDay;
import com.sapient.db.calculatorOperations.GetWeek;
import com.sapient.db.calculatorOperations.ShowHistory;
import com.sapient.db.calculatorOperations.ShowSessionHistory;

/**
 * Factory Class to create a static map which contains options for the user and their
 * corresponding actions and a function which returns the correct Class to be used
 * for each operation.  
 */
public class FactoryClass {
	static Map<Integer, Calculator> operationMap = new HashMap<>();
    static {
    	operationMap.put(0, new Exit());
        operationMap.put(1, new AddSubDates());
        operationMap.put(2, new AddSubDays());
        operationMap.put(3, new AddSubWeeks());
        operationMap.put(4, new AddSubMonths());
        operationMap.put(5, new AddSubYears());
        operationMap.put(6, new GetDay());
        operationMap.put(7, new GetWeek());
        operationMap.put(8, new GetDateFromString());
        operationMap.put(9, new ShowHistory());
        operationMap.put(10, new ShowSessionHistory());
    }
 
    public static Calculator getOperation(int choice) {
        return operationMap.get(choice);
        
    }
}
