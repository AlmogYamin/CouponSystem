package com.couponsystem.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.couponsystem.enums.CouponType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Entity(name = "Coupon")
public class Coupon {
	
	@Getter
	@Setter
	@Id 
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Getter
	@Setter
	@Column
	private String title;
	
	@Getter
	@Setter
	@Column
	private Date startDate;
	
	@Getter
	@Setter
	@Column
	private Date endDate;
	
	@Getter
	@Setter
	@Column
	private int amount;
	
	@Getter
	@Setter
	@Column
	@Enumerated(EnumType.STRING)
	private CouponType type;
	
	@Getter
	@Setter
	@Column
	private String message;
	
	@Getter
	@Setter
	@Column
	private double price;

	@Getter
	@Setter
	@Column
	private String image;
	
	@Setter
	@ManyToOne
	@JoinColumn(name = "Company_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Company company;
	
	@ManyToMany(mappedBy = "coupons")
	private List<Customer> customers = new ArrayList<>();
	


	/***
	 * CTR using fields for creating new object
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param type
	 * @param message
	 * @param price
	 * @param image
	 */
	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType type, String message,
			double price, String image) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	// toString
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}
}
