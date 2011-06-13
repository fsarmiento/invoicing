package org.fsarmiento.invoicing.application;

import java.util.*;

import org.fsarmiento.invoicing.*;

/**
 * The Interface ApplicationProductDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public interface ApplicationProductDao extends GenericDao<ApplicationProduct> {

	/**
	 * List by application.
	 * 
	 * @param appId
	 *            the app id
	 * @return the list
	 */
	List<ApplicationProduct> listByApplication(Long appId);

}
