package com.couponsystem.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couponsystem.enums.ClientType;

@Component
public class CouponSytem {

	// Object's members
		@Autowired
		private AdminFacade adminf;
		@Autowired
		private CompanyFacade compf;
		@Autowired
		private CustomerFacade custf;

		
	/***
	 * Login method
	 * @param name
	 * @param password
	 * @param type
	 * @return CouponClientFacade
	 */
	public CouponClientFacade login(String name , String password , ClientType type)
	{
		switch(type)
		{
		case ADMIN:
			return adminf.login(name, password, type);
		case COMPANY:
			return compf.login(name, password, type);
		case CUSTOMER:
			return custf.login(name, password, type);
			default:
				return null;
		}
	}
}
