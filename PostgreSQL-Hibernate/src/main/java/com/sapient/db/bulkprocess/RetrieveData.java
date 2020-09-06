package com.sapient.db.bulkprocess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.sapient.db.CalculatorEntity;

public class RetrieveData {
	Session session;

	public RetrieveData() {
		Configuration config = new Configuration().configure().addAnnotatedClass(CalculatorEntity.class);
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		SessionFactory sf = config.buildSessionFactory(registry);
		this.session = sf.openSession();
	}

	public void retrieveLastNSessions(String file, int sessions) throws IOException {
		session.beginTransaction();
		Query query = session.createQuery("FROM CalculatorEntity");
		@SuppressWarnings("unchecked")
		List<CalculatorEntity> list = (List<CalculatorEntity>) query.list();

		int actualSessions;
		if (list.size() == 0)
			actualSessions = 0;
		else
			actualSessions = Integer.parseInt(list.get(list.size() - 1).getSessionNo());

		if (actualSessions < sessions)
			System.out.println("Session number entered is greater than total number of sessions in history");
		else {
			int index = list.size() - 1;
			while (index >= 0 && Integer.parseInt(list.get(index).getSessionNo()) >= actualSessions - sessions + 1)
				index--;
			index++;
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			String str = "Session Number, Operation, Start Timestamp, End Timestamp, Date 1, Date 2, Days, Weeks,"
					+ " Months, Years, Phrase, Result of Operation, Error\n";
			out.write(str);
			for (int i = index; i < list.size(); i++) 
				out.write(list.get(i).toString());
			out.close();
		}
		session.getTransaction().commit();
		session.close();
	}
}
