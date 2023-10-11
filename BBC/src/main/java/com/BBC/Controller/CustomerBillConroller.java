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

@CrossOrigin
@RestController
public class CustomerBillConroller {
	@Autowired
	CustomerBillServices customerBillServices;

	/**
	 * Retrieves a list of customer bills representing transactions.
	 *
	 * This endpoint is used to retrieve a list of customer bills, which represent
	 * transactions.
	 *
	 * @return A list of CustomerBill objects representing transactions.
	 */
	@GetMapping("/transactions")
	public List<CustomerBill> getAlltransaction() {
		return customerBillServices.getAlltransaction();
	}

	/**
	 * Retrieves a list of customer bills representing pending transactions by a
	 * specific ID.
	 *
	 * This endpoint is used to retrieve a list of customer bills that represent
	 * pending transactions for a specific customer ID.
	 *
	 * @param id The unique identifier of the customer.
	 * @return A list of CustomerBill objects representing pending transactions for
	 *         the specified customer ID.
	 */
	@GetMapping("/pending/{id}")
	public List<CustomerBill> getPendingBills(@PathVariable long id) {
		return customerBillServices.getPendingBills(id);
	}

	/**
	 * Set a transaction by its ID and a payment ID.
	 *
	 * This endpoint is used to associate a transaction with a payment by specifying
	 * the IDs of the transaction, payment, and customer.
	 *
	 * @param id  The ID of the customer.
	 * @param tId The ID of the transaction to set.
	 * @param pId The ID of the payment to associate with the transaction.
	 * @return A message indicating the success or result of the operation.
	 */
	@GetMapping("/sets/{id}/{tId}/{pId}")
	public String setPaymentMode(@PathVariable long id, @PathVariable long tId, @PathVariable long pId) {
		return customerBillServices.setPaymentMode(id, tId, pId);
	}

	/**
	 * Retrieve a list of customer bills related to a specific transaction and
	 * invoice.
	 *
	 * This endpoint allows you to retrieve a list of customer bills that are
	 * related to a specific transaction identified by the provided 'id' and a
	 * specific invoice identified by 'tId'.
	 *
	 * @param id  The ID of the transaction for which to retrieve related customer
	 *            bills.
	 * @param tId The ID of the invoice for which to retrieve related customer
	 *            bills.
	 * @return A list of CustomerBill objects representing bills related to the
	 *         specified transaction and invoice.
	 */
	@GetMapping("/invoices/{id}/{tId}")
	public List<CustomerBill> getInvoice(@PathVariable long id, @PathVariable long tId) {
		return customerBillServices.getInvoice(id, tId);
	}

	/**
	 * Retrieve a status message indicating the result of updating a transaction's
	 * payment.
	 *
	 * This endpoint allows you to update the payment status of a specific
	 * transaction identified by the provided 'id' and a specific payment identified
	 * by 'tId'. It then returns a status message indicating the result of the
	 * payment update operation.
	 *
	 * @param id  The ID of the transaction for which to update the payment status.
	 * @param tId The ID of the payment for which to update the payment status.
	 * @return A status message indicating the result of updating the payment
	 *         status.
	 */
	@GetMapping("/updates/{id}/{tId}")
	public String getPaymentUpdate(@PathVariable long id, @PathVariable long tId) {
		return customerBillServices.getPaymentUpdate(id, tId);
	}

	/**
	 * Retrieve a list of successful transaction records by a specific ID.
	 *
	 * This endpoint allows you to retrieve a list of successful transaction records
	 * identified by the provided 'id'. It returns a list of CustomerBill objects
	 * representing successful transactions.
	 *
	 * @param id The ID used to filter and retrieve successful transaction records.
	 * @return A list of CustomerBill objects representing successful transactions.
	 */
	@GetMapping("/success/{id}")
	public List<CustomerBill> getPaidBills(@PathVariable long id) {
		return customerBillServices.getPaidBills(id);
	}

	/**
	 * Retrieve a list of successful customer bill records for a specific ID and
	 * transaction ID.
	 *
	 * This endpoint allows you to retrieve a list of successful customer bill
	 * records identified by the provided 'id' and 'tId'. It returns a list of
	 * CustomerBill objects representing successful transactions.
	 *
	 * @param id  The ID used to filter and retrieve successful customer bill
	 *            records.
	 * @param tId The transaction ID used for further filtering of successful
	 *            customer bill records.
	 * @return A list of CustomerBill objects representing successful customer bill
	 *         records.
	 */
	@GetMapping("/successfull/{id}/{tId}")
	public List<CustomerBill> getPaidBillId(@PathVariable long id, @PathVariable long tId) {
		return customerBillServices.getPaidBillId(id, tId);
	}
}
