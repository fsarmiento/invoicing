package org.fsarmiento.invoicing.customer;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.address.*;
import org.fsarmiento.invoicing.application.*;
import org.fsarmiento.invoicing.util.*;
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

    @Column(length = 16)
    private String title;

    @Column(length = 32)
    private String firstname;

    @Column(length = 32, nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    private Set<Application> applications = new HashSet<Application>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    private Set<Address> addresses = new HashSet<Address>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private CustomerStatus status;

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
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * Sets the title.
     * 
     * @param title
     *            the new title
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Gets the firstname.
     * 
     * @return the firstname
     */
    public String getFirstname() {
	return firstname;
    }

    /**
     * Sets the firstname.
     * 
     * @param firstname
     *            the new firstname
     */
    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    /**
     * Gets the lastname.
     * 
     * @return the lastname
     */
    public String getLastname() {
	return lastname;
    }

    /**
     * Sets the lastname.
     * 
     * @param lastname
     *            the new lastname
     */
    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
	StringBuilder name = new StringBuilder();

	if (!StringUtil.isNullOrEmpty(title)) {
	    name.append(title);
	}

	if (!StringUtil.isNullOrEmpty(firstname)) {
	    if (name.length() > 0) {
		name.append(" ");
	    }

	    name.append(firstname);
	}

	if (!StringUtil.isNullOrEmpty(lastname)) {
	    if (name.length() > 0) {
		name.append(" ");
	    }

	    name.append(lastname);
	}

	return name.toString();
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
    private void setApplications(Set<Application> applications) {
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

    /**
     * Gets the addresses.
     * 
     * @return the addresses
     */
    public Set<Address> getAddresses() {
	return addresses;
    }

    private void setAddresses(Set<Address> addresses) {
	this.addresses = addresses;
    }

    /**
     * Adds the address.
     * 
     * @param address
     *            the address
     */
    public void addAddress(Address address) {
	address.setOwner(this);
	this.addresses.add(address);
    }

    /**
     * Removes the address.
     * 
     * @param address
     *            the address
     */
    public void removeAddress(Address address) {
	this.addresses.remove(address);
	address.setOwner(null);
    }

    /**
     * Gets the status.
     * 
     * @return the status
     */
    public CustomerStatus getStatus() {
	return status;
    }

    /**
     * Sets the status.
     * 
     * @param status
     *            the new status
     */
    public void setStatus(CustomerStatus status) {
	this.status = status;
    }
}
