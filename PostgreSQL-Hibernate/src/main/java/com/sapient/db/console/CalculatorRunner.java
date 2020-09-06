package com.sapient.db.console;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.sapient.db.CalculatorEntity;
import com.sapient.db.Read;

/**
 * Class to perform the calculator operation selected by the user and store the
 * history of operations in a file.
 */
public class CalculatorRunner extends CalculatorIO {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
	private static Map<Integer, String> menuOptions;
	
	public static final String FILE = "DateTimeCalculator_History.txt";
	public static List<String> history = new ArrayList<>();
	public static List<String> currentOperation = new ArrayList<>();
	Session session;

	public CalculatorRunner() {
		menuOptions = new HashMap<>();
		
		menuOptions.put(0, "Exit");
		menuOptions.put(1, "Add/Subtract Dates");
		menuOptions.put(2, "Add/Subtract Days");
		menuOptions.put(3, "Add/Subtract Weeks");
		menuOptions.put(4, "Add/Subtract Months");
		menuOptions.put(5, "Add/Subtract Years");
		menuOptions.put(6, "Get day of the week");
		menuOptions.put(7, "Get week number");
		menuOptions.put(8, "Get date from string");
		menuOptions.put(9, "See history");
		menuOptions.put(10, "See session history");
		
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
		List<CalculatorEntity> list = (List<CalculatorEntity>)query.list();
		if (list.size() == 0)
			sessionNumber = 1;
		else
			sessionNumber = Integer.parseInt(list.get(list.size() - 1).getSessionNo()) + 1;
		session.getTransaction().commit();
		return sessionNumber;
	}

	public void run() throws IOException {
		int sessionNumber = this.getSessionNumber();
		while (true) {
			printMenuOption();
			this.session.beginTransaction();
			CalculatorEntity row = new CalculatorEntity();
			currentOperation.clear();
			
			row.setStartTime(sdf.format(new Date()));
			row.setSessionNo(String.valueOf(sessionNumber));
			row.setId(String.valueOf(sessionNumber) + sdf.format(new Date()) + UUID.randomUUID().toString());
			
			int choice = Read.sc.nextInt();
			
			if (choice < 0 || choice > 10) {
				row.setError("Invalid Input!");
				row.setEndTime(sdf.format(new Date()).toString());
				System.out.println(row.getError());
				this.session.getTransaction().commit();
				this.session.save(row);
				continue;
			}
			currentOperation.add(menuOptions.get(choice));
			String ans = FactoryClass.getOperation(choice).operation(row);
			
			if(ans != null) {
				currentOperation.add("Output : " + ans);
				currentOperation.add(" ");
				if(row.getError().equals("N.A")) {
					row.setResult(ans);
					System.out.println(ans);
				}
			}
			
			updateHistory();
			
			row.setEndTime(sdf.format(new Date()));
			this.session.getTransaction().commit();
			
			if (choice != 9 && choice != 10) {
				
				this.session.save(row);
			}
			if (choice == 0) {
				this.session.beginTransaction();
				this.session.getTransaction().commit();
				break;
			}
		}
		this.session.close();
	}

	private void updateHistory() {
		currentOperation.forEach((str) -> history.add(str));
	}

	private static void printMenuOption() {
		System.out.println("\n");
		menuOptions.forEach((key, value) -> {
			System.out.print(key);
			System.out.println(") " + value);
		});
	}
}
