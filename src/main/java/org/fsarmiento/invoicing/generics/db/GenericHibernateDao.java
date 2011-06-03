package org.fsarmiento.invoicing.generics.db;

import java.sql.SQLException;
import java.util.List;

import org.fsarmiento.invoicing.entities.AbstractEntity;
import org.fsarmiento.invoicing.exception.EntityNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public T getById(Long id) {
		return getByColumnValue("id", id);
	}

	public T getByColumnValue(final String column, final Object value) {
		List<T> entities = listByColumnValue(column, value);
		return entities.get(0);
	}
	
	public List<T> listByColumnValue(final String column, final Object value) {

		@SuppressWarnings("unchecked")
		List<T> entities = (List<T>) getHibernateTemplate().execute(new HibernateCallback() {

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
