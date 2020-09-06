package com.sapient.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.sapient.calculator.operations.AddSubDates;
import com.sapient.calculator.operations.AddSubDays;
import com.sapient.calculator.operations.AddSubMonths;
import com.sapient.calculator.operations.AddSubWeeks;
import com.sapient.calculator.operations.AddSubYears;
import com.sapient.calculator.operations.GetDateFromString;
import com.sapient.calculator.operations.GetDay;
import com.sapient.calculator.operations.GetWeek;

public class FactoryClass {
	static Map<Integer, DateTimeCalculator> operationMap = new HashMap<>();
    static {
        operationMap.put(1, new AddSubDates());
        operationMap.put(2, new AddSubDays());
        operationMap.put(3, new AddSubWeeks());
        operationMap.put(4, new AddSubMonths());
        operationMap.put(5, new AddSubYears());
        operationMap.put(6, new GetDay());
        operationMap.put(7, new GetWeek());
        operationMap.put(8, new GetDateFromString());
    }
 
    public static Optional<DateTimeCalculator> getOperation(int choice) {
        return Optional.ofNullable(operationMap.get(choice));
        
    }
}
