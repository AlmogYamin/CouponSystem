package com.couponsystem;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.couponsystem.dbdao.CompanyDBDAO;
import com.couponsystem.dbdao.CouponDBDAO;
import com.couponsystem.dbdao.CustomerDBDAO;
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
import com.couponsystem.exceptions.IllegalAmountException;
import com.couponsystem.facades.AdminFacade;
import com.couponsystem.facades.CompanyFacade;
import com.couponsystem.facades.CustomerFacade;
import com.couponsystem.facades.ExpiredDateException;
import com.couponsystem.repo.CouponRepo;
import com.couponsystem.sidekick.DateMaker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests {

	// DBDAO
	@Autowired
	private CompanyDBDAO compdb;
	@Autowired
	private CustomerDBDAO custdb;
	@Autowired
	private CouponDBDAO coupdb;

	// Facades
	@Autowired
	private AdminFacade af;
	@Autowired
	private CompanyFacade compf;
	@Autowired
	private CustomerFacade custf;
	

	@AfterClass
	public static void afterAll() {
		System.out.println("#############");
	}

	@Test
	public void contextLoads() {
	}

	@Ignore
	@Test
	public void dbdaoTest() {

		// ------------------------//
		// Company

		// Create Company
		try {
			compdb.createCompany(new Company("bug", "1111", "bug@gmail.com"));
			compdb.createCompany(new Company("Aroma", "2222", "aroma@gmail.com"));
			compdb.createCompany(new Company("alm", "3333", "alm@gmail.com"));
			compdb.createCompany(new Company("clubmed", "4444", "clubmed@gmail.com"));
			compdb.createCompany(new Company("cafecafe", "5555", "cafecafe@gmail.com"));
			compdb.createCompany(new Company("RemovosCompany", "1234", "ib@gmail.com"));
		} catch (CompanyExistException e) {
			System.err.println(e.getMessage());
		}

		// Remove
		// try {
		// compdb.removeCompany(6);
		// } catch (CompanyNotExistException e) {
		// System.err.println(e.getMessage());
		// }

		// Update
		try {
			compdb.updateCompany("AROMA@GMAIL.CO.IL", "2221", 2);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Company by ID
		try {
			Company byID = compdb.getCompany(2);
			System.out.println(byID);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get all Companies
		try {
			ArrayList<Company> allCompanies = compdb.getAllCompanies();
			System.out.println(allCompanies);
		} catch (CompaniesNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Login
		boolean login = compdb.login("bug", "1111");
		System.out.println(login);

		// ------------------------//
		// Customer

		// Create
		try {
			custdb.createCustomer(new Customer("ifrah", "1234"));
			custdb.createCustomer(new Customer("avner", "1234"));
			custdb.createCustomer(new Customer("almog", "1234"));
			custdb.createCustomer(new Customer("dor", "1234"));
			custdb.createCustomer(new Customer("gvir", "1234"));
			custdb.createCustomer(new Customer("removosCustomer", "1234"));
		} catch (CustomerExistException e) {
			System.err.println(e.getMessage());
		}

		// Remove
		// try {
		// custdb.removeCustomer(6);
		// } catch (CustomerNotExistException e) {
		// System.err.println(e.getMessage());
		// }

		// Update
		try {
			custdb.updateCustomer("6666", 1);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get
		try {
			Customer custId = custdb.getCustomer(1);
			System.out.println(custId);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get All
		try {
			ArrayList<Customer> allCustomers = custdb.getAllCustomers();
			System.out.println(allCustomers);
		} catch (CustomersNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Login
		boolean loginCust = custdb.login("ifrah", "6666");
		System.out.println(loginCust);

		// ------------------------//
		// Coupon

		// Create
		Coupon phone = new Coupon("free phone", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 10,
				CouponType.ELECTRICITY, "phone for free!", 1, "phone.com/c.png");
		Coupon coffe = new Coupon("Coffe break", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 20,
				CouponType.FOOD, "high qulity coffe", 9, "coffe.com/c.png");
		Coupon fridge = new Coupon("free fridge", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 30,
				CouponType.ELECTRICITY, "fridge for free!", 2, "fridge.com/c.png");
		Coupon weekned = new Coupon("weekned free", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 40,
				CouponType.TRAVELLING, "weekned at a low cost", 200, "weekned.com/c.png");
		Coupon cake = new Coupon("cake for go", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 50,
				CouponType.RESTURANTS, "buy coffe and get a cake", 11, "coffe.com/c.png");
		Coupon removos = new Coupon("Removos break", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 60,
				CouponType.FOOD, "high qulity coffe", 27, "coffe.com/c.png");

		try {
			coupdb.createCoupon(phone, 1);
			coupdb.createCoupon(coffe, 2);
			coupdb.createCoupon(fridge, 3);
			coupdb.createCoupon(weekned, 4);
			coupdb.createCoupon(cake, 5);
			coupdb.createCoupon(removos, 6);
		} catch (CouponExistException | CompanyNotExistException e) {
		}

		// Remove
		// try {
		// coupdb.removeCoupon(6, 6);
		// } catch (CouponNotExistException | CompanyNotExistException e) {
		// }

		// Update
		try {
			coupdb.updateCoupon(DateMaker.fixDate(2029, 1, 1), 199, 1, 1);
		} catch (CouponNotExistException | CompanyNotExistException e) {
		}

		// Get Coupon by ID
		try {
			Coupon byIDCoupon = coupdb.getCoupon(3);
			System.out.println(byIDCoupon);
		} catch (CouponNotExistException e) {
		}

		// Get all Coupons
		try {
			ArrayList<Coupon> allCoupons = coupdb.getAllCoupons();
			System.out.println(allCoupons);
		} catch (CouponsNotExistException e) {
		}

		// Get all Coupons by type
		try {
			ArrayList<Coupon> byType = coupdb.getCouponsByType(CouponType.FOOD);
			ArrayList<Coupon> byTypes = coupdb.getCouponsByType(CouponType.ELECTRICITY);
			System.out.println(byType + "\n" + byTypes);
		} catch (CouponsNotExistException e) {
		}

		// Get Company Coupons
		try {
			ArrayList<Coupon> companyCoupons = compdb.getAllCoupons(2);
			System.out.println(companyCoupons);
		} catch (CouponsNotExistException | CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Purchasing Coupon
		try {
			custdb.purchaseCoupon(3, 3);
		} catch (CouponNotExistException | CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Coustomer's Coupons
		try {
			ArrayList<Coupon> coustomresCoupon = custdb.getAllCoupons(4);
			System.out.println(coustomresCoupon);
		} catch (CouponsNotExistException | CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}
	}

	/***
	 * Test for adminFacade
	 */
	@Ignore
	@Test
	public void adminTest() {
		// Login
		af = af.login("admin", "1234", ClientType.ADMIN);
		System.out.println(af);

		// Creating Company
		try {
			af.createCompany(new Company("bug", "1111", "bug@gmail.com"));
			af.createCompany(new Company("aroma", "2222", "aroma@gmail.com"));
			af.createCompany(new Company("alm", "3333", "alm@gmail.com"));
			af.createCompany(new Company("clubmed", "4444", "clubmed@gmail.com"));
			af.createCompany(new Company("cafecafe", "5555", "cafecafe@gmail.com"));
			af.createCompany(new Company("RemovosCompany", "1234", "ib@gmail.com"));
		} catch (CompanyExistException e) {
			System.err.println(e.getMessage());
		}

		// Remove Company
		// try {
		// af.removeCompany(6);
		// af.removeCompany(6);
		// } catch (CompanyNotExistException e) {
		// System.err.println(e.getMessage());
		// }

		// Update Company
		try {
			af.updateCompany("000012340000", "BUG@GMAIL.COM", 1);
			af.updateCompany("1234", "armoa@icq", 2888);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Company
		try {
			Company adminComp = af.getCompany(1);
			System.out.println(adminComp);
			Company adminComp2 = af.getCompany(9999);
		} catch (CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get all Companies
		try {
			ArrayList<Company> allCompanies = af.getAllCompanies();
			System.out.println(allCompanies);
		} catch (CompaniesNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Login for company
		compf = compf.login("aroma", "2222", ClientType.COMPANY);
		System.out.println(compf.getLoginCompany());

		// creating Customer
		try {
			af.createCustomer(new Customer("israel", "1234"));
			af.createCustomer(new Customer("daniela", "1234"));
			af.createCustomer(new Customer("gvir", "1234"));
			af.createCustomer(new Customer("almog", "1234"));
			af.createCustomer(new Customer("meital", "1234"));
			af.createCustomer(new Customer("shmuel", "1234"));
			af.createCustomer(new Customer("removus", "1234"));
		} catch (CustomerExistException e) {
			System.err.println(e.getMessage());
		}

		// remove Customer
		// try {
		// af.removeCustomer(7);
		// } catch (CustomerNotExistException e) {
		// System.err.println(e.getMessage());
		// }

		// updating Customer
		try {
			af.updateCustomer("0000", 1);
			af.updateCustomer("0000", 234234);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// get Customer
		try {
			Customer adminCust = af.getCustomer(4);
			System.out.println(adminCust);
			Customer adminCust2 = af.getCustomer(9999);
		} catch (CustomerNotExistException e) {
			System.err.println(e.getMessage());
		}

		// get allCustomers
		try {
			ArrayList<Customer> allCustomers = af.getAllCustomer();
			System.out.println(allCustomers);
		} catch (CustomersNotExistException e) {
			System.err.println(e.getMessage());
		}

	}

	/***
	 * Test for CompanyFacade
	 */
	@Ignore
	@Test
	public void companyTest() {
		// Login
		compf = compf.login("aroma", "2222", ClientType.COMPANY);
		System.out.println(compf);

		// Creating Coupon
		try {
			compf.createCoupon(new Coupon("Brackfast", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1),
					100, CouponType.FOOD, "double Brackfast at the cost of one", 60, "brackfast.com/c.png"));
			compf.createCoupon(new Coupon("lunch", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 100,
					CouponType.FOOD, "double lunch at the cost of one", 90, "lunch.com/c.png"));
			compf.createCoupon(new Coupon("dessert", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 100,
					CouponType.FOOD, "double dessert at the cost of one", 30, "dessert.com/c.png"));
			compf.createCoupon(new Coupon("dessert", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), 100,
					CouponType.FOOD, "double dessert at the cost of one", 30, "dessert.com/c.png"));
		} catch (CouponExistException | CompanyNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Remove Coupon
		// try {
		// compf.removeCoupon(3);
		// } catch (CompanyNotExistException | CouponNotExistException e) {
		// System.err.println(e.getMessage());
		// }

		// Update Coupon
		try {
			compf.updateCoupon(DateMaker.fixDate(2020, 1, 1), 999, 2);
			compf.updateCoupon(DateMaker.fixDate(2020, 1, 1), 999, 999);
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Company's Coupon
		try {
			Coupon costumerCoupon = compf.getCoupon(2);
			System.out.println(costumerCoupon);
			Coupon costumerCoupon2 = compf.getCoupon(999);
		} catch (CompanyNotExistException | CouponNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get all Coupons
		try {
			ArrayList<Coupon> allCoupons = compf.getAllCoupons();
			System.out.println(allCoupons);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get all Company's Coupons
		try {
			ArrayList<Coupon> allCompanyCoupons = compf.getCompanyCoupons();
			System.out.println(allCompanyCoupons);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get all Coupons by Type
		try {
			ArrayList<Coupon> couponByType = compf.getCouponsByType(CouponType.FOOD);
			System.out.println(couponByType);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Company's Coupons by Type
		try {
			ArrayList<Coupon> allCoupons = compf.getCompanyCouponsByType(CouponType.FOOD);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Coupons laser then Price given
		try {
			ArrayList<Coupon> couponsByPrice = compf.getCouponsLaserThenPrice(0);
			System.out.println(couponsByPrice);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Get Coupons up to date
		System.out.println("this are getCouponsByGivenEndDate:");
		try {
			ArrayList<Coupon> allCoupons = compf.getCouponsByGivenEndDate(DateMaker.fixDate(2020, 1, 1), 2);
			System.out.println(allCoupons);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}

	}

	
	@Test
	public void customerTest() {

		// Login test
		custf = custf.login("israel", "0000", ClientType.CUSTOMER);
		System.out.println(custf);

		// Purchase Coupon
		try {
			custf.purchaseCoupon(1);
			System.out.println("good");
		} catch (CustomerNotExistException | CouponNotExistException | CouponExistException | IllegalAmountException
				| ExpiredDateException e) {
			System.err.println(e.getMessage());
		}

		// checking Customer holds Coupon
		try {
			Assert.notNull(custf.getPurchasedCoupon(1), "coupon is null");
		} catch (CustomerNotExistException | CouponNotExistException e) {
			System.err.println(e.getMessage());
		}

		// Getting all purchased Coupons
		try {
			ArrayList<Coupon> allCustomerCoupons = custf.getAllPurchesedCoupons();
			Assert.notEmpty(allCustomerCoupons, "Array List of Coupons is empty");
		} catch (CustomerNotExistException | CouponsNotExistException e) {
			System.err.println(e.getMessage());
		}
		
		try {
			ArrayList<Coupon> allPurchesCouponsByType = custf.getAllPurchesCouponsByType(CouponType.FOOD);
		} catch (CouponsNotExistException | CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}		
		
		// Getting all purchased Coupons by price
		try {
			ArrayList<Coupon> allCustomerCoupons = custf.getCouponsByPrice(999999);
			Assert.notEmpty(allCustomerCoupons, "Array List of Coupons is empty - no lower price");
		} catch (CustomerNotExistException | CouponsNotExistException e) {
			System.out.println(e.getMessage());
		}
	
	}
	
//	@Test
//	public void testOfRandomStuff() {
//		coupr.findbytypeAndCustomerId(CouponType.FOOD , 1);
//	}


}