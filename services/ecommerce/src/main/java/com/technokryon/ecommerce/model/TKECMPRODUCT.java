package com.technokryon.ecommerce.model;

import java.time.OffsetDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TKECM_PRODUCT")
@Data
@NoArgsConstructor
public class TKECMPRODUCT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TKECMP_ID")
	private String tkecmpId;

	@Column(name = "TKECMP_SKU", unique = true)
	private String tkecmpSKU;

	@Column(name = "TKECMP_NAME", unique = true)
	private String tkecmpName;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "TKECMP_CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_TKECMP_CATEGORY_ID"))
	private TKECMCATEGORY tkecmpCategoryId;

	@Column(name = "TKECMP_WEIGHT")
	private Float tkecmpWeight;

	@Column(name = "TKECMP_QUANTITY")
	private Integer tkecmpQuantity;

	@Column(name = "TKECMP_SHORT_DESC")
	private String tkecmpShortDesc;

	@Column(name = "TKECMP_LONG_DESC")
	private String tkecmpLongDesc;

	@Column(name = "TKECMP_COUNTRY_OF_MFG")
	private String tkecmpCountryOfMfg;

	@Column(name = "TKECMP_CREATED_DATE")
	private OffsetDateTime tkecmpCreatedDate;

	@Column(name = "TKECMP_CREATED_USERID")
	private String tkecmpCreatedUserId;

	@Column(name = "TKECMP_MODIFIED_DATE")
	private OffsetDateTime tkecmpModifiedDate;

	@Column(name = "TKECMP_MODIFIED_USERID")
	private String tkecmpModifiedUserId;

	@Column(name = "TKECMP_STATUS")
	private String tkecmpStatus;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "TKECMP_TYPE", foreignKey = @ForeignKey(name = "FK_TKECMP_TYPE"))
	private TKECMPRODUCTTYPE tkecmpType;

	@Column(name = "TKECMP_PRICE")
	private Double tkecmpPrice;

	@Column(name = "TKECMP_PARENT_ID")
	private String tkecmpParentId;

	@Column(name = "TKECMP_DEFAULT")
	private String tkecmpDefault;

}