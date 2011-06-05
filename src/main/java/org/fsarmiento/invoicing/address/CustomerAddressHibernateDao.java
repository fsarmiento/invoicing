package org.fsarmiento.invoicing.address;

import org.fsarmiento.invoicing.entities.CustomerAddress;
import org.fsarmiento.invoicing.generics.db.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * The Class CustomerAddressHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("customerAddressDao")
public class CustomerAddressHibernateDao extends
		GenericHibernateDao<CustomerAddress> implements CustomerAddressDao {

	/**
	 * Instantiates a new customer address hibernate dao.
	 */
	public CustomerAddressHibernateDao() {
		super(CustomerAddress.class);
	}

}
