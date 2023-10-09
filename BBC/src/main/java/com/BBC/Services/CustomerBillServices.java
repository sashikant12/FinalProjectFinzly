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

	public List<CustomerBill> getAlltranction() {
		logger.info("All customer tranctions");
		return customerBillDao.getAlltranction();
	}

	public List<CustomerBill> getAlltranctionByIdForPending(long id) {
		try {
			logger.info("In main function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill tranction : list) {
				Customer customer = tranction.getCustomer();
				if (customer.getCustomerId() == id && tranction.getStatus().equals("pending")) {
					logger.info(" customer pending status tranctions");
					newList.add(tranction);
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

	public String setAlltranctionByIdAndPaymentId(long id, long tId, long pId) {
		try {
			
			if (pId == 0) {
				logger.info("Not a valid Id. Please check payment mode id");
				throw new InvalidIdException("Not a valid Id. Please check id");

			} else if (pId > 3) {
				logger.info("Not a valid Id. Please check payment mode id");
				throw new InvalidIdException("Payment ID is greater than 3. Invalid payment mode.");

			} else {
				logger.info("In main function");
				List<CustomerBill> list = customerBillDao.getAllTransaction();
				for (CustomerBill tranction : list) {
					Customer customer = tranction.getCustomer();
					PaymentModes paymentMode = tranction.getPaymentMode();
					if (customer.getCustomerId() == id && tranction.getStatus().equals("pending")
							&& tranction.getBillId() == tId) {
						logger.info("transaction mode choosen");
						paymentMode.setPaymentModeId(pId);
						logger.info("" + paymentMode.getDiscountPercentage());

						tranction.setTotalAmount(tranction.getAmount()
								- ((tranction.getAmount() * paymentMode.getDiscountPercentage()) / 100));

						logger.info("" + tranction.getTotalAmount());

						if ((todaysDate.before(tranction.getEndDate()) && todaysDate.after(tranction.getStartDate()))
								|| (todaysDate.equals(tranction.getEndDate()))) {

							tranction.setTotalAmount(tranction.getTotalAmount()
									- ((tranction.getTotalAmount() * paymentMode.getDateDiscount()) / 100));
							logger.info("" + tranction.getTotalAmount());
						}
						return customerBillDao.setAllTransactionUpdate(tranction);
					}
				}

			}
		} catch (Exception e) {
			logger.error("Not a valid entry");
			return "Error: " + e.getMessage();
		}

		return "Not a valid entry";

	}

	public List<CustomerBill> getAlltranctionBillInvoice(long id, long tId) {

		try {
			logger.info("In main function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill tranction : list) {
				Customer customer = tranction.getCustomer();
				if (customer.getCustomerId() == id && tranction.getBillId() == tId
						&& tranction.getStatus().equals("pending")) {
					logger.info("Invoice generated");
					newList.add(tranction);
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

	public String getAlltranctionPaymentUpdate(long id, long tId) {

		try {
			logger.info("In main function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			for (CustomerBill tranction : list) {
				Customer customer = tranction.getCustomer();
				if (customer.getCustomerId() == id && tranction.getBillId() == tId
						&& tranction.getStatus().equals("pending")) {
					logger.info("transaction update");
					tranction.setStatus("success");
					return customerBillDao.getAlltranctionPaymentUpdate(tranction);
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

	public List<CustomerBill> getAlltranctionByIdForSucess(long id) {
		try {
			logger.info("In main function");
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill tranction : list) {
				Customer customer = tranction.getCustomer();
				if (customer.getCustomerId() == id && tranction.getStatus().equals("success")) {
					logger.info("success transaction");
					newList.add(tranction);
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

}
