package org.fsarmiento.invoicing.invoice;

import java.sql.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.exception.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate3.*;
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.fsarmiento.invoicing.invoice.InvoiceLineDao#listByInvoiceHeader(java
     * .lang.Long)
     */
    @Override
    public List<InvoiceLine> listByInvoiceHeader(final Long invoiceId) {

	@SuppressWarnings("unchecked")
	List<InvoiceLine> entities = (List<InvoiceLine>) getHibernateTemplate()
		.execute(new HibernateCallback() {

		    public Object doInHibernate(Session session)
			    throws HibernateException, SQLException {
			return session
				.createCriteria(InvoiceLine.class)
				.createAlias("invoiceHeader", "invoiceHeader")
				.add(Restrictions.eq("invoiceHeader.id",
					invoiceId)).list();
		    }
		});

	if (entities == null || entities.isEmpty()) {
	    throw new EntityNotFoundException(InvoiceLine.class,
		    "invoiceHeader.id", invoiceId);
	}

	return entities;
    }
}
