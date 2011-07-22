package org.fsarmiento.invoicing.customer.web;

import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.customer.*;
import org.fsarmiento.invoicing.shared.web.*;
import org.zkoss.zkplus.spring.*;
import org.zkoss.zul.*;

import com.googlecode.genericdao.search.*;

public class CustomerPagedListWrapper extends
	AbstractPagedListWrapper<Customer> {

    public CustomerPagedListWrapper(Listbox listBox, Paging paging,
	    List initialList, HibernateSearchObject<Customer> searchObj) {
	super(listBox, paging, initialList, searchObj);
    }

    public CustomerPagedListWrapper(Listbox listBox, Paging paging,
	    SearchResult searchResult, HibernateSearchObject searchObj) {
	super(listBox, paging, searchResult, searchObj);
    }

    @Override
    public SearchService<Customer> getSearchService() {
	return (SearchService) SpringUtil.getBean("defaultCustomerService");
    }

}
