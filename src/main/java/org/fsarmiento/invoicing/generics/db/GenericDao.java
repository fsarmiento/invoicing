package org.fsarmiento.invoicing.generics.db;

import java.util.List;

import org.fsarmiento.invoicing.entities.AbstractEntity;

/**
 *
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface GenericDao <T extends AbstractEntity> {

    void saveOrUpdate(T entity);

    void delete(T entity);    
	
	T getById(Long id);

    T getByColumnValue(String column, Object value);

    List<T> listAll();
}
