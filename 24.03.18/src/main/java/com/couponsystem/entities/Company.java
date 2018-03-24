package com.couponsystem.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Entity(name = "Company")
public class Company {
	
	@Id
	@Getter
	@Setter
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // PK
	
	@Getter
	@Setter
	@Column
	private String compName;
	
	@Getter
	@Setter
	@Column
	private String password;
	
	@Getter
	@Setter
	@Column
	private String email;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Coupon> coupons = new ArrayList<>();
	
	

	/***
	 * CTR using fields for create new objects
	 * @param compName
	 * @param password
	 * @param email
	 */
	public Company(String compName, String password, String email) {
		super();
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	
	// toString
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + "]";
	}
	
	
}
