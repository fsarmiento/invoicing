package org.fsarmiento.invoicing.entities;

import java.math.BigDecimal;

import javax.persistence.*;

import org.hibernate.annotations.Index;

/**
 * The Class Product.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "product")
public class Product extends AbstractEntity {

	@Column(length = 32, nullable = false, unique = true)
	@Index(name = "product_index")
	private String productCode;

	@Column(length = 64)
	private String description;

	@Column(length = 10)
	private String unitOfMeasure;

	@Column(precision = 19, scale = 2)
	private BigDecimal unitPrice;

	@Column(precision = 6, scale = 2)
	private BigDecimal vatRate;

	/**
	 * Gets the product code.
	 * 
	 * @return the product code
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Sets the product code.
	 * 
	 * @param productCode
	 *            the new product code
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the unit of measure.
	 * 
	 * @return the unit of measure
	 */
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/**
	 * Sets the unit of measure.
	 * 
	 * @param unitOfMeasure
	 *            the new unit of measure
	 */
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	/**
	 * Gets the unit price.
	 * 
	 * @return the unit price
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice == null ? BigDecimal.ZERO : unitPrice;
	}

	/**
	 * Sets the unit price.
	 * 
	 * @param unitPrice
	 *            the new unit price
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * Gets the vat rate.
	 * 
	 * @return the vat rate
	 */
	public BigDecimal getVatRate() {
		return vatRate == null ? BigDecimal.ZERO : vatRate;
	}

	/**
	 * Sets the vat rate.
	 * 
	 * @param vatRate
	 *            the new vat rate
	 */
	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}
}