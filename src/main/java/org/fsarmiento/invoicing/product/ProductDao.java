package org.fsarmiento.invoicing.product;

import org.fsarmiento.invoicing.GenericDao;

/**
 * The Interface ProductDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface ProductDao extends GenericDao<Product> {

    /**
     * Gets the by product code.
     * 
     * @param productCode
     *            the product code
     * @return the by product code
     */
    Product getByProductCode(String productCode);
}
