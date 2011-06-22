package org.fsarmiento.invoicing.invoice;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.customer.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.*;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * The Class Invoice.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "invoice_header")
public class InvoiceHeader extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "account", referencedColumnName = "account")
    @ForeignKey(name = "invoice_header_customer_fk")
    private Customer invoicee;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date invoiceDate;

    @Column(columnDefinition = "int(2)")
    private InvoiceStatus status;

    @Column(columnDefinition = "text")
    private String notes;

    private String comments;

    @Column(length = 32)
    private String paymentTerms;

    @Column(length = 64)
    private String paymentRef;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal totalIncVat;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal totalExcVat;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal totalVat;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal totalDiscount;

    @Column(length = 32)
    private Currency currency;

    @OneToMany(mappedBy = "invoiceHeader", fetch = FetchType.LAZY)
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    private Set<InvoiceLine> invoiceLines = new HashSet<InvoiceLine>();

    /**
     * The Enum InvoiceStatus.
     * 
     * @author Florencio Sarmiento
     * @since 1.0
     */
    public enum InvoiceStatus {

	/** The REVIEW. */
	REVIEW,
	/** The HOLD. */
	HOLD,
	/** The APPROVED. */
	APPROVED,
	/** The SENT. */
	SENT,
	/** The PAID. */
	PAID,
	/** The REJECTED. */
	REJECTED
    }

    /**
     * Gets the invoicee.
     * 
     * @return the invoicee
     */
    public Customer getInvoicee() {
	return invoicee;
    }

    /**
     * Sets the invoicee.
     * 
     * @param invoicee
     *            the new invoicee
     */
    public void setInvoicee(Customer invoicee) {
	this.invoicee = invoicee;
    }

    /**
     * Gets the invoice date.
     * 
     * @return the invoice date
     */
    public Date getInvoiceDate() {
	return invoiceDate;
    }

    /**
     * Sets the invoice date.
     * 
     * @param invoiceDate
     *            the new invoice date
     */
    public void setInvoiceDate(Date invoiceDate) {
	this.invoiceDate = invoiceDate;
    }

    /**
     * Gets the status.
     * 
     * @return the status
     */
    public InvoiceStatus getStatus() {
	return status;
    }

    /**
     * Sets the status.
     * 
     * @param status
     *            the new status
     */
    public void setStatus(InvoiceStatus status) {
	this.status = status;
    }

    /**
     * Gets the notes.
     * 
     * @return the notes
     */
    public String getNotes() {
	return notes;
    }

    /**
     * Sets the notes.
     * 
     * @param notes
     *            the new notes
     */
    public void setNotes(String notes) {
	this.notes = notes;
    }

    /**
     * Gets the comments.
     * 
     * @return the comments
     */
    public String getComments() {
	return comments;
    }

    /**
     * Sets the comments.
     * 
     * @param comments
     *            the new comments
     */
    public void setComments(String comments) {
	this.comments = comments;
    }

    /**
     * Gets the payment terms.
     * 
     * @return the payment terms
     */
    public String getPaymentTerms() {
	return paymentTerms;
    }

    /**
     * Sets the payment terms.
     * 
     * @param paymentTerms
     *            the new payment terms
     */
    public void setPaymentTerms(String paymentTerms) {
	this.paymentTerms = paymentTerms;
    }

    /**
     * Gets the payment ref.
     * 
     * @return the payment ref
     */
    public String getPaymentRef() {
	return paymentRef;
    }

    /**
     * Sets the payment ref.
     * 
     * @param paymentRef
     *            the new payment ref
     */
    public void setPaymentRef(String paymentRef) {
	this.paymentRef = paymentRef;
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
     * Gets the total vat.
     * 
     * @return the total vat
     */
    public BigDecimal getTotalVat() {
	return totalVat == null ? BigDecimal.ZERO : totalVat;
    }

    /**
     * Sets the total vat.
     * 
     * @param totalVat
     *            the new total vat
     */
    public void setTotalVat(BigDecimal totalVat) {
	this.totalVat = totalVat;
    }

    /**
     * Gets the total discount.
     * 
     * @return the total discount
     */
    public BigDecimal getTotalDiscount() {
	return totalDiscount == null ? BigDecimal.ZERO : totalDiscount;
    }

    /**
     * Sets the total discount.
     * 
     * @param totalDiscount
     *            the new total discount
     */
    public void setTotalDiscount(BigDecimal totalDiscount) {
	this.totalDiscount = totalDiscount;
    }

    /**
     * Gets the currency.
     * 
     * @return the currency
     */
    public Currency getCurrency() {
	return currency;
    }

    /**
     * Sets the currency.
     * 
     * @param currency
     *            the new currency
     */
    public void setCurrency(Currency currency) {
	this.currency = currency;
    }

    /**
     * Gets the invoice lines.
     * 
     * @return the invoice lines
     */
    public Set<InvoiceLine> getInvoiceLines() {
	return invoiceLines;
    }

    /**
     * Sets the invoice lines.
     * 
     * @param invoiceLines
     *            the new invoice lines
     */
    private void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
	this.invoiceLines = invoiceLines;
    }

    /**
     * Adds the invoice line.
     * 
     * @param invoiceLine
     *            the invoice line
     */
    public void addInvoiceLine(InvoiceLine invoiceLine) {
	invoiceLine.setInvoiceHeader(this);
	this.invoiceLines.add(invoiceLine);
    }

    /**
     * Removes the invoice line.
     * 
     * @param invoiceLine
     *            the invoice line
     */
    public void removeInvoiceLine(InvoiceLine invoiceLine) {
	this.invoiceLines.remove(invoiceLine);
	invoiceLine.setInvoiceHeader(null);
    }

}
