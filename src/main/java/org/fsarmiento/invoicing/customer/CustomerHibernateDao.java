package org.fsarmiento.invoicing.customer;

import org.fsarmiento.invoicing.entities.Customer;
import org.fsarmiento.invoicing.generics.db.GenericHibernateDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
*
* @author Florencio Sarmiento
* @since 1.0
*/
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CustomerHibernateDao<T extends Customer> extends
		GenericHibernateDao<T> implements CustomerDao<T> {

	public CustomerHibernateDao(Class<T> entityClass) {
		super(entityClass);
	}
}
