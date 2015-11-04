package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.example.model.Department;
import org.example.model.Student;
import org.example.model.StudentDetail;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class Main {

	public static void main(String[] args) throws ParseException {

		Student student = new Student();
		Student student2 = new Student();

		StudentDetail studentDetail = new StudentDetail();
		StudentDetail studentDetail2 = new StudentDetail();

		Department department = new Department();

		// set student
		student.setName("student 1");
		student.setCreatedDate(new Date());
		student.setBirthDate(new SimpleDateFormat("dd-MM-yyyy")
				.parse("01-01-1988"));

		student2.setName("student 2");
		student2.setCreatedDate(new Date());
		student2.setBirthDate(new SimpleDateFormat("dd-MM-yyyy")
				.parse("02-02-1988"));

		// set student detail
		studentDetail.setMobileNumber("11111111");
		studentDetail.setStudent(student);

		studentDetail2.setMobileNumber("22222222");
		studentDetail2.setStudent(student2);

		// set department
		department.setCode("EE");
		department.setName("Electronic Enginnering");

		// set relationship
		student.setStudentDetail(studentDetail);
		student2.setStudentDetail(studentDetail2);

		student.setDepartment(department);
		student2.setDepartment(department);

		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(student);
		session.save(student2);

		/*
		 * Test 1: Remove one of the Many side with CascadeType.ALL to see
		 * whether it can succeed ( Many students link to one department)
		 * 
		 * Result: Exception in thread "main"
		 * org.hibernate.ObjectDeletedException: deleted object would be
		 * re-saved by cascade (remove deleted object from associations):
		 * [org.example.model.Department#EE]
		 */
		// session.save(student);
		// session.save(student2);
		// session.delete(student);

		/*
		 * Test 2: Remove one of the Many side with CascadeType.PERSIST to see
		 * whether it can succeed ( Many students link to one department)
		 * 
		 * Result: student can be deleted successfully and the department is
		 * remained unchanged as the cascade type is persist only. No removal
		 * can be done.
		 */

		// session.persist(student);
		// session.persist(student2);
		// session.delete(student);

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

	}

}
