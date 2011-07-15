package org.fsarmiento.invoicing;

import java.util.*;

import org.fsarmiento.invoicing.exception.*;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.*;
import org.springframework.transaction.annotation.*;

import com.googlecode.genericdao.search.*;
import com.googlecode.genericdao.search.hibernate.*;

/**
 * The GenericHibernateDao class.
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
    public void save(T entity) {
	getHibernateTemplate().save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(T entity) {
	getHibernateTemplate().update(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(T entity) {
	getHibernateTemplate().delete(entity);
    }

    public T getById(Long id) {
	T entity = getHibernateTemplate().get(entityClass, id);

	if (entity == null) {
	    throw new EntityNotFoundException(entityClass, "id", id);
	}

	return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> listBySearchObject(HibernateSearchObject<T> searchObject) {
	SessionFactory sessionFactory = getHibernateTemplate()
		.getSessionFactory();
	Session currentSession = sessionFactory.getCurrentSession();

	HibernateSearchProcessor hibernateSearchProcessor = HibernateSearchProcessor
		.getInstanceForSessionFactory(sessionFactory);

	return (List<T>) hibernateSearchProcessor.search(currentSession,
		searchObject);
    }

    @SuppressWarnings("unchecked")
    public SearchResult<T> getSearchResultBySearchObject(
	    HibernateSearchObject<T> searchObject) {
	SessionFactory sessionFactory = getHibernateTemplate()
		.getSessionFactory();
	Session currentSession = sessionFactory.getCurrentSession();

	HibernateSearchProcessor hibernateSearchProcessor = HibernateSearchProcessor
		.getInstanceForSessionFactory(sessionFactory);

	return hibernateSearchProcessor.searchAndCount(currentSession,
		searchObject);
    }
}
