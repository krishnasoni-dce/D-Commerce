package com.technokryon.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TKECM_CATEGORY")
@Data
@NoArgsConstructor
public class TKECMCATEGORY {

	@Id
	@Column(name = "TKECMC_CATEGORY_ID")
	private String tkecmcCategoryId;

	@Column(name = "TKECMC_PARENT_ID")
	private String tkecmcParentId;

	@Column(name = "TKECMC_CATEGORY_NAME")
	private String tkecmcCategoryName;

	@Column(name = "TKECMC_CATEGORY_LEVEL")
	private Integer tkecmcCategoryLevel;

}