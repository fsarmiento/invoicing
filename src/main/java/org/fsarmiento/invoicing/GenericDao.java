package org.fsarmiento.invoicing;

import java.util.*;

import com.googlecode.genericdao.search.*;

/**
 * The Interface GenericDao.
 * 
 * @param <T>
 *            the generic type
 * @author Florencio Sarmiento
 * @since 1.0
 */
/**
 * @author Florencio Sarmiento
 *
 * @param <T>
 */
/**
 * @author Florencio Sarmiento
 * 
 * @param <T>
 */
public interface GenericDao<T extends AbstractEntity> {

    /**
     * Save or update.
     * 
     * @param entity
     *            the entity
     */
    void save(T entity);

    /**
     * Update.
     * 
     * @param entity
     *            the entity
     */
    void update(T entity);

    /**
     * Delete.
     * 
     * @param entity
     *            the entity
     */
    void delete(T entity);

    /**
     * Gets the by id.
     * 
     * @param id
     *            the id
     * @return the by id
     */
    T getById(Long id);

    /**
     * List by search object.
     * 
     * @param searchObject
     *            the search object
     * @return the list
     */
    List<T> listBySearchObject(HibernateSearchObject<T> searchObject);

    /**
     * Gets the search result by search object.
     * 
     * @param searchObject
     *            the search object
     * @return the search result by search object
     */
    SearchResult<T> getSearchResultBySearchObject(
	    HibernateSearchObject<T> searchObject);
}
