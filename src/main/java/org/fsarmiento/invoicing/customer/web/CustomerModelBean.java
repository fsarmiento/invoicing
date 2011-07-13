package org.fsarmiento.invoicing.customer.web;

import org.fsarmiento.invoicing.customer.*;

public class CustomerModelBean implements CustomerModel {

    private Customer customer;

    public Customer getCustomer() {
	return customer;
    }

    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

}
