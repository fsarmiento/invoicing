package org.fsarmiento.invoicing.address;

import org.fsarmiento.invoicing.entities.AddressType;
import org.fsarmiento.invoicing.generics.db.GenericDao;

/**
 * The Interface AddressTypeDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface AddressTypeDao extends GenericDao<AddressType> {

	AddressType getByType(String type);
}
