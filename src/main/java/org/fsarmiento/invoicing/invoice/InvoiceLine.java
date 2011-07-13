package org.fsarmiento.invoicing.invoice;

import java.math.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.fsarmiento.invoicing.*;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.*;
import org.springframework.format.annotation.NumberFormat.Style;

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
    @ForeignKey(name = "invoice_line_header_fk")
    private InvoiceHeader invoiceHeader;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal totalIncVat;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal totalExcVat;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal vatValue;

    @NumberFormat(style = Style.NUMBER)
    private BigDecimal quantity;

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
     * Gets the total inc vat.
     * 
     * @return the total inc vat
     */
    public BigDecimal getTotalIncVat() {
	return totalIncVat == null ? BigDecimal.ZERO : totalIncVat;
    }

    /**
     * Sets the total inc vat.
     * 
     * @param totalIncVat
     *            the new total inc vat
     */
    public void setTotalIncVat(BigDecimal totalIncVat) {
	this.totalIncVat = totalIncVat;
    }

    /**
     * Gets the total exc vat.
     * 
     * @return the total exc vat
     */
    public BigDecimal getTotalExcVat() {
	return totalExcVat == null ? BigDecimal.ZERO : totalExcVat;
    }

    /**
     * Sets the total exc vat.
     * 
     * @param totalExcVat
     *            the new total exc vat
     */
    public void setTotalExcVat(BigDecimal totalExcVat) {
	this.totalExcVat = totalExcVat;
    }

    /**
     * Gets the vat value.
     * 
     * @return the vat value
     */
    public BigDecimal getVatValue() {
	return vatValue == null ? BigDecimal.ZERO : vatValue;
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
     * Gets the quantity.
     * 
     * @return the quantity
     */
    public BigDecimal getQuantity() {
	return quantity;
    }

    /**
     * Sets the quantity.
     * 
     * @param quantity
     *            the new quantity
     */
    public void setQuantity(BigDecimal quantity) {
	this.quantity = quantity;
    }
}
