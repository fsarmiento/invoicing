package org.fsarmiento.invoicing.product;

import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.exception.*;
import org.springframework.stereotype.*;

/**
 * The Class ProductHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("productDao")
public class ProductHibernateDao extends GenericHibernateDao<Product> implements
	ProductDao {

    /**
     * Instantiates a new customer hibernate dao.
     * 
     */
    public ProductHibernateDao() {
	super(Product.class);
    }

    @Override
    public Product getByProductCode(String productCode) {
	HibernateSearchObject<Product> searchObject = new HibernateSearchObject(
		Product.class);
	searchObject.addFilterEqual("productCode", productCode);

	List<Product> products = listBySearchObject(searchObject);

	if (products.isEmpty()) {
	    throw new EntityNotFoundException(Product.class, "productCode",
		    productCode);
	}

	return products.get(0);
    }
}
