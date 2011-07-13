package org.fsarmiento.invoicing.invoice;

import java.util.*;

import org.fsarmiento.invoicing.*;

/**
 * The Interface InvoiceLineDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface InvoiceLineDao extends GenericDao<InvoiceLine> {

	/**
	 * List by customer.
	 * 
	 * @param invoiceId
	 *            the invoice id
	 * @return the list
	 */
	List<InvoiceLine> listByInvoiceHeader(Long invoiceId);

}
