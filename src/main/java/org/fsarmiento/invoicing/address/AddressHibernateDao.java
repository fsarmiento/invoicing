package org.fsarmiento.invoicing.address;

import org.fsarmiento.invoicing.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * The Class AddressHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("addressDao")
public class AddressHibernateDao extends GenericHibernateDao<Address> implements
		AddressDao {

	/**
	 * Instantiates a new address hibernate dao.
	 */
	public AddressHibernateDao() {
		super(Address.class);
	}

}
