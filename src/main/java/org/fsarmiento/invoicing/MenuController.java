package org.fsarmiento.invoicing;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.zkoss.spring.context.annotation.*;
import org.zkoss.spring.util.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

@org.springframework.stereotype.Component("menuController")
@Scope("desktop")
public class MenuController extends GenericSpringComposer {

    private static Logger logger = LoggerFactory
	    .getLogger(MenuController.class);

    @Autowired
    private Window winApp;

    @Autowired
    private Center contentContainer;

    @Autowired
    private Button btnListCustomers;

    @Autowired
    private Button btnListInvoices;

    @EventHandler("btnListCustomers.onClick")
    public void displayCustomers(Event evt) throws WrongValueException,
	    InterruptedException {
	replaceContentsWith("/WEB-INF/views/customer/customer_layout.zul");
    }

    @EventHandler("btnListInvoices.onClick")
    public void displayInvoices(Event evt) throws WrongValueException,
	    InterruptedException {
	replaceContentsWith("/WEB-INF/views/invoice/invoices.zul");
    }

    private void replaceContentsWith(String contentName) {
	removeContents();
	createContents(contentName);
    }

    private void createContents(String contentName) {
	Component comp = Executions.createComponents(contentName, null, null);
	contentContainer.appendChild(comp);
    }

    private void removeContents() {
	List<Component> contents = contentContainer.getChildren();

	if (contents != null && !contents.isEmpty()) {
	    contents.clear();
	}

	contentContainer.invalidate();
    }
}
