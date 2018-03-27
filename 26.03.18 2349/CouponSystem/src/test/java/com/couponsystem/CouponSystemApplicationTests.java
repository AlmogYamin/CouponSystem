package com.couponsystem;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.couponsystem.dbdao.ConnectionPool;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Customer;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CompaniesNotExistException;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;
import com.couponsystem.exceptions.CustomersNotExistException;
import com.couponsystem.exceptions.ExpiredDateException;
import com.couponsystem.exceptions.IllegalAmountException;
import com.couponsystem.facades.AdminFacade;
import com.couponsystem.facades.CompanyFacade;
import com.couponsystem.facades.CouponSystem;
import com.couponsystem.facades.CustomerFacade;
import com.couponsystem.sidekick.DateMaker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests {
	
	//attributes
	@Autowired
	private CouponSystem cs;
	@Autowired
	private ConnectionPool cp;
	// Facades
	@Autowired
	private AdminFacade adminFacade;
	@Autowired
	private CompanyFacade companyFacade;
	@Autowired
	private CustomerFacade customerFacade;
	

	@AfterClass
	public static void afterAll() {
		System.err.println("~~~~~~~The end ~~~~~~~~");
	}
	
	@Test
	public void contextLoads() {
	}

	/***
	 * Test for adminFacade
	 * @throws InterruptedException 
	 */

	@Test
	public void CouponSystemTest() throws InterruptedException {
		
		
		//================= admin test =====================//
		
		// LOGIN AS ADMIN + ASSERT
		cp.getConnection();
		adminFacade = (AdminFacade) cs.login("admin", "1234", ClientType.ADMIN);
		cp.returnConnection();
		Assert.notNull(adminFacade, "login is null");

		// Creating Company
		try {
			adminFacade.createCompany(new Company("BUG", "1111", "bug@gmail.com"));
			adminFacade.createCompany(new Company("Aroma", "2222", "aroma@gmail.com"));
			adminFacade.createCompany(new Company("KSP", "3333", "alm@gmail.com"));
			adminFacade.createCompany(new Company("Club-Med", "4444", "clubmed@gmail.com"));
			adminFacade.createCompany(new Company("CafeCafe", "5555", "cafecafe@gmail.com"));
			adminFacade.createCompany(new Company("RemovosCompany", "1234", "ib@gmail.com"));
		} catch (CompanyExistException e) {
			System.out.println(e.getMessage());
		}

		// ASSERT
		// Checking if company exist
		try {
			Assert.notNull(adminFacade.getCompany(1), "Element not exist");
		} catch (CompanyNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Remove Company
		try {
			adminFacade.removeCompany(6);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking company does not exist
		try {
			Assert.isNull(adminFacade.getCompany(6), "Element exist");
		} catch (CompanyNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Update Company
		try {
			adminFacade.updateCompany("UPDATED-PASSWOED", "UPDATEDEMAIL @GMAIL.COM", 1);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking company updated
		try {
			Assert.isTrue(adminFacade.getCompany(1).getPassword().equals("UPDATED-PASSWOED"), "Element not updated");
		} catch (CompanyNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Get Company + ASSERT the get is not null
		try {
			Company adminComp = adminFacade.getCompany(1);
			Assert.notNull(adminComp, "Element is null");
			System.out.println(adminComp);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get all Companies + ASSERT the get is not null
		try {
			ArrayList<Company> allCompanies = adminFacade.getAllCompanies();
			Assert.notEmpty(allCompanies, "All Companies empty");
			System.out.println(allCompanies);
		} catch (CompaniesNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Creating Customer
		try {
			adminFacade.createCustomer(new Customer("Israel", "1234"));
			adminFacade.createCustomer(new Customer("Daniela", "1234"));
			adminFacade.createCustomer(new Customer("Gvir", "1234"));
			adminFacade.createCustomer(new Customer("Almog", "1234"));
			adminFacade.createCustomer(new Customer("Meital", "1234"));
			adminFacade.createCustomer(new Customer("Shmuel", "1234"));
			adminFacade.createCustomer(new Customer("Removus", "1234"));
		} catch (CustomerExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking customer exist
		try {
			Assert.notNull(adminFacade.getCustomer(1), "Element not exist");
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Remove Customer
		try {
			adminFacade.removeCustomer(7);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking Customer not exist
		try {
			Assert.isNull(adminFacade.getCustomer(7), "Element exist");
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Updating Customer
		try {
			adminFacade.updateCustomer("UPDATED-PASSWOED", 1);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking customer Updated
		try {
			Assert.isTrue(adminFacade.getCustomer(1).getPassword().equals("UPDATED-PASSWOED"), "Customer did not not updated");
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Get Customer + ASSERT the get is not null
		try {
			Customer adminCust = adminFacade.getCustomer(4);
			Assert.notNull(adminCust, "There is no Customer to this Company");
			System.out.println(adminCust);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get allCustomers + ASSERT the get is not null
		try {
			ArrayList<Customer> allCustomers = adminFacade.getAllCustomer();
			Assert.notNull(allCustomers, "Company is empty of Customers");
			System.out.println(allCustomers);
		} catch (CustomersNotExistException e) {
			System.err.println(e.getMessage());
		}

		
		
		//================= Company test =====================//
		
		// LOGIN AS COMPANY + ASSERT
		companyFacade = (CompanyFacade) cs.login("Aroma", "2222", ClientType.COMPANY);

		// Creating Coupon
		try {
			companyFacade.createCoupon(new Coupon("Brackfast", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1),
					100, CouponType.FOOD, "double Brackfast at the cost of one", 60.0, "brackfast.com/c.png"));
			companyFacade.createCoupon(new Coupon("lunch", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 100,
					CouponType.FOOD, "double lunch at the cost of one", 90.0, "lunch.com/c.png"));
			companyFacade.createCoupon(new Coupon("cookie in 50%", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1),
					100, CouponType.FOOD, "cookie in 50% off", 2.0, "cookie.com/c.png"));
			companyFacade.createCoupon(new Coupon("forDelete", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1),
					100, CouponType.FOOD, "double dessert at the cost of one", 30.0, "dessert.com/c.png"));
		} catch (CouponExistException | CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking coupon created
		try {
			Assert.notNull(companyFacade.getCoupon(1), "Element not exist");
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Remove Coupon
		try {
			companyFacade.removeCoupon(4);
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking Coupon removed
		try {
			Assert.isNull(companyFacade.getCoupon(4), "Cuopon is was not Removed");
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Update Coupon
		try {
			companyFacade.updateCoupon(DateMaker.fixDate(2020, 1, 1), 999.0, 2);
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking coupon updated
		try {
			Assert.isTrue(companyFacade.getCoupon(2).getPrice() == 999.0, "The Coupon was not updated");
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Get Company's Coupon + ASSERT the get is not null
		try {
			Coupon costumerCoupon = companyFacade.getCoupon(3);
			Assert.notNull(costumerCoupon, "The Company does not have this Coupon");
			System.out.println(costumerCoupon);
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.err.println(e.getMessage());
		}

		// //Get all Coupons + ASSERT the get is not null
		// try {
		// ArrayList<Coupon> allCoupons = compf.getAllCoupons();
		// Assert.notEmpty(allCoupons, "Empty Array list of Coupons");
		// System.out.println(allCoupons);
		// } catch (CompanyNotExistException | CouponsNotExistException e) {
		// System.err.println(e.getMessage());
		// }

		// Get all Company's Coupons + ASSERT the get is not null
		try {
			ArrayList<Coupon> allCompanyCoupons = companyFacade.getCompanyCoupons();
			Assert.notEmpty(allCompanyCoupons, "Company's Coupons ArrayList is empty");
			System.out.println(allCompanyCoupons);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// // Get all Coupons by Type + ASSERT the get is not null
		// try {
		// ArrayList<Coupon> couponByType = compf.getCouponsByType(CouponType.FOOD);
		// Assert.notEmpty(couponByType, "Empty Array list of Coupons");
		// System.out.println(couponByType);
		// } catch (CompanyNotExistException | CouponsNotExistException e) {
		// System.err.println(e.getMessage());
		// }
		//

		// Get Company's Coupons by Type + ASSERT the get is not null
		try {
			ArrayList<Coupon> couponsByType = companyFacade.getCompanyCouponsByType(CouponType.FOOD);
			Assert.notEmpty(couponsByType, "Empty Array list of Coupons");
			System.out.println(couponsByType);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Coupons laser then Price given + ASSERT the get is not null
		try {
			ArrayList<Coupon> couponsByPrice = companyFacade.getCouponsLaserThenPrice(100);
			Assert.notEmpty(couponsByPrice, "Empty Arraylist of Coupons laser then the given Price");
			System.out.println(couponsByPrice);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Coupons up to date + ASSERT the get is not null
		try {
			ArrayList<Coupon> couponsByDate = companyFacade.getCouponsByGivenEndDate(DateMaker.fixDate(2020, 1, 1), 2);
			Assert.notEmpty(couponsByDate, "Empty Arraylist of Coupons laser then the given Date");
			System.out.println(couponsByDate);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		
		//================= Customer test =====================//
		
		// Login as Customer
		customerFacade = (CustomerFacade) cs.login("Israel", "UPDATED-PASSWOED", ClientType.CUSTOMER);
		System.out.println(customerFacade);

		// Purchase Coupon
		try {
			customerFacade.purchaseCoupon(1);
		} catch (CustomerNotExistException | CouponNotExistException | CouponExistException | IllegalAmountException
				| ExpiredDateException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking Customer Purchased Coupon
		try {
			Assert.notNull(customerFacade.getPurchasedCoupon(1), "This Customer has no such Coupon");
		} catch (CustomerNotExistException | CouponNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Getting all purchased Coupons + ASSERT the get is not null
		try {
			ArrayList<Coupon> allCustomerCoupons = customerFacade.getAllPurchesedCoupons();
			Assert.notEmpty(allCustomerCoupons, "Array List of Coupons is empty");
		} catch (CustomerNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Getting all purchased Coupons by type + ASSERT the get is not null
		try {
			ArrayList<Coupon> allPurchesCouponsByType = customerFacade.getAllPurchesCouponsByType(CouponType.FOOD);
			Assert.notEmpty(allPurchesCouponsByType , "Array List of Coupons is empty");
		} catch (CouponsNotExistException | CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}

		 // Getting all purchased Coupons by price + ASSERT the get is not null
		 try {
		 ArrayList<Coupon> allCustomerCoupons = customerFacade.getCouponsByPrice(999999);
		 Assert.notEmpty(allCustomerCoupons, "ArrayList of Coupons laesr then given Price is null");
		 } catch (CustomerNotExistException | CouponsNotExistException e) {
		 System.out.println(e.getMessage());
		 }
		  
	}

}