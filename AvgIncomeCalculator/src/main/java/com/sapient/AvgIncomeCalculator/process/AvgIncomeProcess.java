package com.sapient.AvgIncomeCalculator.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.AvgIncomeCalculator.AvgIncomeAttr;
import com.sapient.AvgIncomeCalculator.Location;

public class AvgIncomeProcess {
	private static Map<String, Location> map;
	private static Map<String, Double> convertMap;
	
	public AvgIncomeProcess() {
		map = new HashMap<>();
		convertMap = new HashMap<>();
		convertMap.put("INR", 66.0);
		convertMap.put("GBP", 0.67);
		convertMap.put("SGP", 1.5);
		convertMap.put("HKD", 8.0);
		convertMap.put("USD", 1.0);
	}

	public List<Location> process(List<AvgIncomeAttr> avgincomeList) {
		for (AvgIncomeAttr avgIncomeAttr : avgincomeList) {
			String name = avgIncomeAttr.getCountry().isEmpty() ? 
					avgIncomeAttr.getCity() : avgIncomeAttr.getCountry();
			String key = name + "#" + avgIncomeAttr.getGender();
			
			Location location = map.getOrDefault(key, new Location());		
			location.setGenderCount();
			location.setAvgIncome(convert(avgIncomeAttr));
			
			if(!map.containsKey(key)) {
				location.setName(name);
				location.setGender(avgIncomeAttr.getGender());
				map.put(key, location);
			} 
		}
		return new ArrayList<Location>(map.values());
	}

	private double convert(AvgIncomeAttr avgIncomeAttr) {
		double avgIncome = avgIncomeAttr.getAvgIncome(); 
		avgIncome /= convertMap.get(avgIncomeAttr.getCurrency());
		return avgIncome;
	}
}
