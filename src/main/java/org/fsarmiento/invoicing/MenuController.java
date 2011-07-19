package org.fsarmiento.invoicing;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.zkoss.spring.context.annotation.*;
import org.zkoss.spring.util.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

@Controller("menuController")
@Scope("desktop")
public class MenuController extends GenericSpringComposer {

    private static Logger logger = LoggerFactory
	    .getLogger(MenuController.class);

    @Autowired
    private Button btnManageCustomers;

    @Autowired
    private Button btnListInvoices;

    @Autowired
    private Include contents;

    @EventHandler("btnManageCustomers.onClick")
    public void manageCustomers(Event evt) throws WrongValueException,
	    InterruptedException {
	replaceContentsWith("/WEB-INF/views/customer/manage_customers_view.zul");
    }

    @EventHandler("btnListInvoices.onClick")
    public void displayInvoices(Event evt) throws WrongValueException,
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
