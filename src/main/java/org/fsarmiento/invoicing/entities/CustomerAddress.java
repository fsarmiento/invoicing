package org.fsarmiento.invoicing.entities;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The Class CustomerAddress.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "customer_address")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "customer_id",
		"address_id", "addressType_id" }) })
public class CustomerAddress extends AbstractEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Customer customer;

	@ManyToOne(optional = false)
	private Address address;

	@ManyToOne(optional = false)
	private AddressType addressType;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date lastUpdated;

	/**
	 * Gets the customer.
	 * 
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 * 
	 * @param customer
	 *            the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address
	 *            the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets the address type.
	 * 
	 * @return the address type
	 */
	public AddressType getAddressType() {
		return addressType;
	}

	/**
	 * Sets the address type.
	 * 
	 * @param addressType
	 *            the new address type
	 */
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	/**
	 * Gets the date from.
	 * 
	 * @return the date from
	 */
	public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * Sets the date from.
	 * 
	 * @param dateFrom
	 *            the new date from
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * Gets the last updated.
	 * 
	 * @return the last updated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * Sets the last updated.
	 * 
	 * @param lastUpdated
	 *            the new last updated
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
