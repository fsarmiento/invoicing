package org.fsarmiento.invoicing.application;

import java.sql.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.exception.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate3.*;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fsarmiento.invoicing.application.ApplicationProductDao#listByApplication
	 * (java.lang.Long)
	 */
	public List<ApplicationProduct> listByApplication(final Long appId) {

		@SuppressWarnings("unchecked")
		List<ApplicationProduct> entities = (List<ApplicationProduct>) getHibernateTemplate()
				.execute(new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createCriteria(ApplicationProduct.class)
								.createAlias("application", "application")
								.add(Restrictions.eq("application.id", appId))
								.list();
					}
				});

		if (entities == null || entities.isEmpty()) {
			throw new EntityNotFoundException(ApplicationProduct.class,
					"application.id", appId);
		}

		return entities;
	}

}
