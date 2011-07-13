package org.fsarmiento.invoicing.application;

import java.util.*;

import org.fsarmiento.invoicing.*;

/**
 * The Interface ApplicationDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface ApplicationDao extends GenericDao<Application> {

	/**
	 * List by customer.
	 * 
	 * @param account
	 *            the account
	 * @return the list
	 */
	List<Application> listByCustomer(String account);

}
