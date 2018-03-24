package com.couponsystem.facades;

import org.springframework.stereotype.Component;

import com.couponsystem.enums.ClientType;

/***
 * Generic Facade for login
 * @author מיתר
 *
 */
@Component
public interface CouponClientFacade {

	/***
	 * Login method for Facades
	 * @param name
	 * @param password
	 * @param type
	 * @return Facade
	 */
	CouponClientFacade login(String name , String password , ClientType type);
}
