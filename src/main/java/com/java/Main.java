package com.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.java.dto.Student;

public class Main {
	
	static SessionFactory sf;
	static {
			Configuration cfg=new Configuration().addPackage("com.java.dto");
		//create the schema
			cfg.setProperty(Environment.HBM2DDL_AUTO, "create");
			cfg.addAnnotatedClass(Student.class);
			//To which db, it should generate sql queries
			cfg.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			cfg.setProperty("hibernate.connection.username", "root");
			cfg.setProperty("hibernate.connection.password", "rootroot");
			cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			cfg.setProperty("hibernate.connection.url", "jdbc:mysql://mydb.cfohefztpr9s.us-east-1.rds.amazonaws.com:3306/mydb");
			cfg.setProperty(Environment.SHOW_SQL, "true");
			StandardServiceRegistryBuilder rb= new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
			 sf=cfg.buildSessionFactory(rb.build());
			
	}

	public void insertStudent() {
		
		Session session= sf.openSession();
		Transaction tx=session.beginTransaction();
		Student student= new Student(1, "Payal", "10R, delhi");
		session.save(student);
		//update, delete,get
		tx.commit();
		session.close();
		
	}
	
public void updateStudent() {
		
		Session session= sf.openSession();
		Transaction tx=session.beginTransaction();
		Student student= new Student(1, "Payal", "10R, delhi road, Opp. ramlila ground");
		session.update(student);
		//If data already exist, save will throw an error
		tx.commit();
		session.close();
		
	}
public void deleteStudent() {
	
	Session session= sf.openSession();
	Transaction tx=session.beginTransaction();
	Student student= new Student(1, "Payal", "10R, delhi road, Opp. ramlila ground");
	session.delete(student);
	//If data already exist, save will throw an error
	tx.commit();
	session.close();
	
}

public void getStudentById(int id) {
	
	Session session= sf.openSession();
	Transaction tx=session.beginTransaction();
	Student st=session.get(Student.class, id);
	//To get one row from db corresponding to primary key
	System.out.println(st);
	tx.commit();
	session.close();
	
}

public void getStudents() {
	
/*	Session session= sf.openSession();
	Transaction tx=session.beginTransaction();
	Student st=session.get(Student.class);
	//To get one row from db corresponding to primary key
	System.out.println(st);
	tx.commit();
	session.close();*/
	
}


public static void main(String args[]) {
	Main obj= new Main();
	obj.insertStudent();
	obj.updateStudent();
	obj.getStudentById(1);
	obj.deleteStudent();
}
}
