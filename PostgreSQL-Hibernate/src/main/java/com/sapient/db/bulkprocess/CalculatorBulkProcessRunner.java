package com.sapient.db.bulkprocess;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.calculatorOperations.AddSubDates;
import com.sapient.db.calculatorOperations.AddSubDays;
import com.sapient.db.calculatorOperations.AddSubMonths;
import com.sapient.db.calculatorOperations.AddSubWeeks;
import com.sapient.db.calculatorOperations.AddSubYears;
import com.sapient.db.calculatorOperations.GetDateFromString;
import com.sapient.db.calculatorOperations.GetDay;
import com.sapient.db.calculatorOperations.GetWeek;

public class CalculatorBulkProcessRunner {
	private static final String FILE = "BulkOperationsFile.txt";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
	Session session;
	Scanner sc;

	public CalculatorBulkProcessRunner() {
		Configuration config = new Configuration().configure().addAnnotatedClass(CalculatorEntity.class);
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		SessionFactory sf = config.buildSessionFactory(registry);
		this.session = sf.openSession();
	}

	public int getSessionNumber() {
		int sessionNumber = 0;
		session.beginTransaction();
		Query query = session.createQuery("FROM CalculatorEntity");
		@SuppressWarnings("unchecked")
		List<CalculatorEntity> list = (List<CalculatorEntity>) query.list();
		if (list.size() == 0)
			sessionNumber = 1;
		else
			sessionNumber = Integer.parseInt(list.get(list.size() - 1).getSessionNo()) + 1;
		session.getTransaction().commit();
		return sessionNumber;
	}

	public void run() throws ParseException, FileNotFoundException {
		int sessionNumber = this.getSessionNumber();

		sc = new Scanner(new FileReader(FILE));
		while (sc.hasNext()) {
			this.session.beginTransaction();
			CalculatorEntity row = new CalculatorEntity();

			row.setStartTime(sdf.format(new Date()));
			row.setSessionNo(String.valueOf(sessionNumber));
			row.setId(String.valueOf(sessionNumber) + sdf.format(new Date()) + UUID.randomUUID().toString());

			String choice = sc.nextLine();
			boolean isNum = choice.chars().allMatch(Character::isDigit);
			if (!isNum) {
				row.setError("Invalid Input!");
				row.setEndTime(sdf.format(new Date()).toString());
				System.out.println(row.getError());
				this.session.getTransaction().commit();
				this.session.save(row);
				continue;
			}

			int n = Integer.parseInt(choice);
			calculate(row, n);

			row.setEndTime(sdf.format(new Date()));
			this.session.getTransaction().commit();
			this.session.save(row);

			if (n == 0) {
				sessionNumber++;
				if (!sc.hasNextLine()) {
					this.session.beginTransaction();
					this.session.getTransaction().commit();
				}
			}
		}
		this.session.close();
	}

	private String getInput() {
		String input = sc.nextLine();
		return input;
	}

	private Calendar stringToDate(String input, int dateNo, CalculatorEntity row) throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (dateNo == 1)
			row.setDate1(input);
		else
			row.setDate2(input);
		cal.setTime(new SimpleDateFormat("dd-M-yyyy").parse(input));
		return cal;
	}

	private void calculate(CalculatorEntity row, int n) throws ParseException {
		String ans = "";
		if (n == 1) {
			Calendar cal1 = stringToDate(getInput(), 1, row);
			Calendar cal2 = stringToDate(getInput(), 2, row);
			char ch = getInput().charAt(0);
			if (ch == '+')
				ans = AddSubDates.addDates(cal1, cal2, row);
			else
				ans = AddSubDates.subDates(cal1, cal2, row);
		} else if (n == 2) {
			Calendar cal = stringToDate(getInput(), 1, row);
			int days = Integer.parseInt(getInput());
			row.setDays(String.valueOf(days));
			char ch = getInput().charAt(0);
			if (ch == '+')
				ans = AddSubDays.addDays(cal, days, row);
			else
				ans = AddSubDays.subDays(cal, days, row);
		} else if (n == 3) {
			Calendar cal = stringToDate(getInput(), 1, row);
			int weeks = Integer.parseInt(getInput());
			row.setWeeks(String.valueOf(weeks));
			char ch = getInput().charAt(0);
			if (ch == '+')
				ans = AddSubWeeks.addWeeks(cal, weeks, row);
			else
				ans = AddSubWeeks.subWeeks(cal, weeks, row);
		} else if (n == 4) {
			Calendar cal = stringToDate(getInput(), 1, row);
			int months = Integer.parseInt(getInput());
			row.setMonths(String.valueOf(months));
			char ch = getInput().charAt(0);
			if (ch == '+')
				ans = AddSubMonths.addMonths(cal, months, row);
			else
				ans = AddSubMonths.subMonths(cal, months, row);
		} else if (n == 5) {
			Calendar cal = stringToDate(getInput(), 1, row);
			int years = Integer.parseInt(getInput());
			row.setYear(String.valueOf(years));
			char ch = getInput().charAt(0);
			if (ch == '+')
				ans = AddSubYears.addYears(cal, years, row);
			else
				ans = AddSubYears.subYears(cal, years, row);
		} else if (n == 6) {
			Calendar cal = stringToDate(getInput(), 1, row);
			ans = GetDay.getDay(cal, row);
		} else if (n == 7) {
			Calendar cal = stringToDate(getInput(), 1, row);
			ans = GetWeek.getWeekNo(cal, row);
		} else if (n == 8) {
			String str = getInput();
			ans = GetDateFromString.convert(str, row);
		} else if (n == 0) {
			row.setOperation("Exit Session");
			ans = "Session Exited!";
		} else
			row.setError("Invalid Input!");

		if (n >= 0 && n <= 8)
			if (row.getError().equals("N.A"))
				row.setResult(ans);
	}
}
