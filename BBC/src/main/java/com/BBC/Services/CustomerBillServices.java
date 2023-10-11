package com.BBC.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BBC.Dao.CustomerBillDao;
import com.BBC.Entiity.Customer;
import com.BBC.Entiity.PaymentModes;
import com.BBC.Entiity.CustomerBill;
import com.BBC.Exception.InvalidIdException;
import com.BBC.Exception.TransactionsNotFoundException;

@Service
public class CustomerBillServices {
	@Autowired
	CustomerBillDao customerBillDao;

	private Date todaysDate = new Date();

	private static final Logger logger = LoggerFactory.getLogger(CustomerBillServices.class);

	public List<CustomerBill> getAlltransaction() {
		logger.info("All customer tranctions");
		return customerBillDao.getAllTransaction();
	}

	public List<CustomerBill> getPendingBills(long id) {
		try {
			logger.info("In main Pending function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill transaction : list) {
				Customer customer = transaction.getCustomer();
				if (customer.getCustomerId() == id && transaction.getStatus().equals("pending")) {
					logger.info(" customer pending status tranctions");
					newList.add(transaction);
				}
			}

			if (newList.isEmpty()) {
				throw new TransactionsNotFoundException("No pending transactions found for customer " + id);
			}

			return newList;
		} catch (TransactionsNotFoundException e) {
			logger.error("No pending transactions found for customer");
			logger.info("Error: " + e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error("An unexpected error occurred: " + e.getMessage());
			return null;
		}
	}

	public String setPaymentMode(long id, long tId, long pId) {
		try {

			if (pId == 0) {
				logger.error("Not a valid Id. Please check payment mode id");
				throw new InvalidIdException("Not a valid Id. Please check id");

			} else if (pId > 3) {
				logger.error("Not a valid Id. Please check payment mode id");
				throw new InvalidIdException("Payment ID is greater than 3. Invalid payment mode.");

			} else {
				logger.info("In main choose Payment mode function");
				List<CustomerBill> list = customerBillDao.getAllTransaction();
				for (CustomerBill transaction : list) {
					Customer customer = transaction.getCustomer();
					PaymentModes paymentMode = transaction.getPaymentMode();
					if (customer.getCustomerId() == id && transaction.getStatus().equals("pending")
							&& transaction.getBillId() == tId) {
						logger.info("transaction mode choosen");
						paymentMode.setPaymentModeId(pId);
						logger.info("" + paymentMode.getDiscountPercentage());

						transaction.setTotalAmount(transaction.getAmount()
								- ((transaction.getAmount() * paymentMode.getDiscountPercentage()) / 100));

						logger.info("" + transaction.getTotalAmount());

						if ((todaysDate.before(transaction.getEndDate())
								&& todaysDate.after(transaction.getStartDate()))
								|| (todaysDate.equals(transaction.getEndDate()))) {
							transaction.setDateDiscount(5);
							transaction.setTotalAmount(transaction.getTotalAmount()
									- ((transaction.getTotalAmount() * transaction.getDateDiscount()) / 100));
							logger.info("" + transaction.getTotalAmount());
						}
						return customerBillDao.setPaymentMode(transaction);
					}
				}

			}
		} catch (Exception e) {
			logger.error("Not a valid entry");
			return "Error: " + e.getMessage();
		}

		return "Not a valid entry";

	}

	public List<CustomerBill> getInvoice(long id, long tId) {

		try {
			logger.info("In main invoice function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill trasanction : list) {
				Customer customer = trasanction.getCustomer();
				PaymentModes paymentMode = trasanction.getPaymentMode();
				if (customer.getCustomerId() == id && trasanction.getBillId() == tId
						&& trasanction.getStatus().equals("pending")) {
					logger.info("Discount on Online Section");
					trasanction.setTotalAmount(trasanction.getAmount()
							- ((trasanction.getAmount() * paymentMode.getDiscountPercentage()) / 100));

					if ((todaysDate.before(trasanction.getEndDate()) && todaysDate.after(trasanction.getStartDate()))
							|| (todaysDate.equals(trasanction.getEndDate()))) {
						logger.info(
								"Date discount on according to the current date and lie b/w start and end date Section");
						trasanction.setDateDiscount(5);
						trasanction.setTotalAmount(trasanction.getTotalAmount()
								- ((trasanction.getTotalAmount() * trasanction.getDateDiscount()) / 100));

					}

					customerBillDao.setPaymentMode(trasanction);
					newList.add(trasanction);
					logger.info("Invoice generated");
				}
			}

			if (newList.isEmpty()) {
				throw new TransactionsNotFoundException(
						"No transactions found for customer " + id + " and transaction ID " + tId);
			}

			return newList;
		} catch (Exception e) {
			logger.error("No transactions found for customer " + id + " and transaction ID " + tId);
			logger.info("An unexpected error occurred: " + e.getMessage());
			return null;
		}
	}

	public String getPaymentUpdate(long id, long tId) {

		try {
			logger.info("In main Update function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			for (CustomerBill transaction : list) {
				Customer customer = transaction.getCustomer();
				if (customer.getCustomerId() == id && transaction.getBillId() == tId
						&& transaction.getStatus().equals("pending")) {
					transaction.setStatus("success");
					logger.info("transaction update");
					return customerBillDao.getPaymentUpdate(transaction);
				}
			}
			throw new TransactionsNotFoundException(
					"No pending payment found for customer " + id + " and transaction ID " + tId);
		} catch (TransactionsNotFoundException e) {
			logger.error("No pending payment found for customer " + id + " and transaction ID " + tId);
			logger.info("Error: " + e.getMessage());
			return "Payment update failed: " + e.getMessage();
		} catch (Exception e) {
			logger.error("An unexpected error occurred: ");
			logger.info("An unexpected error occurred: " + e.getMessage());
			return "An unexpected error occurred while updating payment";
		}

	}

	public List<CustomerBill> getPaidBills(long id) {
		try {
			logger.info("In main Success function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill transaction : list) {
				Customer customer = transaction.getCustomer();
				if (customer.getCustomerId() == id && transaction.getStatus().equals("success")) {
					logger.info("success transaction");
					newList.add(transaction);
				}
			}
			if (newList.isEmpty()) {
				throw new TransactionsNotFoundException("No successful transactions found for customer " + id);
			}

			return newList;
		} catch (TransactionsNotFoundException e) {
			logger.error("No successful transactions found for customer  " + id);
			logger.info("Error: " + e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error("An unexpected error occurred: ");
			logger.info("An unexpected error occurred: " + e.getMessage());
			return null;
		}
	}

	public List<CustomerBill> getPaidBillId(long id, long tId) {
		try {
			logger.info("In main successfull function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill transaction : list) {
				Customer customer = transaction.getCustomer();
				if (customer.getCustomerId() == id && transaction.getBillId() == tId
						&& transaction.getStatus().equals("success")) {
					logger.info("success");
					newList.add(transaction);
				}
			}

			if (newList.isEmpty()) {
				throw new TransactionsNotFoundException(
						"No transactions found for customer " + id + " and transaction ID " + tId);
			}

			return newList;
		} catch (Exception e) {
			logger.error("No transactions found for customer " + id + " and transaction ID " + tId);
			logger.info("An unexpected error occurred: " + e.getMessage());
			return null;
		}
	}

}
