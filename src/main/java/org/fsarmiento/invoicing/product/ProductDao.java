package org.fsarmiento.invoicing.product;

import org.fsarmiento.invoicing.GenericDao;

/**
 * The Interface ProductDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface ProductDao extends GenericDao<Product> {

	Product getByProductCode(String productCode);
}
