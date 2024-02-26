package com.zemoso.checkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

@SpringBootApplication
public class CheckrApplication {

	public static void main(String[] args) {

	    SessionFactory sessionFactory=	HibernateUtil.getSessionFactory();
		Session session= sessionFactory.openSession();
		SpringApplication.run(CheckrApplication.class, args);
	}

}
