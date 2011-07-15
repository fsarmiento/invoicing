package org.fsarmiento.invoicing;

import com.googlecode.genericdao.search.*;

/**
 * SearchObject depending on the Search Object from the Hibernate-Generic-DAO
 * framework. <br>
 * 
 * Solution adapted by Stephen Gerth using Hibernate-Generic-DAO framework. <br>
 * 
 * @see http://books.zkoss.org/wiki/Small_Talks/2009/May/
 *      Paging_Sorting_with_a_filter_object <br>
 * @see http://code.google.com/p/hibernate-generic-dao/ <br>
 * 
 * @param <T>
 *            the generic type
 * @author Florencio Sarmiento
 */
public class HibernateSearchObject<T extends AbstractEntity> extends Search {

    /**
     * Instantiates a new hibernate search object.
     * 
     * @param entityClass
     *            the entity class
     */
    public HibernateSearchObject(Class<T> entityClass) {
	super(entityClass);
    }
}
