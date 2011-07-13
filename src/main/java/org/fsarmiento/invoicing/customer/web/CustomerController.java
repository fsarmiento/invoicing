package org.fsarmiento.invoicing.customer.web;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.zkoss.spring.context.annotation.*;
import org.zkoss.spring.util.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

@Component("customerController")
@Scope("desktop")
public class CustomerController extends GenericSpringComposer {

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private Button add;

    @EventHandler("add.onClick")
    public void onAdd(Event evt) throws InterruptedException {
	logger.info("onAdd is called");
	Messagebox.show("Hello");
    }

    public void onTest() throws InterruptedException {
	Messagebox.show("Hello using forward");
    }

}
