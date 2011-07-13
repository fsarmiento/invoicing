package org.fsarmiento.invoicing;

import java.math.*;

import javax.persistence.*;

import org.springframework.format.annotation.*;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * The Class Product.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@MappedSuperclass
public abstract class AbstractProduct extends AbstractEntity {

    @Column(length = 32, nullable = false)
    private String productCode;

    @Column(length = 128)
    private String description;

    @Column(length = 10)
    private String unitOfMeasure;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal unitPrice;

    @Column(precision = 6, scale = 2)
    @NumberFormat(style = Style.PERCENT)
    private BigDecimal vatRate;

    @Column(precision = 6, scale = 2)
    @NumberFormat(style = Style.PERCENT)
    private BigDecimal discountRate;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal discountValue;

    private Boolean applyDiscountAfterVat;

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

    /**
     * Gets the discount rate.
     * 
     * @return the discount rate
     */
    public BigDecimal getDiscountRate() {
	return discountRate == null ? BigDecimal.ZERO : discountRate;
    }

    /**
     * Sets the discount rate.
     * 
     * @param discountRate
     *            the new discount rate
     */
    public void setDiscountRate(BigDecimal discountRate) {
	this.discountRate = discountRate;
    }

    /**
     * Gets the discount value.
     * 
     * @return the discount value
     */
    public BigDecimal getDiscountValue() {
	return discountValue == null ? BigDecimal.ZERO : discountValue;
    }

    /**
     * Sets the discount value.
     * 
     * @param discountValue
     *            the new discount value
     */
    public void setDiscountValue(BigDecimal discountValue) {
	this.discountValue = discountValue;
    }

    /**
     * Gets the apply discount after vat.
     * 
     * @return the apply discount after vat
     */
    public Boolean getApplyDiscountAfterVat() {
	return applyDiscountAfterVat == null ? Boolean.FALSE
		: applyDiscountAfterVat;
    }

    /**
     * Sets the apply discount after vat.
     * 
     * @param applyDiscountAfterVat
     *            the new apply discount after vat
     */
    public void setApplyDiscountAfterVat(Boolean applyDiscountAfterVat) {
	this.applyDiscountAfterVat = applyDiscountAfterVat;
    }

}
