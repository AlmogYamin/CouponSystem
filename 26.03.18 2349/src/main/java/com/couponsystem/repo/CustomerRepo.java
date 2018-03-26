package com.couponsystem.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.couponsystem.entities.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {
	
	/***
	 * Purchase Coupon for Customer
	 * @param customerId
	 * @param couponId
	 */
	@Transactional
	@Modifying 
	@Query(value = "INSERT INTO customer_coupons (customers_id, coupons_id) VALUES (:customerId, :couponId)", nativeQuery = true) 
	void purchaseCustoerCoupon(@Param("customerId") int  customerId, @Param("couponId") int couponId);
	
	/***
	 * Update Coupon's amount while purchasing  
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon coup SET coup.amount = amount-1 WHERE coup.id = :couponId")
	void updateAmount(@Param("couponId") int couponId);

	/***
	 * Updating Customer query set only password & email
	 * @param email
	 * @param password
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query ("UPDATE Customer cust SET cust.password = :password WHERE cust.id =:id")
	void upadeCustomer(@Param("password")String password,@Param("id")int id);

	/***
	 * find Customer by name
	 * @param custName
	 * @return
	 */
	Customer findByCustName(String custName);
	
	/***
	 * find Customer by name and password 
	 * @param custName
	 * @param Password
	 * @return
	 */
	Customer findByCustNameAndPassword(String custName, String password);
	
}