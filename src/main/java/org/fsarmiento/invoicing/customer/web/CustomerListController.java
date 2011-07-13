package org.fsarmiento.invoicing.customer.web;

import java.util.*;

import javax.servlet.http.*;

import org.fsarmiento.invoicing.address.*;
import org.fsarmiento.invoicing.customer.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.zkoss.spring.context.annotation.*;
import org.zkoss.spring.util.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

@Component("customerListController")
@Scope("desktop")
public class CustomerListController extends GenericSpringComposer {

    private Logger logger = LoggerFactory
	    .getLogger(CustomerListController.class);

    private Customer customer;

    private List<Customer> customers;

    private Address shippingAddress = new Address();

    private Address billingAddress = new Address();

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Button btnSave;

    @Autowired
    private Button btnEdit;

    @Autowired
    private Button btnDelete;

    @Autowired
    private Listbox custList;

    @Override
    public void doBeforeComposeChildren(org.zkoss.zk.ui.Component comp)
	    throws Exception {
	super.doBeforeComposeChildren(comp);
	customers = customerService.listCustomers();
	comp.setAttribute("customers", customers);

	// customer = (Customer) Executions.getCurrent().getDesktop()
	// .getAttribute("customer");
	customer = (Customer) ((HttpServletRequest) Executions.getCurrent()
		.getNativeRequest()).getAttribute("customer");

	if (customer == null) {
	    customer = new Customer();
	}

	// comp.setAttribute("customer", customer);
    }

    @Override
    public void doAfterCompose(org.zkoss.zk.ui.Component comp) throws Exception {
	super.doAfterCompose(comp);
    }

    @EventHandler("btnSave.onClick")
    public void saveCustomer(Event evt) throws WrongValueException,
	    InterruptedException {
	logger.info("btnSave has been clicked!");
	customerService.saveCustomer(customer);
	Executions.getCurrent().getSession().setAttribute("customer", customer);

    }

    @EventHandler("btnEdit.onClick")
    public void editCustomer(Event evt) throws WrongValueException,
	    InterruptedException {
	logger.info("btnEdit has been clicked!");
	customerService.saveCustomer(customer);
	// Executions.getCurrent().getDesktop().setAttribute("customer",
	// customer);
	((HttpServletRequest) Executions.getCurrent().getNativeRequest())
		.setAttribute("customer", customer);
	Executions.sendRedirect("customer_list.zul");

    }

    @EventHandler("btnDelete.onClick")
    public void deleteCustomer(Event evt) throws WrongValueException,
	    InterruptedException {
	logger.info("btnDelete.onClick has been clicked!");
	customerService.deleteCustomer(customer);
	custList.getSelectedItem().detach();
	customer = null;
	// reload component
    }

    public Customer getCustomer() {
	return customer;
    }

    public void setCustomer(Customer customer) {
	this.customer = customer;
    }

}
