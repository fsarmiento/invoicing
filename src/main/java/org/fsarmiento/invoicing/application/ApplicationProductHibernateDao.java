package org.fsarmiento.invoicing.application;

import org.fsarmiento.invoicing.*;
import org.springframework.stereotype.*;

/**
 * The Class ApplicationProductHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since
 */
@Repository("applicationProductDao")
public class ApplicationProductHibernateDao extends
	GenericHibernateDao<ApplicationProduct> implements
	ApplicationProductDao {

    /**
     * Instantiates a new application product hibernate dao.
     */
    public ApplicationProductHibernateDao() {
	super(ApplicationProduct.class);
    }
}
