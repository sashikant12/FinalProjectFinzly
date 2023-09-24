package com.BBC.Services;

import java.util.ArrayList; 
import java.util.Date;
import java.util.List;
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

	private Date date = new Date();

	public List<CustomerBill> getAlltranction() {
		return customerBillDao.getAlltranction();
	}

	public List<CustomerBill> getAlltranctionByIdForPending(long id) {
		try {
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill tranction : list) {
				Customer customer = tranction.getCustomer();
				if (customer.getCustomerId() == id && tranction.getStatus().equals("pending")) {
					newList.add(tranction);
				}
			}

			if (newList.isEmpty()) {
				throw new TransactionsNotFoundException("No pending transactions found for customer " + id);
			}

			return newList;
		} catch (TransactionsNotFoundException e) {

			System.out.println("Error: " + e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
			return null;
		}
	}

	public String setAlltranctionByIdAndPaymentId(long id, long tId, long pId) {
		try {
			if (pId == 0) {
				throw new InvalidIdException("Not a valid Id. Please check id");
			} else if (pId > 3) {
				throw new InvalidIdException("Payment ID is greater than 3. Invalid payment mode.");
			} else {

				List<CustomerBill> list = customerBillDao.getAllTransaction();
				for (CustomerBill tranction : list) {
					Customer customer = tranction.getCustomer();
					PaymentModes paymentMode = tranction.getPaymentMode();
//					if (pId == 2) {
//						paymentMode.setDiscountPercentage(5);
//					}
					if (customer.getCustomerId() == id && tranction.getStatus().equals("pending")
							&& tranction.getBillId() == tId) {
						paymentMode.setPaymentModeId(pId);

						System.out.println(paymentMode.getDiscountPercentage());

						tranction.setTotalAmount(
								tranction.getAmount() - ((tranction.getAmount() * paymentMode.getDiscountPercentage()) / 100));

						System.out.println(tranction.getTotalAmount());

						if ((date.before(tranction.getEndDate()) && date.after(tranction.getStartDate()))
								|| (date.equals(tranction.getEndDate()))) {

							tranction.setTotalAmount(tranction.getTotalAmount()
									- ((tranction.getTotalAmount() * paymentMode.getDateDiscount()) / 100));
							System.out.println(tranction.getTotalAmount());
						}
						return customerBillDao.setAllTransactionUpdate(tranction);
					}
				}

			}
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}

		return "Not a valid entry";

	}

	public List<CustomerBill> getAlltranctionBillInvoice(long id, long tId) {
		try {
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill tranction : list) {
				Customer customer = tranction.getCustomer();
				if (customer.getCustomerId() == id && tranction.getBillId() == tId && tranction.getStatus().equals("pending")) {
					newList.add(tranction);
				}
			}

			if (newList.isEmpty()) {
				throw new TransactionsNotFoundException(
						"No transactions found for customer " + id + " and transaction ID " + tId);
			}

			return newList;
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
			return null;
		}
	}

	public String getAlltranctionPaymentUpdate(long id, long tId) {

		try {
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			for (CustomerBill tranction : list) {
				Customer customer = tranction.getCustomer();
				if (customer.getCustomerId() == id && tranction.getBillId() == tId && tranction.getStatus().equals("pending")) {
					tranction.setStatus("success");
					return customerBillDao.getAlltranctionPaymentUpdate(tranction);
				}
			}
			throw new TransactionsNotFoundException(
					"No pending payment found for customer " + id + " and transaction ID " + tId);
		} catch (TransactionsNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			return "Payment update failed: " + e.getMessage();
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
			return "An unexpected error occurred while updating payment";
		}

	}

	public List<CustomerBill> getAlltranctionByIdForSucess(long id) {
		try {
			List<CustomerBill> list = customerBillDao.getAllTransaction();
			List<CustomerBill> newList = new ArrayList<>();
			for (CustomerBill tranction : list) {
				Customer customer = tranction.getCustomer();
				if (customer.getCustomerId() == id && tranction.getStatus().equals("success")) {
					newList.add(tranction);
				}
			}

			if (newList.isEmpty()) {
				throw new TransactionsNotFoundException("No successful transactions found for customer " + id);
			}

			return newList;
		} catch (TransactionsNotFoundException e) {

			System.out.println("Error: " + e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
			return null;
		}
	}

}
