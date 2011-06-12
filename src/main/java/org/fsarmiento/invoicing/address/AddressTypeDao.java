package org.fsarmiento.invoicing.address;

import org.fsarmiento.invoicing.GenericDao;

/**
 * The Interface AddressTypeDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface AddressTypeDao extends GenericDao<AddressType> {

	AddressType getByType(String type);
}
