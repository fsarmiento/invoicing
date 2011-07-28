package org.fsarmiento.invoicing.customer.web;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.customer.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zul.*;

import com.googlecode.genericdao.search.*;

@Controller("homeCustomerListController")
@Scope("desktop")
public class HomeCustomerListController extends GenericForwardComposer {

    private Logger logger = LoggerFactory
	    .getLogger(HomeCustomerListController.class);

    private Listbox incCustomerListDetail$lbCustomerList;

    @Autowired
    private CustomerService customerService;

    private HibernateSearchObject<Customer> custSearchObject;

    private Paging incCustomerListDetail$customerPaging;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
	super.doAfterCompose(comp);
    }

    public void onCreate$winHomeCustomerList(Event event)
	    throws WrongValueException, InterruptedException {
	logger.info("Creating a new window: winHomeCustomerList");

	int testPageSize = 2;

	custSearchObject = new HibernateSearchObject(Customer.class);
	custSearchObject.setFirstResult(0);
	custSearchObject.setMaxResults(testPageSize);

	incCustomerListDetail$customerPaging.setPageSize(testPageSize);
	incCustomerListDetail$customerPaging.setDetailed(true);

	SearchResult searchResult = customerService
		.getSearchResultBySearchObject(custSearchObject);

	logger.info("No of results " + searchResult.getResult().size());
	logger.info("No of total count " + searchResult.getTotalCount());

	incCustomerListDetail$lbCustomerList
		.setModel(new CustomerPagedListWrapper(
			incCustomerListDetail$lbCustomerList,
			incCustomerListDetail$customerPaging, searchResult,
			custSearchObject));
    }
}
