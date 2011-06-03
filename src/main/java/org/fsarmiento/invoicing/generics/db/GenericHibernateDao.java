package org.fsarmiento.invoicing.generics.db;

import java.sql.SQLException;
import java.util.List;

import org.fsarmiento.invoicing.entities.AbstractEntity;
import org.fsarmiento.invoicing.exception.EntityNotFoundException;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.*;

/**
 * The Class GenericHibernateDao.
 * 
 * @param <T>
 *            the generic type
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = EntityNotFoundException.class)
public abstract class GenericHibernateDao<T extends AbstractEntity> extends
		HibernateDaoSupport implements GenericDao<T> {

	/** The entity class. */
	private Class<T> entityClass;

	/**
	 * Instantiates a new generic hibernate dao.
	 * 
	 * @param entityClass
	 *            the entity class
	 */
	public GenericHibernateDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fsarmiento.invoicing.generics.db.GenericDao#saveOrUpdate(org.fsarmiento
	 * .invoicing.entities.AbstractEntity)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fsarmiento.invoicing.generics.db.GenericDao#delete(org.fsarmiento
	 * .invoicing.entities.AbstractEntity)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fsarmiento.invoicing.generics.db.GenericDao#getById(java.lang.Long)
	 */
	public T getById(Long id) {
		return getByColumnValue("id", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fsarmiento.invoicing.generics.db.GenericDao#getByColumnValue(java
	 * .lang.String, java.lang.Object)
	 */
	public T getByColumnValue(final String column, final Object value) {
		List<T> entities = listByColumnValue(column, value);
		return entities.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fsarmiento.invoicing.generics.db.GenericDao#listByColumnValue(java
	 * .lang.String, java.lang.Object)
	 */
	public List<T> listByColumnValue(final String column, final Object value) {

		@SuppressWarnings("unchecked")
		List<T> entities = (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createCriteria(entityClass)
								.add(Restrictions.eq(column, value)).list();
					}
				});

		if (entities == null || entities.isEmpty()) {
			throw new EntityNotFoundException(entityClass, column, value);
		}

		return entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fsarmiento.invoicing.generics.db.GenericDao#listAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		return (List<T>) getHibernateTemplate().execute(

		new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(entityClass).list();
			}
		});
	}
}
