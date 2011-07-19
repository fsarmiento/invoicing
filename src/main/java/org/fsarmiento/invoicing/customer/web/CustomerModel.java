package org.fsarmiento.invoicing.customer.web;

import org.fsarmiento.invoicing.customer.*;

public class CustomerModel {

    private Customer customer;

    private boolean existing = false;

    public Customer getCustomer() {
	return customer;
    }

    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

    public boolean isExisting() {
	return existing;
    }

    public void setExisting(boolean existing) {
	this.existing = existing;
    }
}
