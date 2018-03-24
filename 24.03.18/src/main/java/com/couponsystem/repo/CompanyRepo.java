package com.couponsystem.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.couponsystem.entities.Company;

@Repository
public interface CompanyRepo extends CrudRepository<Company, Integer> {

	/***
	 * Updating query set only password & email
	 * @param email
	 * @param password
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Company comp SET comp.email = :email , comp.password = :password WHERE comp.id =:companyId")
	void updateCompany(@Param("email")String email,@Param("password") String password,@Param("companyId") int companyId);
	
	/***
	 * finding company by name
	 * @param compName
	 * @return company
	 */
	Company findByCompName(String compName);
	
	/***
	 * find company by company name and password 
	 * @param compName
	 * @param password
	 * @return
	 */
	Company findByCompNameAndPassword (String compName , String password);
	
}