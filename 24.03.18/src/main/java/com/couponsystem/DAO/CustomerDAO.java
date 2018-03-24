package com.couponsystem.DAO;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Customer;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;
import com.couponsystem.exceptions.CustomersNotExistException;
/***
 * Data Access Object for Customer
 * @author מיתר
 *
 */
@Service
public interface CustomerDAO {

	/***
	 * Creating new Customer
	 * @param customer
	 * @throws CustomerExistException
	 */
	void createCustomer(Customer customer) throws CustomerExistException;

	/***
	 * Removing Customer by Id
	 * @param custId
	 * @throws CustomerNotExistException
	 */
	void removeCustomer(int customerId) throws CustomerNotExistException;

	/***
	 * Update Customer set only password by ID
	 * @param password
	 * @param id
	 * @throws CustomerNotExistException
	 */
	void updateCustomer(String password, int customerId) throws CustomerNotExistException;

	/***
	 * Get Customer by ID
	 * @param customerId
	 * @return Customer
	 * @throws CustomerNotExistException
	 */
	Customer getCustomer(int customerId) throws CustomerNotExistException;

	/***
	 * Get all Customers
	 * @return ArrayList<Customer>
	 * @throws CustomersNotExistException
	 */
	ArrayList<Customer> getAllCustomers() throws CustomersNotExistException;

	/****
	 * Get Customer Coupons
	 * @param custId
	 * @return ArrayList<Coupon>
	 * @throws CouponsNotExistException
	 */
	ArrayList<Coupon> getAllCoupons(int customerId) throws CouponsNotExistException , CustomerNotExistException;

	/***
	 * Login method for Customrer
	 * @param custName
	 * @param password
	 * @return true if exist
	 */
	boolean login(String custName, String password);

}