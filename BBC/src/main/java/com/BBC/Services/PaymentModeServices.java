package com.BBC.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BBC.Dao.PaymentModeDao;

@Service
public class PaymentModeServices {
@Autowired
PaymentModeDao paymentModeDao;

}
