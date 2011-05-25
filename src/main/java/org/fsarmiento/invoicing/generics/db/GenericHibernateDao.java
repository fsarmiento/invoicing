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
 *
 * @author Florencio Sarmiento
 * @since 1.0
 */
public abstract class GenericHibernateDao<T extends AbstractEntity> extends HibernateDaoSupport
        implements GenericDao<T> {

    private Class<T> entityClass;

    public GenericHibernateDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }
    
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=EntityNotFoundException.class)
    public T getById(Long id) {
    	return getByColumnValue("id", id);
    }
    
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=EntityNotFoundException.class)
    public T getByColumnValue(final String column, final Object value) {
        T entity = (T) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                return session.createCriteria(entityClass).add(Restrictions.eq(column, value)).uniqueResult();
            }
        });

        if (entity == null) {
            throw new EntityNotFoundException(entityClass, column, value);
        }

        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> listAll() {
        return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                return session.createCriteria(entityClass).list();
            }
        });
    }
}
