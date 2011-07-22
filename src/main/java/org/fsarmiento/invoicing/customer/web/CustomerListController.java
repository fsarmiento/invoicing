package org.fsarmiento.invoicing.customer.web;

import java.util.*;

import org.fsarmiento.invoicing.customer.*;
import org.fsarmiento.invoicing.shared.web.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zul.*;

@Controller("customerListController")
@Scope("desktop")
public class CustomerListController extends GenericForwardComposer {

    private Logger logger = LoggerFactory
	    .getLogger(CustomerListController.class);

    private Customer selectedCustomer;

    private List<Customer> custList;

    @Autowired
    private CustomerService customerService;

    @Override
    public void doBeforeComposeChildren(Component comp) throws Exception {
	super.doBeforeComposeChildren(comp);

	custList = new ArrayList<Customer>();

	for (int index = 1; index <= 100; index++) {
	    Customer customer = new Customer();
	    customer.setAccount("Account " + index);
	    customer.setName("Test Name " + index);
	    custList.add(customer);
	}

	comp.setAttribute("custList", custList);
    }

    public void onClick$btnAdd(Event evt) throws WrongValueException,
	    InterruptedException {
	logger.info("btnAddCustomer has been clicked!");
	openCustomerScreen(null);
    }

    public void onClick$btnEdit(Event evt) throws WrongValueException,
	    InterruptedException {
	logger.info("btnEdit has been clicked!");
	openCustomerScreen(getSelectedCustomer());
    }

    public void onDoubleClickCustomer(Listitem item)
	    throws WrongValueException, InterruptedException {

	Listbox parent = (Listbox) item.getParent();
	ListModel custListModel = parent.getListModel();
	Customer customer = (Customer) custListModel.getElementAt(item
		.getIndex());
	logger.info("onDoubleClickCustomer " + customer.getAccount());
	openCustomerScreen(customer);
    }

    public void onClickCustomer(Listitem item) {
	Listbox parent = (Listbox) item.getParent();
	ListModel custListModel = parent.getListModel();
	Customer customer = (Customer) custListModel.getElementAt(item
		.getIndex());

	logger.info("onClickCustomer " + customer.getAccount());

	EventQueues.lookup(InvoicingEventQueue.CUSTOMER.getName(),
		EventQueues.DESKTOP, true).publish(
		new Event(CustomerEvent.ON_CLICK.getName(), null, customer));
    }

    private void openCustomerScreen(Customer customer)
	    throws WrongValueException, InterruptedException {
	Executions.getCurrent().getSession().setAttribute("customer", customer);

	Window win = (Window) Executions.createComponents(
		"/WEB-INF/views/customer/customer.zul", null, null);
	win.doModal();
	win.setClosable(true);
	win.setMaximizable(true);
    }

    public void onClick$btnDelete(Event evt) throws WrongValueException,
	    InterruptedException {
	logger.info("btnDelete.onClick has been clicked!");
	// reload component
    }

    public Customer getSelectedCustomer() {
	return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
	this.selectedCustomer = selectedCustomer;
    }
}
