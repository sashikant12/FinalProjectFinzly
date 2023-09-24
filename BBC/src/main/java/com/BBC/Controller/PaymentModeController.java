package com.BBC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RestController;

import com.BBC.Services.PaymentModeServices;
@CrossOrigin
@RestController
public class PaymentModeController {

	@Autowired
	PaymentModeServices paymentModeServices ;
	
	
}
