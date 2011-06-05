package org.fsarmiento.invoicing.customer;

import org.fsarmiento.invoicing.entities.Customer;
import org.fsarmiento.invoicing.generics.db.GenericDao;

/**
 * The Interface CustomerDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface CustomerDao extends GenericDao<Customer> {

	/**
	 * Gets the by account.
	 * 
	 * @param account
	 *            the account
	 * @return the by account
	 */
	Customer getByAccount(String account);
}
