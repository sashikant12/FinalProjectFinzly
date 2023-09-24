package com.BBC.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BBC.Entiity.Customer;
import com.BBC.Entiity.CustomerBill;

@Repository
public class CustomerDao {
@Autowired
SessionFactory sessionFactory;

public List<Customer> checkUserId(long id) {
	Session session = sessionFactory.openSession();
	Criteria criteria = session.createCriteria(Customer.class);
	return criteria.list();
}
}
