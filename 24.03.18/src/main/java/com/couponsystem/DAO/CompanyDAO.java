package com.couponsystem.DAO;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.exceptions.CompaniesNotExistException;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
/***
 * Data Access Object for Company
 * @author מיתר
 *
 */
@Service
public interface CompanyDAO {

	/***
	 * Creating new Company
	 * @param company
	 * @throws CompanyExistException
	 */
	void createCompany(Company company)throws CompanyExistException;
	
	/***
	 * Removing Company by ID
	 * @param compId
	 * @throws CompanyNotExistException
	 */
	void removeCompany(int companyId)throws CompanyNotExistException;
	
	/***
	 * Updating Company set only password & email by ID
	 * @param password
	 * @param email
	 * @param id
	 * @throws CompanyNotExistException
	 */
	void updateCompany(String password , String email , int companyId)throws CompanyNotExistException;
	
	/***
	 * Get Company by ID
	 * @param id
	 * @return Company
	 * @throws CompanyNotExistException
	 */
	Company getCompany(int companyId)throws CompanyNotExistException;
	
	/***
	 * Get all Companies
	 * @return ArrayList<Company>
	 * @throws CompaniesNotExistException
	 */
	ArrayList<Company> getAllCompanies()throws CompaniesNotExistException;
	
	/***
	 * Get Company's Coupons
	 * @param companyId
	 * @return ArrayList<Coupon>
	 * @throws CouponsNotExistException
	 */
	ArrayList<Coupon> getAllCoupons(int companyId)throws CouponsNotExistException , CompanyNotExistException;
	
	/***
	 * Login method for Company
	 * @param compName
	 * @param password
	 * @return true if exist
	 */
	boolean login(String compName , String password);
}