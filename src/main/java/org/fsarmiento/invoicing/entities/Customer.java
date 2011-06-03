package org.fsarmiento.invoicing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The Class Customer.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name="customer")
public class Customer extends AbstractEntity {

	@Column(length = 16, unique = true)
	private String account;

	@Column(length = 64)
	private String name;

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
}
