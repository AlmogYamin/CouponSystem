package com.couponsystem.dbdao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.DAO.CouponDAO;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.repo.CompanyRepo;
import com.couponsystem.repo.CouponRepo;

/***
 * Data Base Access Object for Coupon
 * 
 * @author מיתר
 *
 */
@Service
public class CouponDBDAO implements CouponDAO {

	// Repository
	@Autowired
	private CouponRepo couponRepo;
	@Autowired
	private CompanyRepo companyRepo;

	/***
	 * Creating new Coupon by its Company ("father")
	 */
	@Override
	public void createCoupon(Coupon coupon, int companyId) throws CouponExistException, CompanyNotExistException {
		// Getting the Coupon's father
		Company father = companyRepo.findOne(companyId);
		coupon.setCompany(father);
		// Creating Coupon
		couponRepo.save(coupon);
	}

	/***
	 * Removing Coupon by its ID & Company's ID
	 */
	@Override
	public void removeCoupon(int couponId, int companyId) throws CouponNotExistException, CompanyNotExistException {
		couponRepo.removeCoupon(couponId, companyId);
	}

	/***
	 * Update Coupon set endDate & price by its ID & Company's ID
	 */
	@Override
	public void updateCoupon(Date endDate, double price, int couponId, int companyId)
			throws CouponNotExistException, CompanyNotExistException {
		couponRepo.updateCoupon(endDate, price, couponId, companyId);

	}

	/***
	 * Get Coupon by Coupon Id
	 */
	@Override
	public Coupon getCoupon(int couponId) throws CouponNotExistException {
		return couponRepo.findOne(couponId);
	}

	/**
	 * get Company's coupon
	 * 
	 * @param couponId
	 * @param companyId
	 * @return
	 * @throws CouponNotExistException
	 * @throws CompanyNotExistException
	 */
	public Coupon getCompanyCoupon(int couponId, int companyId) throws CouponNotExistException, CompanyNotExistException {
		return couponRepo.getCompanyCoupon(couponId, companyId);
	}

	/***
	 * Get Coupon by title & Company's ID
	 * 
	 * @param title
	 * @param companyId
	 * @return Coupon
	 */
	public Coupon getCouponByTitleAndCompany(String title, int companyId) {
		return couponRepo.findBytitleAndCompanyId(title, companyId);
	}

	/***
	 * Get all Coupons
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons() throws CouponsNotExistException {
		return (ArrayList<Coupon>) couponRepo.findAll();
	}

	/***
	 * Get all Coupons by type
	 */
	@Override
	public ArrayList<Coupon> getCouponsByType(CouponType type) throws CouponsNotExistException {
		return couponRepo.findBytype(type);
	}

	/***
	 * Get all Coupons laser then Price given
	 * 
	 * @param CouponPrice
	 * @return
	 */
	public ArrayList<Coupon> getCouponLaserThenPriceGiven(double CouponPrice) {
		return couponRepo.findWhereCouponPriceLaserThenPriceGiven(CouponPrice);
	}

	/**
	 * Get Customer's Coupons by Type
	 * @param type
	 * @param id
	 * @return
	 */
	public ArrayList<Coupon> getCouponsByTypeAndCustomerid(CouponType type, int id){
		return couponRepo.findbytypeAndCustomerId(type, id);
	}

}