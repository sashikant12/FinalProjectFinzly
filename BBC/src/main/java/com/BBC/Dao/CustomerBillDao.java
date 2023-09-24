package com.BBC.Dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BBC.Entiity.CustomerBill;

import antlr.collections.List;
@Repository
public class CustomerBillDao {
	@Autowired
	SessionFactory sessionFactory;

	public java.util.List getAlltranction() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CustomerBill.class);
		return criteria.list();
		
	}

	public java.util.List<CustomerBill> getAllTransaction() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CustomerBill.class);
		return criteria.list();
	}
	
	public String setAllTransactionUpdate(CustomerBill tranction) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CustomerBill.class);
		session.update(tranction);
		session.beginTransaction().commit();
		return "Transaction Done";
	}

	public String getAlltranctionPaymentUpdate(CustomerBill tranction) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CustomerBill.class);
		session.update(tranction);
		session.beginTransaction().commit();
		return "Payment done sucessfully";
	}
}
