package com.BBC.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BBC.Entiity.Customer;
import com.BBC.Services.CustomerServices;
@CrossOrigin
@RestController
public class CustomerController {
@Autowired
CustomerServices customerServices;

@GetMapping("Validation/{id}")
public List<Customer> checkUserId(@PathVariable long id) {
	return customerServices.checkUserId(id);
}
}
