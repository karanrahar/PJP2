package com.sapient.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DateTimeCalculatorController {
	
	public static final String OPERATION = "operation";
	
	@RequestMapping(value = "/menuOptions", method = RequestMethod.GET)
	public String getMenuOptions() {
		return "menuOptions";
	}
	
	@RequestMapping(value = "/calculate", method = RequestMethod.GET)
	public String handleUserChoice(@RequestParam String operation, ModelMap model) {
		model.addAttribute("calculatorDetails", new CalculatorDetails());
		return OPERATION + operation;
	}
	
	@RequestMapping(value = "/calculate", method = RequestMethod.POST)
	public String calculate(ModelMap model, CalculatorDetails calculatorDetails, @RequestParam String choice) {
		DateTimeCalculator calculator = FactoryClass.getOperation(Integer.parseInt(choice))
								 .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		String ans = calculator.operation(calculatorDetails);
		model.addAttribute("ans", ans);
		return "result";
	}
}
