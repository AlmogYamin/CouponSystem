package com.couponsystem.dbdao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.DAO.CompanyDAO;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.exceptions.CompaniesNotExistException;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.repo.CompanyRepo;
import com.couponsystem.repo.CouponRepo;
/***
 * Data Base Access Object for Company
 * @author מיתר
 *
 */
@Service
public class CompanyDBDAO implements CompanyDAO {

	// Repository
	@Autowired
	private CompanyRepo compRepo;
	@Autowired
	private CouponRepo coupRepo;
	
	/***
	 * Creating new Company
	 */
	@Override
	public void createCompany(Company company) throws CompanyExistException {
		compRepo.save(company);

	}

	/***
	 * Removing Company by ID
	 */
	@Override
	public void removeCompany(int companyId) throws CompanyNotExistException {
		compRepo.delete(companyId);

	}

	/***
	 * Updating Company set only password & email
	 */
	@Override
	public void updateCompany(String email, String password, int companyId) throws CompanyNotExistException {
		compRepo.updateCompany(email, password, companyId);
	}

	/***
	 * Getting Company by id
	 */
	@Override
	public Company getCompany(int companyId) throws CompanyNotExistException {
		return compRepo.findOne(companyId);
	}

	/***
	 * Getting Company by name
	 */
	public Company getCompanyByName(String compName) {
		return compRepo.findByCompName(compName);
	}
	
	/***
	 * Getting Company by name & password
	 * @param compName
	 * @param password
	 * @return Company
	 */
	public Company getCompanyByNameAndPassword(String compName , String password)
	{
		return compRepo.findByCompNameAndPassword(compName, password);
	}

	/***
	 * Getting all Companies
	 */
	@Override
	public ArrayList<Company> getAllCompanies() throws CompaniesNotExistException {
		return (ArrayList<Company>) compRepo.findAll();
	}

	/***
	 * Get all Company's Coupons
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons(int companyId) throws CouponsNotExistException , CompanyNotExistException {
		return coupRepo.findByCompanyId(companyId);
	}

	/***
	 * Login method
	 */
	@Override
	public boolean login(String compName, String password) {
		Company check = compRepo.findByCompNameAndPassword(compName, password);
		if (check != null) {
			return true;
		}
		return false;
	}

}
