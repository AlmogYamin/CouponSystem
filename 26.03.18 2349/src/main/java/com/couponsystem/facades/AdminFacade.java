package com.couponsystem.facades;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couponsystem.dbdao.CompanyDBDAO;
import com.couponsystem.dbdao.CustomerDBDAO;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Customer;
import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.CompaniesNotExistException;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;
import com.couponsystem.exceptions.CustomersNotExistException;

import lombok.ToString;

/***
 * AdminFacade for managing Companies & Customers
 * 
 * @author מיתר
 *
 */
@ToString
@Component
public class AdminFacade implements CouponClientFacade {

	// Login attributes
	private final String NAME = "admin";
	private final String PASSWORD = "1234";

	// DBDAO
	@Autowired
	private CompanyDBDAO compdb;
	@Autowired
	private CustomerDBDAO custdb;

	/***
	 * Login method for AdminFacade
	 */
	@Override
	public AdminFacade login(String name, String password, ClientType type) {
		// Checking type first
		if (!type.equals(ClientType.ADMIN)) {
			return null;
		}

		// Checking name & password
		String que = name + password;
		String ans = NAME + PASSWORD;
		if (!que.equals(ans)) {
			return null;
		}
		return this;
	}

	// !!!!!!-----Company------!!!!!!!

	/***
	 * Creating new Company if not exist
	 * 
	 * @param company
	 * @throws CompanyExistException
	 */
	public void createCompany(Company company) throws CompanyExistException {
		// Checking if exist
		Company check = compdb.getCompanyByName(company.getCompName());
		if (check != null) {
			throw new CompanyExistException("Company " + company.getCompName() + " already exist");
		}
		// Success - creating Company
		compdb.createCompany(company);
	}

	/***
	 * Removing Company if exist
	 * 
	 * @param companyId
	 * @throws CompanyNotExistException
	 */
	public void removeCompany(int companyId) throws CompanyNotExistException {
		// Checking if exist
		Company check = compdb.getCompany(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}
		// Success - remove Company
		compdb.removeCompany(companyId);
	}

	/***
	 * Update Company set only password & email by ID
	 * 
	 * @param password
	 * @param email
	 * @param companyId
	 * @throws CompanyNotExistException
	 */
	public void updateCompany(String password, String email, int companyId) throws CompanyNotExistException {
		// Checking if exist
		Company check = compdb.getCompany(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}
		// Success - update Company
		compdb.updateCompany(email, password, companyId);
	}

	/***
	 * Get Company by ID
	 * 
	 * @param companyId
	 * @return Company
	 * @throws CompanyNotExistException
	 */
	public Company getCompany(int companyId) throws CompanyNotExistException {
		// Checking if exist
		Company check = compdb.getCompany(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}
		// Success - return Company
		return check;
	}

	/***
	 * Get all Companies
	 * 
	 * @return ArrayList<Company>
	 * @throws CompaniesNotExistException
	 */
	public ArrayList<Company> getAllCompanies() throws CompaniesNotExistException {
		// Checking ArrayList not null
		ArrayList<Company> allCompanies = compdb.getAllCompanies();
		if (allCompanies == null) {
			throw new CompaniesNotExistException("Companies ArrayList = null");
		}

		// Success - return all Companies
		return allCompanies;

	}

	// !!!!!!-----Customer------!!!!!!!

	/***
	 * Creating new Customer if not exist
	 * 
	 * @param customer
	 * @throws CustomerExistException
	 */
	public void createCustomer(Customer customer) throws CustomerExistException {
		// Checking if exist
		Customer check = custdb.getCustomerByName(customer.getCustName());
		if (check != null) {
			throw new CustomerExistException("Customer " + customer.getCustName() + " already exist");
		}
		// Success - creating Customer
		custdb.createCustomer(customer);
	}

	/***
	 * Removing Customer if exist
	 * 
	 * @param customerId
	 * @throws CustomerNotExistException
	 */
	public void removeCustomer(int customerId) throws CustomerNotExistException {
		// checking if exist
		Customer check = custdb.getCustomer(customerId);
		if (check == null) {
			System.out.println("Customer with the ID: " + customerId + "does not exist");
		}
		// Success - remove Customer
		custdb.removeCustomer(customerId);
	}

	/***
	 * Updating Customer password using Customer id
	 * 
	 * @param password
	 * @param id
	 * @throws CustomerNotExistException
	 */
	public void updateCustomer(String password, int id) throws CustomerNotExistException {
		// checking if exist
		Customer check = custdb.getCustomer(id);
		if (check == null) {
			System.out.println("Customer with the ID: " + id + "does not exist");
		}
		// Success - update Customer
		custdb.updateCustomer(password, id);
	}

	/***
	 * Get Customer by ID
	 * 
	 * @param customerId
	 * @return
	 * @throws CustomerNotExistException
	 */
	public Customer getCustomer(int customerId) throws CustomerNotExistException {
		// checking if exist
		Customer check = custdb.getCustomer(customerId);
		if (check == null) {
			System.out.println("Customer with the ID: " + customerId + " does not exist");
		}
		// Success - get Customer
		return check;
	}

	/***
	 * Get all Customers
	 * 
	 * @return
	 * @throws CustomersNotExistException
	 */
	public ArrayList<Customer> getAllCustomer() throws CustomersNotExistException {
		// Checking ArrayList not null
		ArrayList<Customer> allCustomer = custdb.getAllCustomers();
		if (allCustomer == null) {
			throw new CustomersNotExistException("Customers ArrayList = null");
		}
		// Success - return all Customers
		return allCustomer;
	}

}