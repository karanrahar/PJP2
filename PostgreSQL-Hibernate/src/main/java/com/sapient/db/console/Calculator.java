package com.sapient.db.console;

import com.sapient.db.CalculatorEntity;

/**
 * Date Time Calculator interface with one method to be implemented by all calculator functions.
 */
public interface Calculator { 
    
	public String operation(CalculatorEntity row); 	
}
