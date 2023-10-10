package com.BBC.Controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BBC.Entiity.CustomerBill;
import com.BBC.Services.CustomerBillServices;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerBillConroller {
	@Autowired
	CustomerBillServices customerBillServices;
//  checking the api connection
	@GetMapping("tranctions")
	public List<CustomerBill> getAlltranction() {
		return customerBillServices.getAlltranction();
	}

//	@GetMapping("getAlltranctionByIdForPending/{id}")
	@GetMapping("/pending/{id}")
	public List<CustomerBill> getAlltranctionByIdForPending(@PathVariable long id) {
		
		return customerBillServices.getAlltranctionByIdForPending(id);
	}

//	@GetMapping("setAlltranctionByIdAndPaymentId/{id}/{tId}/{pId}")
	@GetMapping("set/{id}/{tId}/{pId}")
	public String setAlltranctionByIdAndPaymentId(@PathVariable long id, @PathVariable long tId,
			@PathVariable long pId) {
		return customerBillServices.setAlltranctionByIdAndPaymentId(id, tId, pId);
	}

//	@GetMapping("getAlltranctionBillInvoice/{id}/{tId}")
	@GetMapping("invoices/{id}/{tId}")
	public List<CustomerBill> getAlltranctionBillInvoice(@PathVariable long id, @PathVariable long tId) {
		return customerBillServices.getAlltranctionBillInvoice(id, tId);
	}
	
//	@GetMapping("getAlltranctionPaymentStatusUpdate/{id}/{tId}")
	@GetMapping("update/{id}/{tId}")
	public String getAlltranctionPaymentUpdate(@PathVariable long id, @PathVariable long tId) {
		return customerBillServices.getAlltranctionPaymentUpdate(id, tId);
	}
	
//	@GetMapping("getAlltranctionByIdForSucess/{id}")
	@GetMapping("success/{id}")
	public List<CustomerBill> getAlltranctionByIdForSucess(@PathVariable long id) {
		return customerBillServices.getAlltranctionByIdForSucess(id);
	}
//	successfull
	@GetMapping("successfull/{id}/{tId}")
	public List<CustomerBill> getAllSuccessBill(@PathVariable long id, @PathVariable long tId) {
		return customerBillServices.getAllSuccessBill(id, tId);
	}
	@GetMapping("gogo")
	public String me() {
		return "its working";
	}
}
