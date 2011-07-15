package org.fsarmiento.invoicing.application;

import java.sql.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.exception.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate3.*;
import org.springframework.stereotype.*;

/**
 * The Class ApplicationHibernateDao.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Repository("applicationDao")
public class ApplicationHibernateDao extends GenericHibernateDao<Application>
	implements ApplicationDao {

    /**
     * Instantiates a new application hibernate dao.
     */
    public ApplicationHibernateDao() {
	super(Application.class);
    }
}
