package org.fsarmiento.invoicing.customer;

import org.fsarmiento.invoicing.*;

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
