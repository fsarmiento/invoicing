package org.fsarmiento.invoicing.invoice;

import org.fsarmiento.invoicing.*;
import org.springframework.stereotype.*;

/**
 * The Class InvoiceLineHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("invoiceLineDao")
public class InvoiceLineHibernateDao extends GenericHibernateDao<InvoiceLine>
	implements InvoiceLineDao {

    /**
     * Instantiates a new invoice line hibernate dao.
     */
    public InvoiceLineHibernateDao() {
	super(InvoiceLine.class);
    }
}
