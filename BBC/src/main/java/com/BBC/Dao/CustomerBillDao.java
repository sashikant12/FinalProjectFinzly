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

	/**
	 * Retrieves a list of all customer transactions.
	 *
	 * This method fetches and returns a list of all customer transactions from the database.
	 *
	 * @return A list of CustomerBill objects representing all customer transactions.
	 */

	public java.util.List<CustomerBill> getAllTransaction() {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CustomerBill.class);
			return criteria.list();
		}
	}
	
	
	/**
	 * Update a customer transaction and commit the changes to the database.
	 *
	 * This method updates a customer transaction provided as a parameter and commits the changes to the database.
	 *
	 * @param transaction The CustomerBill object representing the transaction to be updated.
	 * @return A status message indicating that the transaction mode has been updated.
	 */

	public String setPaymentMode(CustomerBill transaction) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CustomerBill.class);
			session.update(transaction);
			session.beginTransaction().commit();
			return "Transaction Mode selected";
		}
	}

	
	/**
	 * Update a customer transaction's payment status and commit the changes to the database.
	 *
	 * This method updates a customer transaction's payment status provided as a parameter and commits the changes to the database.
	 *
	 * @param transaction The CustomerBill object representing the transaction for which payment is updated.
	 * @return A status message indicating that the payment has been done successfully.
	 */
	public String getPaymentUpdate(CustomerBill transaction) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CustomerBill.class);
			session.update(transaction);
			session.beginTransaction().commit();
			return "Payment done sucessfully";
			
		}
	}
}
