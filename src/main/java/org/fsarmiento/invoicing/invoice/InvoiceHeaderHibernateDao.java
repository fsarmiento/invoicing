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
 * The Class InvoiceHeaderHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("invoiceHeaderDao")
public class InvoiceHeaderHibernateDao extends
	GenericHibernateDao<InvoiceHeader> implements InvoiceHeaderDao {

    /**
     * Instantiates a new invoice header hibernate dao.
     */
    public InvoiceHeaderHibernateDao() {
	super(InvoiceHeader.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.fsarmiento.invoicing.invoice.InvoiceHeaderDao#listByCustomer(java
     * .lang.String)
     */
    @Override
    public List<InvoiceHeader> listByCustomer(final String account) {

	@SuppressWarnings("unchecked")
	List<InvoiceHeader> entities = (List<InvoiceHeader>) getHibernateTemplate()
		.execute(new HibernateCallback() {

		    public Object doInHibernate(Session session)
			    throws HibernateException, SQLException {
			return session
				.createCriteria(InvoiceHeader.class)
				.createAlias("invoicee", "invoicee")
				.add(Restrictions.eq("invoicee.account",
					account)).list();
		    }
		});

	if (entities == null || entities.isEmpty()) {
	    throw new EntityNotFoundException(InvoiceHeader.class,
		    "invoicee.account", account);
	}

	return entities;
    }
}
