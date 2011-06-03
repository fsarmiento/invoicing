package org.fsarmiento.invoicing.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="product")
public class Product extends AbstractEntity {
	
	@Column(length=32, nullable=false, unique=true)
	private String productCode;

	@Column(length=64)
	private String description;
	
	@Column(length=10)
	private String unitOfMeasure;
	
	private BigDecimal unitPrice;
	
	private BigDecimal vatRate;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}
}
