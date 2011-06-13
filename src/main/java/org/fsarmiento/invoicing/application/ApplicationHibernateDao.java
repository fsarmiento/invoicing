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
 * The Class ApplicationHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("applicationDao")
public class ApplicationHibernateDao extends GenericHibernateDao<Application>
		implements ApplicationDao {

	/**
	 * Instantiates a new application hibernate dao.
	 */
	public ApplicationHibernateDao() {
		super(Application.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fsarmiento.invoicing.application.ApplicationDao#listByCustomer(java
	 * .lang.String)
	 */
	public List<Application> listByCustomer(final String account) {

		@SuppressWarnings("unchecked")
		List<Application> entities = (List<Application>) getHibernateTemplate()
				.execute(new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createCriteria(Application.class)
								.createAlias("owner", "owner")
								.add(Restrictions.eq("owner.account", account))
								.list();
					}
				});

		if (entities == null || entities.isEmpty()) {
			throw new EntityNotFoundException(Application.class,
					"owner.account", account);
		}

		return entities;
	}

}
