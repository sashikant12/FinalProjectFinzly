package com.BBC.Entiity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CustomerBill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long billId;	
	private double totalAmount;
	private double amount;
	private String status;
	private double consumedUnit;
	private int month;
	private Date startDate;
	private Date endDate;
	private Date dueDate;
	@OneToOne
	private Customer customer;
	@OneToOne
	private PaymentModes paymentMode;
	public long getBillId() {
		return billId;
	}
	public void setBillId(long billId) {
		this.billId = billId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getAmount() {
		return amount=paymentMode.getRate()*getConsumedUnit();
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getConsumedUnit() {
		return consumedUnit;
	}
	public void setConsumedUnit(double consumedUnit) {
		this.consumedUnit = consumedUnit;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public PaymentModes getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(PaymentModes paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Date getDueDate() {
		return endDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}