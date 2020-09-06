package com.sapient.week2.DateTimeCalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Factory Class to create a static map which contains options for the user and their
 * corresponding actions and a function which returns the correct Class to be used
 * for each operation.  
 */
public class FactoryClass {
	static Map<Integer, Calculator> operationMap = new HashMap<>();
    static {
        operationMap.put(1, new AddSubDates());
        operationMap.put(2, new AddSubDays());
        operationMap.put(3, new AddSubWeeks());
        operationMap.put(4, new AddSubMonths());
        operationMap.put(5, new AddSubYears());
        operationMap.put(6, new AddSubDates());
        operationMap.put(7, new AddSubDays());
        operationMap.put(8, new AddSubWeeks());
        operationMap.put(9, new AddSubMonths());
        operationMap.put(10, new AddSubYears());
        operationMap.put(11, new GetDay());
        operationMap.put(12, new GetWeek());
        operationMap.put(13, new GetDateFromString());
    }
 
    public static Optional<Calculator> getOperation(int choice) {
        return Optional.ofNullable(operationMap.get(choice));
        
    }
}
