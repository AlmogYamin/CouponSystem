package com.couponsystem.repo;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.CouponType;

@Repository
public interface CouponRepo extends CrudRepository<Coupon, Integer> {

	/***
	 * Remove Coupon by its ID & Company's ID
	 * 
	 * @param couponId
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM Coupon coup WHERE coup.id = :couponId AND coup.company.id = :companyId")
	void removeCoupon(@Param("couponId") int couponId, @Param("companyId") int companyId);

	/***
	 * Updating Coupon set price & end date by Coupon's ID & Company's ID
	 * 
	 * @param endDate
	 * @param price
	 * @param couponId
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon coup SET coup.endDate= :endDate, coup.price = :price WHERE coup.id = :couponId AND coup.company.id = :companyId")
	void updateCoupon(@Param("endDate") Date endDate, @Param("price") double price, @Param("couponId") int couponId,
			@Param("companyId") int companyId);

	/***
	 * Get all Coupons by type
	 * 
	 * @param type
	 * @return ArrayList<Coupon>
	 */
	ArrayList<Coupon> findBytype(CouponType type);

	/***
	 * Get all Company's Coupons
	 * 
	 * @param companyId
	 * @return ArrayList<Coupon>
	 */
	ArrayList<Coupon> findByCompanyId(int companyId);

	/***
	 * Get Company's Coupon
	 * 
	 * @param couponId
	 * @param companyId
	 * @return Coupon
	 */
	Coupon findByIdAndCompanyId(int couponId, int companyId);

	/***
	 * Get Customer's Coupons
	 * 
	 * @param customerId
	 * @return ArrayList<Coupon>
	 */
	ArrayList<Coupon> findByCustomersId(int customerId);

	/***
	 * Get Customer's Coupon
	 * 
	 * @param couponId
	 * @param customerId
	 * @return Coupon
	 */
	Coupon findByIdAndCustomersId(int couponId, int customerId);

	/***
	 * Get Coupon by title & Company's ID
	 * 
	 * @param title
	 * @param companyId
	 * @return Coupon
	 */
	Coupon findBytitleAndCompanyId(String title, int companyId);

	/***
	 * Get all Coupons that are laser then given price
	 * 
	 * @param price
	 * @return
	 */
	 @Transactional
	 @Modifying
	 @Query ("SELECT coup FROM Coupon coup WHERE coup.price< :price")
	 ArrayList<Coupon> findWhereCouponPriceLaserThenPriceGiven( @Param ("price")double price);
}
