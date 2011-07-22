package org.fsarmiento.invoicing.menu.web;

import org.slf4j.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zul.*;

@Controller("menuController")
@Scope("desktop")
public class MenuController extends GenericForwardComposer {

    private static Logger logger = LoggerFactory
	    .getLogger(MenuController.class);

    private Include contents;

    public void onClick$btnManageCustomers(Event evt)
	    throws WrongValueException, InterruptedException {
	replaceContentsWith("/WEB-INF/views/customer/manage_customers_view.zul");
    }

    public void onClick$btnListInvoices(Event evt) throws WrongValueException,
	    InterruptedException {
	replaceContentsWith("/WEB-INF/views/invoice/invoices.zul");
    }

    private void replaceContentsWith(String contentName) {
	contents.setProgressing(true);
	contents.setSrc(null);
	contents.invalidate();
	contents.setSrc(contentName);
	contents.setProgressing(false);
    }
}
