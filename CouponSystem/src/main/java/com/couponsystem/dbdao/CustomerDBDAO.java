package com.couponsystem.dbdao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.DAO.CustomerDAO;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Customer;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;
import com.couponsystem.exceptions.CustomersNotExistException;
import com.couponsystem.repo.CouponRepo;
import com.couponsystem.repo.CustomerRepo;
/***
 * Data Base Access Object for Customer
 * @author מיתר
 *
 */
@Service
public class CustomerDBDAO implements CustomerDAO {

	// Repository
	@Autowired
	private CustomerRepo custRepo;
	@Autowired
	private CouponRepo coupRepo;

	/***
	 * Creating new Customer
	 */
	@Override
	public void createCustomer(Customer customer) throws CustomerExistException {
		custRepo.save(customer);

	}

	/***
	 * Removing Customer by Id
	 */
	@Override
	public void removeCustomer(int custId) throws CustomerNotExistException {
		custRepo.delete(custId);

	}

	/***
	 * Updating Customer set only password
	 */
	@Override
	public void updateCustomer(String password, int id) throws CustomerNotExistException {
		custRepo.upadeCustomer(password, id);

	}

	/***
	 * Getting Customer by id
	 */
	@Override
	public Customer getCustomer(int id) throws CustomerNotExistException {
		return custRepo.findOne(id);
	}

	/***
	 * Getting Customer by name
	 */
	public Customer getCustomerByName(String custName) {
		return custRepo.findByCustName(custName);

	}

	/***
	 * Getting all Customers
	 */
	@Override
	public ArrayList<Customer> getAllCustomers() throws CustomersNotExistException {
		ArrayList<Customer> allCustomer = (ArrayList<Customer>) custRepo.findAll();
		return allCustomer;
	}

	/***
	 * Get Customer's Coupons
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons(int customerId) throws CouponsNotExistException , CustomerNotExistException {
		return coupRepo.findByCustomersId(customerId);
	}
	
	/***
	 * Purchasing Coupon
	 * @param couponId
	 * @param customerId
	 * @throws CouponNotExistException
	 * @throws CustomerNotExistException
	 */
	public void purchaseCoupon(int customerId , int couponId)throws CouponNotExistException , CustomerNotExistException
	{
		custRepo.purchaseCustoerCoupon(customerId, couponId);
		custRepo.updateAmount(couponId);
	}

	/***
	 * Login method
	 */
	@Override
	public boolean login(String custName, String password) {
		Customer check = custRepo.findByCustNameAndPassword(custName, password);
		if(check != null) {
			return true;
		}
		return false;
	}

}
