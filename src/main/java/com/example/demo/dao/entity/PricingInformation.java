package com.example.demo.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "packages")
@Data
public class PricingInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;

	@Column(name = "package")
	private String packageType;

	@Column(name = "to_do_list")
	private String toToList;

	@Column(name = "price")
	private String price;

	@Column(name = "button")
	private String button;

	@Column(name = "features")
	private String features;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "valid_from")
	private Date validFrom;

	@Column(name = "valid_to")
	private Date validTo;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

}
