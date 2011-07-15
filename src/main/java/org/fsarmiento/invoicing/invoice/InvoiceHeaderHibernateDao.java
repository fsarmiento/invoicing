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
}
