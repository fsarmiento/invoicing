package org.fsarmiento.invoicing.customer;

import org.fsarmiento.invoicing.entities.Customer;
import org.fsarmiento.invoicing.generics.db.GenericHibernateDao;
import org.springframework.stereotype.Repository;

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
		return getByColumnValue("account", account);
	}
}
