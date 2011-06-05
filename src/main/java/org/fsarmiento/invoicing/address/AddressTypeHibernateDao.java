package org.fsarmiento.invoicing.address;

import org.fsarmiento.invoicing.entities.AddressType;
import org.fsarmiento.invoicing.generics.db.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * The Class AddressTypeHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("addressTypeDao")
public class AddressTypeHibernateDao extends GenericHibernateDao<AddressType>
		implements AddressTypeDao {

	/**
	 * Instantiates a new address type hibernate dao.
	 */
	public AddressTypeHibernateDao() {
		super(AddressType.class);
	}

	@Override
	public AddressType getByType(String type) {
		return getByColumnValue("type", type);
	}
}
