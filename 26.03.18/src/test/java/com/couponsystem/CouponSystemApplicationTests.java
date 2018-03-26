package com.couponsystem;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

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
import com.couponsystem.facades.CouponSytem;
import com.couponsystem.facades.CustomerFacade;
import com.couponsystem.sidekick.DateMaker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests {
	
	// Facades
//	@Autowired
//	private AdminFacade af;
//	@Autowired
//	private CompanyFacade compf;
//	@Autowired
//	private CustomerFacade custf;
	@Autowired
	private CouponSytem cs;

	@AfterClass
	public static void afterAll() {
		System.err.println("~~~~~~~The end ~~~~~~~~");
	}
	
	@Test
	public void contextLoads() {
	}

	/***
	 * Test for adminFacade
	 */

	@Test
	public void CouponSystemTest() {
		
		
		//================= admin test =====================//
		
		// LOGIN AS ADMIN + ASSERT
		AdminFacade af = (AdminFacade)cs.login("admin", "1234", ClientType.ADMIN);
		System.out.println(af);
		Assert.notNull(af, "login is null");

		// Creating Company
		try {
			af.createCompany(new Company("BUG", "1111", "bug@gmail.com"));
			af.createCompany(new Company("Aroma", "2222", "aroma@gmail.com"));
			af.createCompany(new Company("KSP", "3333", "alm@gmail.com"));
			af.createCompany(new Company("Club-Med", "4444", "clubmed@gmail.com"));
			af.createCompany(new Company("CafeCafe", "5555", "cafecafe@gmail.com"));
			af.createCompany(new Company("RemovosCompany", "1234", "ib@gmail.com"));
		} catch (CompanyExistException e) {
			System.out.println(e.getMessage());
		}

		// ASSERT
		// Checking if company exist
		try {
			Assert.notNull(af.getCompany(1), "Element not exist");
		} catch (CompanyNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Remove Company
		try {
			af.removeCompany(6);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking company does not exist
		try {
			Assert.isNull(af.getCompany(6), "Element exist");
		} catch (CompanyNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Update Company
		try {
			af.updateCompany("UPDATED-PASSWOED", "UPDATEDEMAIL @GMAIL.COM", 1);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking company updated
		try {
			Assert.isTrue(af.getCompany(1).getPassword().equals("UPDATED-PASSWOED"), "Element not updated");
		} catch (CompanyNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Get Company + ASSERT the get is not null
		try {
			Company adminComp = af.getCompany(1);
			Assert.notNull(adminComp, "Element is null");
			System.out.println(adminComp);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get all Companies + ASSERT the get is not null
		try {
			ArrayList<Company> allCompanies = af.getAllCompanies();
			Assert.notEmpty(allCompanies, "All Companies empty");
			System.out.println(allCompanies);
		} catch (CompaniesNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Creating Customer
		try {
			af.createCustomer(new Customer("Israel", "1234"));
			af.createCustomer(new Customer("Daniela", "1234"));
			af.createCustomer(new Customer("Gvir", "1234"));
			af.createCustomer(new Customer("Almog", "1234"));
			af.createCustomer(new Customer("Meital", "1234"));
			af.createCustomer(new Customer("Shmuel", "1234"));
			af.createCustomer(new Customer("Removus", "1234"));
		} catch (CustomerExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking customer exist
		try {
			Assert.notNull(af.getCustomer(1), "Element not exist");
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Remove Customer
		try {
			af.removeCustomer(7);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking Customer not exist
		try {
			Assert.isNull(af.getCustomer(7), "Element exist");
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Updating Customer
		try {
			af.updateCustomer("UPDATED-PASSWOED", 1);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking customer Updated
		try {
			Assert.isTrue(af.getCustomer(1).getPassword().equals("UPDATED-PASSWOED"), "Customer did not not updated");
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Get Customer + ASSERT the get is not null
		try {
			Customer adminCust = af.getCustomer(4);
			Assert.notNull(adminCust, "There is no Customer to this Company");
			System.out.println(adminCust);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get allCustomers + ASSERT the get is not null
		try {
			ArrayList<Customer> allCustomers = af.getAllCustomer();
			Assert.notNull(allCustomers, "Company is empty of Customers");
			System.out.println(allCustomers);
		} catch (CustomersNotExistException e) {
			System.err.println(e.getMessage());
		}

		
		
		//================= Company test =====================//
		
		// LOGIN AS COMPANY + ASSERT
		CompanyFacade compf = (CompanyFacade) cs.login("Aroma", "2222", ClientType.COMPANY);
		System.out.println(compf);

		// Creating Coupon
		try {
			compf.createCoupon(new Coupon("Brackfast", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1),
					100, CouponType.FOOD, "double Brackfast at the cost of one", 60.0, "brackfast.com/c.png"));
			compf.createCoupon(new Coupon("lunch", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 100,
					CouponType.FOOD, "double lunch at the cost of one", 90.0, "lunch.com/c.png"));
			compf.createCoupon(new Coupon("cookie in 50%", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1),
					100, CouponType.FOOD, "cookie in 50% off", 2.0, "cookie.com/c.png"));
			compf.createCoupon(new Coupon("forDelete", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1),
					100, CouponType.FOOD, "double dessert at the cost of one", 30.0, "dessert.com/c.png"));
		} catch (CouponExistException | CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking coupon created
		try {
			Assert.notNull(compf.getCoupon(1), "Element not exist");
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Remove Coupon
		try {
			compf.removeCoupon(4);
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking Coupon removed
		try {
			Assert.isNull(compf.getCoupon(4), "Cuopon is was not Removed");
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Update Coupon
		try {
			compf.updateCoupon(DateMaker.fixDate(2020, 1, 1), 999.0, 2);
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking coupon updated
		try {
			Assert.isTrue(compf.getCoupon(2).getPrice() == 999.0, "The Coupon was not updated");
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Get Company's Coupon + ASSERT the get is not null
		try {
			Coupon costumerCoupon = compf.getCoupon(3);
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
			ArrayList<Coupon> allCompanyCoupons = compf.getCompanyCoupons();
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
			ArrayList<Coupon> couponsByType = compf.getCompanyCouponsByType(CouponType.FOOD);
			Assert.notEmpty(couponsByType, "Empty Array list of Coupons");
			System.out.println(couponsByType);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Coupons laser then Price given + ASSERT the get is not null
		try {
			ArrayList<Coupon> couponsByPrice = compf.getCouponsLaserThenPrice(100);
			Assert.notEmpty(couponsByPrice, "Empty Arraylist of Coupons laser then the given Price");
			System.out.println(couponsByPrice);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Coupons up to date + ASSERT the get is not null
		try {
			ArrayList<Coupon> couponsByDate = compf.getCouponsByGivenEndDate(DateMaker.fixDate(2020, 1, 1), 2);
			Assert.notEmpty(couponsByDate, "Empty Arraylist of Coupons laser then the given Date");
			System.out.println(couponsByDate);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		
		//================= Customer test =====================//
		
		// Login as Customer
		CustomerFacade custf = (CustomerFacade)cs.login("Israel", "UPDATED-PASSWOED", ClientType.CUSTOMER);
		System.out.println(custf);

		// Purchase Coupon
		try {
			custf.purchaseCoupon(1);
		} catch (CustomerNotExistException | CouponNotExistException | CouponExistException | IllegalAmountException
				| ExpiredDateException e) {
			System.err.println(e.getMessage());
		}

		// ASSERT
		// Checking Customer Purchased Coupon
		try {
			Assert.notNull(custf.getPurchasedCoupon(1), "This Customer has no such Coupon");
		} catch (CustomerNotExistException | CouponNotExistException e) {
			System.out.println(e.getMessage());
		}

		// Getting all purchased Coupons + ASSERT the get is not null
		try {
			ArrayList<Coupon> allCustomerCoupons = custf.getAllPurchesedCoupons();
			Assert.notEmpty(allCustomerCoupons, "Array List of Coupons is empty");
		} catch (CustomerNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Getting all purchased Coupons by type + ASSERT the get is not null
		try {
			ArrayList<Coupon> allPurchesCouponsByType = custf.getAllPurchesCouponsByType(CouponType.FOOD);
			Assert.notEmpty(allPurchesCouponsByType , "Array List of Coupons is empty");
		} catch (CouponsNotExistException | CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}

		 // Getting all purchased Coupons by price + ASSERT the get is not null
		 try {
		 ArrayList<Coupon> allCustomerCoupons = custf.getCouponsByPrice(999999);
		 Assert.notEmpty(allCustomerCoupons, "ArrayList of Coupons laesr then given Price is null");
		 } catch (CustomerNotExistException | CouponsNotExistException e) {
		 System.out.println(e.getMessage());
		 }
		  
	}

}