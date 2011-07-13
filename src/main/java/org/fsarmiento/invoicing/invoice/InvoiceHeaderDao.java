package org.fsarmiento.invoicing.invoice;

import java.util.*;

import org.fsarmiento.invoicing.*;

/**
 * The Interface InvoiceHeaderDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface InvoiceHeaderDao extends GenericDao<InvoiceHeader> {

	/**
	 * List by customer.
	 * 
	 * @param account
	 *            the account
	 * @return the list
	 */
	List<InvoiceHeader> listByCustomer(String account);

}
