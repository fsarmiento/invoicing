package org.fsarmiento.invoicing.customer.web;

import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.application.*;
import org.fsarmiento.invoicing.customer.*;
import org.fsarmiento.invoicing.shared.web.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zul.*;

import com.googlecode.genericdao.search.*;

@Controller("customerListController")
@Scope("prototype")
public class CustomerListController extends GenericForwardComposer {

    private Logger logger = LoggerFactory
	    .getLogger(CustomerListController.class);

    private Customer selectedCustomer;

    private Listbox lbCustomerList;

    @Autowired
    private CustomerService customerService;

    private HibernateSearchObject<Customer> custSearchObject;

    private Paging customerPaging;

    private Button btnEdit;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);

	// currently doesn't work due to prototype scope
	// need to find a way round it
	registerEventListeners();
    }

    private void registerEventListeners() {
	EventQueue customerEventQueue = EventQueues
		.lookup(InvoicingEventQueue.CUSTOMER.getName());

	registerOnSelectCustomerEventListener(customerEventQueue);
    }

    private void registerOnSelectCustomerEventListener(
	    EventQueue customerEventQueue) {
	EventListener eventListener = (EventListener) Executions.getCurrent()
		.getDesktop().getAttribute(CustomerEvent.ON_SELECT.getName());

	// lbCustomerList.addEventListener("onSelect", new EventListener() {
	//
	// // @Override
	// // public void onEvent(Event event) throws Exception {
	// // event.
	// // }
	// });

	if (eventListener == null) {
	    eventListener = createCustomerEventListener();

	} else if (customerEventQueue.isSubscribed(eventListener)) {
	    // we need to unregister the old listener
	    // as it's attached to the old page
	    customerEventQueue.unsubscribe(eventListener);
	    eventListener = createCustomerEventListener();
	}

	Executions.getCurrent().getDesktop()
		.setAttribute(CustomerEvent.ON_SELECT.getName(), eventListener);
	customerEventQueue.subscribe(eventListener);
    }

    private EventListener createCustomerEventListener() {
	return new EventListener() {

	    @SuppressWarnings("unchecked")
	    @Override
	    public void onEvent(Event evt) throws Exception {
		if (evt.getName().equals(CustomerEvent.ON_SELECT.getName())) {
		    Set tmp = (Set) evt.getData();

		    logger.info("createCustomerEventListener: No of selected items = "
			    + tmp.size());

		    if (tmp.isEmpty()) {
			btnEdit.setDisabled(true);
		    } else {
			btnEdit.setDisabled(false);
		    }

		    btnEdit.invalidate();
		}
	    }
	};
    }

    public void onCreate$winCustomerList(Event event)
	    throws WrongValueException, InterruptedException {
	logger.info("Creating a new window: winCustomerList");

	int testPageSize = 2;

	custSearchObject = new HibernateSearchObject(Customer.class);
	custSearchObject.setFirstResult(0);
	custSearchObject.setMaxResults(testPageSize);

	customerPaging.setPageSize(testPageSize);
	customerPaging.setDetailed(true);

	SearchResult searchResult = customerService
		.getSearchResultBySearchObject(custSearchObject);

	logger.info("No of results " + searchResult.getResult().size());
	logger.info("No of total count " + searchResult.getTotalCount());

	lbCustomerList.setModel(new CustomerPagedListWrapper(lbCustomerList,
		customerPaging, searchResult, custSearchObject));
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
	logger.info("No of selected items = " + parent.getSelectedCount());

	ListModel custListModel = parent.getListModel();
	Customer customer = (Customer) custListModel.getElementAt(item
		.getIndex());

	EventQueue customerEventQueue = EventQueues.lookup(
		InvoicingEventQueue.CUSTOMER.getName(), true);

	customerEventQueue.publish(new Event(CustomerEvent.ON_CLICK.getName(),
		null, customer));

	// customerEventQueue.publish(new
	// Event(CustomerEvent.ON_SELECT.getName(),
	// null, parent.getSelectedItems()));
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
