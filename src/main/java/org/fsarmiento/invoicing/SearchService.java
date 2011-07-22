package org.fsarmiento.invoicing;

import java.util.*;

import com.googlecode.genericdao.search.*;

public interface SearchService<T extends AbstractEntity> {
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
