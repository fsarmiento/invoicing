package org.fsarmiento.invoicing.product;

import org.fsarmiento.invoicing.entities.Product;
import org.fsarmiento.invoicing.generics.db.GenericHibernateDao;
import org.springframework.stereotype.Repository;

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

	public Product getByProductCode(String productCode) {
		return getByColumnValue("productCode", productCode);
	}
}
