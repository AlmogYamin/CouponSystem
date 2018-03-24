package com.couponsystem.DAO;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
/***
 * Data Access Object for Coupons
 * @author מיתר
 *
 */
@Service
public interface CouponDAO {

	/***
	 * Creating new Coupon by Company
	 * @param coupon
	 * @param compId
	 * @throws CouponExistException
	 * @throws CompanyNotExistException
	 */
	void createCoupon(Coupon coupon, int companyId) throws CouponExistException, CompanyNotExistException;

	/***
	 * Removing Coupon by Company
	 * @param coupId
	 * @param compId
	 * @throws CouponNotExistException
	 * @throws CompanyNotExistException
	 */
	void removeCoupon(int couponId, int companyId) throws CouponNotExistException, CompanyNotExistException;

	/***
	 * Updating Coupon set only endDate & price by Coupon's ID & Company's ID
	 * @param endDate
	 * @param price
	 * @param coupId
	 * @param compId
	 * @throws CouponNotExistException
	 * @throws CompanyNotExistException
	 */
	void updateCoupon(Date endDate, double price, int couponId, int companyId)
			throws CouponNotExistException, CompanyNotExistException;

	/***
	 * Get Coupon by ID
	 * @param coupId
	 * @return Coupon
	 * @throws CouponNotExistException
	 */
	Coupon getCoupon(int couponId) throws CouponNotExistException;

	/***
	 * Get all Coupons
	 * @return ArrayList<Coupon>
	 * @throws CouponsNotExistException
	 */
	ArrayList<Coupon> getAllCoupons() throws CouponsNotExistException;

	/***
	 * Get all Coupons by type
	 * @param type
	 * @return ArrayList<Coupon>
	 * @throws CouponsNotExistException
	 */
	ArrayList<Coupon> getCouponsByType(CouponType type) throws CouponsNotExistException;

}