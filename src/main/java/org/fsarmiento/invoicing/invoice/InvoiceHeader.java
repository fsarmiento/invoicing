package org.fsarmiento.invoicing.invoice;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.fsarmiento.invoicing.AbstractEntity;
import org.fsarmiento.invoicing.customer.Customer;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * The Class Invoice.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "invoice_header")
public class InvoiceHeader extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "account", referencedColumnName = "account")
	private Customer invoicee;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date invoiceDate;

	private InvoiceStatus status;

	@Column(columnDefinition = "text")
	private String notes;

	private String comments;

	@Column(length = 32)
	private String paymentTerms;

	@Column(length = 64)
	private String paymentRef;

	private BigDecimal totalNetValue;

	private BigDecimal totalGrossValue;

	private BigDecimal totalVat;

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
	 * @since
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
	 * Gets the total net value.
	 * 
	 * @return the total net value
	 */
	public BigDecimal getTotalNetValue() {
		return totalNetValue == null ? BigDecimal.ZERO : totalNetValue;
	}

	/**
	 * Sets the total net value.
	 * 
	 * @param totalNetValue
	 *            the new total net value
	 */
	public void setTotalNetValue(BigDecimal totalNetValue) {
		this.totalNetValue = totalNetValue;
	}

	/**
	 * Gets the total gross value.
	 * 
	 * @return the total gross value
	 */
	public BigDecimal getTotalGrossValue() {
		return totalGrossValue == null ? BigDecimal.ZERO : totalGrossValue;
	}

	/**
	 * Sets the total gross value.
	 * 
	 * @param totalGrossValue
	 *            the new total gross value
	 */
	public void setTotalGrossValue(BigDecimal totalGrossValue) {
		this.totalGrossValue = totalGrossValue;
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
	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
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
