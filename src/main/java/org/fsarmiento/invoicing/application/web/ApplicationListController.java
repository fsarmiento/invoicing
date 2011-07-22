package org.fsarmiento.invoicing.application.web;

import java.math.*;
import java.util.*;

import org.fsarmiento.invoicing.application.*;
import org.fsarmiento.invoicing.customer.*;
import org.fsarmiento.invoicing.customer.web.*;
import org.fsarmiento.invoicing.shared.web.*;
import org.slf4j.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zul.*;

@Controller("applicationListController")
@Scope("prototype")
public class ApplicationListController extends GenericForwardComposer {

    private Logger logger = LoggerFactory
	    .getLogger(ApplicationListController.class);

    private List<Application> appList;

    private Listbox lbApplicationList;

    private Listbox lbProductListTemplate;

    private Window winApplicationList;

    @Override
    public void doBeforeComposeChildren(Component comp) throws Exception {
	logger.info("doBeforeComposeChildren() - " + this.toString());
	super.doBeforeComposeChildren(comp);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
	logger.info("doAfterCompose()" + this.toString());
	super.doAfterCompose(comp);
	registerEventListeners();
    }

    private void registerEventListeners() {
	EventQueue customerEventQueue = EventQueues
		.lookup(InvoicingEventQueue.CUSTOMER.getName());

	registerOnClickCustomerEventListener(customerEventQueue);
    }

    private void registerOnClickCustomerEventListener(
	    EventQueue customerEventQueue) {
	EventListener eventListener = (EventListener) Executions.getCurrent()
		.getDesktop().getAttribute(CustomerEvent.ON_CLICK.getName());

	if (eventListener == null) {
	    eventListener = createCustomerEventListener();

	} else if (customerEventQueue.isSubscribed(eventListener)) {
	    // we need to unregister the old listener
	    // as it's attached to the old page
	    customerEventQueue.unsubscribe(eventListener);
	    eventListener = createCustomerEventListener();
	}

	Executions.getCurrent().getDesktop()
		.setAttribute(CustomerEvent.ON_CLICK.getName(), eventListener);
	customerEventQueue.subscribe(eventListener);
    }

    private EventListener createCustomerEventListener() {
	return new EventListener() {

	    @SuppressWarnings("unchecked")
	    @Override
	    public void onEvent(Event evt) throws Exception {
		if (evt.getName().equals(CustomerEvent.ON_CLICK.getName())) {
		    Customer customer = (Customer) evt.getData();
		    createList(customer);

		    lbApplicationList.setModel(new SimpleListModel(appList));

		    List<Listitem> listItems = lbApplicationList.getItems();

		    for (Listitem listItem : listItems) {
			Application app = (Application) lbApplicationList
				.getListModel().getElementAt(
					listItem.getIndex());
			createTooltip(listItem, app);
		    }
		}
	    }
	};
    }

    private Popup createTooltip(Listitem parent, Application application) {
	Popup popup = new Popup();

	Label label = new Label("Products of " + application.getName());
	popup.appendChild(label);

	Listbox prodList = (Listbox) lbProductListTemplate.clone();
	prodList.setId(null);
	prodList.setVisible(true);

	prodList.setModel(new SimpleListModel(application
		.getApplicationProducts().toArray()));
	popup.appendChild(prodList);

	parent.setTooltip(popup);

	winApplicationList.appendChild(popup);

	return popup;
    }

    private void createList(Customer customer) {
	appList = new ArrayList<Application>();

	for (int index = 1; index <= 50; index++) {
	    Application application = new Application();
	    application.setName("App" + index);
	    application.setDescription("Application " + index + " of Customer "
		    + customer.getAccount());

	    for (int prodIndex = 1; prodIndex <= 10; prodIndex++) {
		ApplicationProduct product = new ApplicationProduct();
		product.setProductCode("App " + index + "-Prod" + prodIndex);
		product.setDescription("App " + index + " product " + prodIndex);
		product.setUnitOfMeasure("EACH");
		product.setUnitPrice(new BigDecimal(10.25));
		application.addApplicationProduct(product);
	    }

	    appList.add(application);
	}
    }

    public void onClick$btnAdd(Event evt) throws WrongValueException,
	    InterruptedException {
	logger.info("onClick$btnAdd has been clicked");
	Messagebox.show("There are "
		+ lbApplicationList.getSelectedItems().size()
		+ " selected applications");
    }
}
