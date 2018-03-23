package com.couponsystem.facades;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couponsystem.dbdao.CompanyDBDAO;
import com.couponsystem.dbdao.CouponDBDAO;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;

import lombok.Getter;
import lombok.ToString;

/***
 * CompanyFacade for managing Coupons
 * 
 * @author מיתר
 *
 */
@ToString
@Component
public class CompanyFacade implements CouponClientFacade {

	// Object's attribute
	@Getter
	private Company loginCompany;

	// DBDAO
	@Autowired
	private CompanyDBDAO compdb;
	@Autowired
	private CouponDBDAO coupdb;

	/***
	 * Login method for Company
	 */
	@Override
	public CompanyFacade login(String name, String password, ClientType type) {
		// Checking type first
		if (!type.equals(ClientType.COMPANY)) {
			return null;
		}
		// Checking name & password
		if (!compdb.login(name, password)) {
			return null;
		}

		// Success
		loginCompany = compdb.getCompanyByNameAndPassword(name, password);
		return this;
	}

	/***
	 * Creating Coupon
	 * 
	 * @param coupon
	 * @throws CompanyNotExistException
	 * @throws CouponExistException
	 */
	public void createCoupon(Coupon coupon) throws CompanyNotExistException, CouponExistException {
		// Checking Company
		if (loginCompany == null) {
			throw new CompanyNotExistException("Please login first");
		}
		// Checking Coupon
		Coupon check = coupdb.getCouponByTitleAndCompany(coupon.getTitle(), loginCompany.getId());
		if (check != null) {
			throw new CouponExistException("Coupon " + coupon.getTitle() + " already exist");
		}

		// Success - creating Coupon
		coupdb.createCoupon(coupon, loginCompany.getId());
	}

	/***
	 * Removing Coupon
	 * 
	 * @param coupon
	 * @throws CompanyNotExistException
	 * @throws CouponNotExistException
	 */
	public void removeCoupon(int couponId) throws CompanyNotExistException, CouponNotExistException {
		// Checking Company
		if (loginCompany == null) {
			throw new CompanyNotExistException("Please login first");
		}
		// Checking Coupon
		Coupon check = coupdb.getCoupon(couponId);
		if (check == null) {
			throw new CouponNotExistException("Coupon " + couponId + " dose not exist ");
		}

		// Success - remove Coupon
		coupdb.removeCoupon(couponId, loginCompany.getId());
	}

	/***
	 * Update Coupon set endDate & Price by couponId & companyId
	 * 
	 * @param endDate
	 * @param price
	 * @param couponId
	 * @param companyId
	 * @throws CompanyNotExistException
	 * @throws CouponNotExistException
	 */
	public void updateCoupon(Date endDate, double price, int couponId, int companyId)
			throws CompanyNotExistException, CouponNotExistException {
		// Checking Company
		if (loginCompany == null) {
			throw new CompanyNotExistException("Please login first");
		}
		// Checking Coupon
		Coupon check = coupdb.getCoupon(couponId);
		if (check == null) {
			throw new CouponNotExistException("Coupon " + couponId + " dose not exist ");
		}

		// Success - update Coupon set endDate and price
		coupdb.updateCoupon(endDate, price, couponId, companyId);
	}

	/***
	 * Get Coupon by ID
	 * 
	 * @param couponId
	 * @return
	 * @throws CompanyNotExistException
	 * @throws CouponNotExistException
	 */
	public Coupon getCoupon(int couponId) throws CompanyNotExistException, CouponNotExistException {
		// Checking Company
		if (loginCompany == null) {
			throw new CompanyNotExistException("Please login first");
		}
		// Checking Coupon
		Coupon check = coupdb.getCoupon(couponId);
		if (check == null) {
			throw new CouponNotExistException("Coupon " + couponId + " dose not exist ");
		}
		// Success - get Coupon
		return check;
	}

	/***
	 * Get all Coupons
	 * 
	 * @return
	 * @throws CompanyNotExistException
	 * @throws CouponsNotExistException
	 */
	public ArrayList<Coupon> getAllCoupons() throws CompanyNotExistException, CouponsNotExistException {
		// Checking Company
		if (loginCompany == null) {
			throw new CompanyNotExistException("Please login first");
		}
		// Checking ArrayList not null
		ArrayList<Coupon> allCoupons = coupdb.getAllCoupons();
		if (allCoupons == null) {
			throw new CouponsNotExistException("Coupons ArrayList = null");
		}
		// Success - get all Coupons
		return allCoupons;
	}

	/***
	 * Get all Coupons by type
	 * 
	 * @param couponType
	 * @return
	 * @throws CompanyNotExistException
	 * @throws CouponsNotExistException
	 */
	public ArrayList<Coupon> getCouponsByType(CouponType couponType)
			throws CompanyNotExistException, CouponsNotExistException {
		// Checking Company
		if (loginCompany == null) {
			throw new CompanyNotExistException("Please login first");
		}
		// Checking ArrayList not null
		ArrayList<Coupon> couponsByType = coupdb.getCouponsByType(couponType);
		if (couponsByType == null) {
			throw new CouponsNotExistException("Coupons ArrayList = null");
		}
		// Success - get all Coupons
		return couponsByType;
	}

	/***
	 * Get all Coupons lesser then given Price
	 * @param CouponPrice
	 * @return
	 * @throws CompanyNotExistException
	 * @throws CouponsNotExistException
	 */
	public ArrayList<Coupon> getCouponsLaserThenPrice(double CouponPrice) throws CompanyNotExistException, CouponsNotExistException {
		// Checking Company
		if (loginCompany == null) {
			throw new CompanyNotExistException("Please login first");
		}
		// Checking ArrayList not null
		ArrayList<Coupon> couponsLaserThenPrice = coupdb.getCouponLaserThenPriceGiven(CouponPrice);
		if (couponsLaserThenPrice == null) {
			throw new CouponsNotExistException("Coupons ArrayList = null");
		}
		return couponsLaserThenPrice;

	}

}
