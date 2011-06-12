package org.fsarmiento.invoicing.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.spring.context.annotation.EventHandler;
import org.zkoss.spring.util.GenericSpringComposer;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

@Component("customerController")
@Scope("desktop")
public class CustomerController extends GenericSpringComposer {

	private Customer customer = new Customer();

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Button btnSave;

	@EventHandler("btnSave.onClick")
	public void saveCustomer(Event evt) throws WrongValueException,
			InterruptedException {
		Messagebox.show("Hello " + customer.getAccount() + "!");
		customerService.saveCustomer(customer);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
