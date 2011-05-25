package org.fsarmiento.invoicing.entities;

/**
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public class Customer extends AbstractEntity {
	
	private String account;
	
	private String name;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
