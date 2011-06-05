package org.fsarmiento.invoicing.entities;

import javax.persistence.*;

import org.hibernate.annotations.Index;

/**
 * The Class Customer.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "customer")
public class Customer extends AbstractEntity {

	@Column(length = 16, unique = true, nullable = false)
	@Index(name = "account_index")
	private String account;

	@Column(length = 64)
	private String name;

	@Column(columnDefinition = "tinyint(1) default 0")
	private Boolean onHold = Boolean.FALSE;

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
	 * @param accounttrue
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
}
