package com.BBC.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BBC.Dao.CustomerDao;
import com.BBC.Entiity.Customer;

@Service
public class CustomerServices {
@Autowired
CustomerDao customerDao;

	public List<Customer> checkUserId(long id) {
		List<Customer> customers  =  customerDao.checkUserId(id);
		List<Customer>al  = new ArrayList<>();
		for(Customer cosom : customers) {
			if(cosom.getCustomerId()==id) {
			 al.add(cosom);
			 return al; 
			}
		}
		return null;
	}

}

