package org.fsarmiento.invoicing.customer;

import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.exception.*;
import org.springframework.stereotype.*;

/**
 * The Class CustomerHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("customerDao")
public class CustomerHibernateDao extends GenericHibernateDao<Customer>
	implements CustomerDao {

    /**
     * Instantiates a new customer hibernate dao.
     * 
     */
    public CustomerHibernateDao() {
	super(Customer.class);
    }

    public Customer getByAccount(String account) {
	HibernateSearchObject searchObject = new HibernateSearchObject(
		Customer.class);
	searchObject.addFilterEqual("account", account);

	List<Customer> customers = listBySearchObject(searchObject);

	if (customers.isEmpty()) {
	    throw new EntityNotFoundException(Customer.class, "account",
		    account);
	}

	return customers.get(0);
    }
}
