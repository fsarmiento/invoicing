package org.fsarmiento.invoicing.invoice;

import java.math.BigDecimal;

import javax.persistence.*;

import org.fsarmiento.invoicing.AbstractProduct;

/**
 * The Class InvoiceLine.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "invoice_line")
public class InvoiceLine extends AbstractProduct {

	@ManyToOne(optional = false)
	@JoinColumn(name = "invoiceId")
	private InvoiceHeader invoiceHeader;

	private BigDecimal netValue;

	private BigDecimal grossValue;

	private BigDecimal vatValue;

	private BigDecimal discountValue;

	/**
	 * Gets the invoice header.
	 * 
	 * @return the invoice header
	 */
	public InvoiceHeader getInvoiceHeader() {
		return invoiceHeader;
	}

	/**
	 * Sets the invoice header.
	 * 
	 * @param invoiceHeader
	 *            the new invoice header
	 */
	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	/**
	 * Gets the net value.
	 * 
	 * @return the net value
	 */
	public BigDecimal getNetValue() {
		return netValue;
	}

	/**
	 * Sets the net value.
	 * 
	 * @param netValue
	 *            the new net value
	 */
	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}

	/**
	 * Gets the gross value.
	 * 
	 * @return the gross value
	 */
	public BigDecimal getGrossValue() {
		return grossValue;
	}

	/**
	 * Sets the gross value.
	 * 
	 * @param grossValue
	 *            the new gross value
	 */
	public void setGrossValue(BigDecimal grossValue) {
		this.grossValue = grossValue;
	}

	/**
	 * Gets the vat value.
	 * 
	 * @return the vat value
	 */
	public BigDecimal getVatValue() {
		return vatValue;
	}

	/**
	 * Sets the vat value.
	 * 
	 * @param vatValue
	 *            the new vat value
	 */
	public void setVatValue(BigDecimal vatValue) {
		this.vatValue = vatValue;
	}

	/**
	 * Gets the discount value.
	 * 
	 * @return the discount value
	 */
	public BigDecimal getDiscountValue() {
		return discountValue;
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
}
