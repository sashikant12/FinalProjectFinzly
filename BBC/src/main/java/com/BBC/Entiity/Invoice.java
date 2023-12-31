package com.BBC.Entiity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.util.Date;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int invoiceId;
	private Date invoiceDate;
	@OneToOne
	private CustomerBill customerBillId;
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public CustomerBill getCustomerBillId() {
		return customerBillId;
	}
	public void setCustomerBillId(CustomerBill customerBillId) {
		this.customerBillId = customerBillId;
	}
	
}
