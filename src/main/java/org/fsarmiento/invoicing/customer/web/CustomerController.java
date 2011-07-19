package org.fsarmiento.invoicing.customer.web;

import org.fsarmiento.invoicing.customer.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.*;

@Controller("customerController")
@Scope("desktop")
public class CustomerController extends GenericForwardComposer {

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerModel customerModel;

    @Autowired
    private CustomerService customerService;

    @Override
    public void doBeforeComposeChildren(org.zkoss.zk.ui.Component comp)
	    throws Exception {
	super.doBeforeComposeChildren(comp);

	customerModel = new CustomerModel();

	Customer customer = (Customer) Executions.getCurrent().getSession()
		.getAttribute("customer");

	boolean custExist = customer != null;

	if (!custExist) {
	    customer = new Customer();

	    // Address billingAddress = new Address();
	    // customer.setBillingAddress(billingAddress);
	    //
	    // Address shippingAddress = new Address();
	    // customer.setShippingAddress(shippingAddress);
	}

	customerModel.setExisting(custExist);
	customerModel.setCustomer(customer);
    }

    public void onClick$btnSaveOrUpdateCustomer(Event evt)
	    throws InterruptedException {
	logger.info("onSaveOrUpdate is called");

	Customer customer = customerModel.getCustomer();

	if (customer.getId() == null) {
	    customerService.save(customer);

	} else {
	    customerService.update(customer);
	}

	onClick$btnClose(evt);
    }

    public void onClick$btnDeleteCustomer(Event evt)
	    throws InterruptedException {
	logger.info("delete has been clicked!");
	customerService.delete(customerModel.getCustomer());
    }

    public void onClick$btnClose(Event evt) throws InterruptedException {
	logger.info("btnClose has been clicked!");
	evt.getTarget().getRoot().detach();
    }

    public CustomerModel getCustomerModel() {
	return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
	this.customerModel = customerModel;
    }
}
