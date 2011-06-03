package org.fsarmiento.invoicing.customer;

import org.fsarmiento.invoicing.entities.Customer;
import org.fsarmiento.invoicing.generics.db.GenericDao;

/**
 * The Interface CustomerDao.
 * 
 * @param <T>
 *            the generic type
 */
public interface CustomerDao<T extends Customer> extends GenericDao<T> {
	
	Customer getByAccount(String account);
}
