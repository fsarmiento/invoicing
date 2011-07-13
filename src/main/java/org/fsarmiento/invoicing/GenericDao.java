package org.fsarmiento.invoicing;

import java.util.List;


/**
 * The Interface GenericDao.
 * 
 * @param <T>
 *            the generic type
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface GenericDao<T extends AbstractEntity> {

	/**
	 * Save or update.
	 * 
	 * @param entity
	 *            the entity
	 */
	void saveOrUpdate(T entity);

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
	 * Gets the by column value.
	 * 
	 * @param column
	 *            the column
	 * @param value
	 *            the value
	 * @return the by column value
	 */
	T getByColumnValue(String column, Object value);

	/**
	 * List by column value.
	 * 
	 * @param column
	 *            the column
	 * @param value
	 *            the value
	 * @return the list
	 */
	List<T> listByColumnValue(String column, Object value);

	/**
	 * List all.
	 * 
	 * @return the list
	 */
	List<T> listAll();
}
