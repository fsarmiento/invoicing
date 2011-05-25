package org.fsarmiento.invoicing.customer;

import org.fsarmiento.invoicing.entities.Customer;
import org.fsarmiento.invoicing.generics.db.GenericHibernateDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CustomerHibernateDao.
 * 
 * @param <T>
 *            the generic type
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CustomerHibernateDao<T extends Customer> extends
		GenericHibernateDao<T> implements CustomerDao<T> {

	/**
	 * Instantiates a new customer hibernate dao.
	 * 
	 * @param entityClass
	 *            the entity class
	 */
	public CustomerHibernateDao(Class<T> entityClass) {
		super(entityClass);
	}
}
