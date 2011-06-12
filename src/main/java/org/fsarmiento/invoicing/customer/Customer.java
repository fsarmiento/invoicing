package org.fsarmiento.invoicing.customer;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.fsarmiento.invoicing.AbstractEntity;
import org.fsarmiento.invoicing.address.Address;
import org.fsarmiento.invoicing.application.Application;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

/**
 * The Class Customer.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "customer")
public class Customer extends AbstractEntity {

	@Column(length = 16, nullable = false)
	@Index(name = "account_index")
	private String account;

	@Column(length = 64)
	private String name;

	@Column(columnDefinition = "tinyint(1) default 0")
	private Boolean onHold = Boolean.FALSE;

	@ManyToOne(cascade = { javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.REFRESH })
	@JoinColumn(name = "billingAddressId")
	private Address billingAddress;

	@ManyToOne(cascade = { javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.REFRESH })
	@JoinColumn(name = "shippingAddressId")
	private Address shippingAddress;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	private Set<Application> applications = new HashSet<Application>();

	/**
	 * Gets the account.
	 * 
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * Sets the account.
	 * 
	 * @param account
	 *            the new account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the on hold.
	 * 
	 * @return the on hold
	 */
	public Boolean getOnHold() {
		return onHold == null ? Boolean.FALSE : onHold;
	}

	/**
	 * Sets the on hold.
	 * 
	 * @param onHold
	 *            the new on hold
	 */
	public void setOnHold(Boolean onHold) {
		this.onHold = onHold;
	}

	/**
	 * Gets the billing address.
	 * 
	 * @return the billing address
	 */
	public Address getBillingAddress() {
		return billingAddress;
	}

	/**
	 * Sets the billing address.
	 * 
	 * @param billingAddress
	 *            the new billing address
	 */
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * Gets the shipping address.
	 * 
	 * @return the shipping address
	 */
	public Address getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * Sets the shipping address.
	 * 
	 * @param shippingAddress
	 *            the new shipping address
	 */
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * Gets the applications.
	 * 
	 * @return the applications
	 */
	public Set<Application> getApplications() {
		return applications;
	}

	/**
	 * Sets the applications.
	 * 
	 * @param applications
	 *            the new applications
	 */
	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	/**
	 * Adds the application.
	 * 
	 * @param application
	 *            the application
	 */
	public void addApplication(Application application) {
		application.setOwner(this);
		this.applications.add(application);
	}

	/**
	 * Removes the application.
	 * 
	 * @param application
	 *            the application
	 */
	public void removeApplication(Application application) {
		this.applications.remove(application);
		application.setOwner(null);
	}

}
