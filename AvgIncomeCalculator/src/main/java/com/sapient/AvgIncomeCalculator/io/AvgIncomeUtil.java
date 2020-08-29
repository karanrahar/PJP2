package com.sapient.AvgIncomeCalculator.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.sapient.AvgIncomeCalculator.AvgIncomeAttr;
import com.sapient.AvgIncomeCalculator.Location;

public class AvgIncomeUtil implements AvgIncomeIO {

	private static final String PATH = "output.csv";

	@Override
	public List<AvgIncomeAttr> read(File file) {
		List<AvgIncomeAttr> avgIncomeList = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String data = "";
			int count = 0;
			while((data = br.readLine()) != null) {
				count++;
				if(count == 1)
					continue;
				
				String [] row = data.split(",");
				AvgIncomeAttr avgIncomeAttr = new AvgIncomeAttr();
				avgIncomeAttr.setCity(row[0]);
				avgIncomeAttr.setCountry(row[1]);
				avgIncomeAttr.setGender(row[2].charAt(0));
				avgIncomeAttr.setCurrency(row[3]);
				avgIncomeAttr.setAvgIncome(Double.parseDouble(row[4]));
				avgIncomeList.add(avgIncomeAttr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return avgIncomeList;
	}

	@Override
	public File write(List<Location> avgIncomeList) {
		DecimalFormat df = new DecimalFormat("#.00"); 
		File file = new File(PATH);
		
		try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {
			avgIncomeList.sort((a,b) -> { 
				if(!a.getName().equals(b.getName()))
					return a.getName().compareTo(b.getName());
				else
					return Character.compare(a.getGender(), b.getGender());
			});
			
			csvWriter.writeNext(new String [] {"Country/City", "Gender", "Average Income(in USD)"});
			
			for (Location location : avgIncomeList) {
				String [] row = new String[3];
				row[0] = location.getName();
				row[1] = Character.toString(location.getGender());
				row[2] = df.format(location.getAvgIncome() / location.getGenderCount());
				csvWriter.writeNext(row);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}
