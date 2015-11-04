package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.example.model.Student;
import org.example.model.StudentDetail;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class Main {

	public static void main(String[] args) throws ParseException {

		Student student = new Student();

		StudentDetail detail = new StudentDetail();

		student.setName("student 1");
		student.setCreatedDate(new Date());
		student.setBirthDate(new SimpleDateFormat("dd-MM-yyyy")
				.parse("01-01-1988"));

		detail.setMobileNumber("23232323");
		detail.setStudent(student);

		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(detail);

		/*
		 * Test 1: if the reference is set to null , the cascade setting will do
		 * nothing.
		 */
		// detail.setStudent(null);
		// session.delete(detail);

		/*
		 * Test 2: The Cascade setting for the reference is ALL or REMOVE, the
		 * reference will be deleted simultaneously.
		 */
		// session.delete(detail);

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

	}

}
