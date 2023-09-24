package com.BBC.Entiity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PaymentModes {
	@Id
	private long paymentModeId;
	private String paymentModeName;
	private final double discountPercentage=5;
	private final double rate = 41.5;
	private final double dateDiscount=5;


	public long getPaymentModeId() {
		return paymentModeId;
	}


	public void setPaymentModeId(long paymentModeId) {
		this.paymentModeId = paymentModeId;
	}


	public String getPaymentModeName() {
		return paymentModeName;
	}


	public void setPaymentModeName(String paymentModeName) {
		this.paymentModeName = paymentModeName;
	}


	public double getDiscountPercentage() {
		return discountPercentage;
	}


//	public void setDiscountPercentage(double discountPercentage) {
//		this.discountPercentage = discountPercentage;
//	}


	public double getRate() {
		return rate;
	}


	public double getDateDiscount() {
		return dateDiscount;
	}
	
	
}
