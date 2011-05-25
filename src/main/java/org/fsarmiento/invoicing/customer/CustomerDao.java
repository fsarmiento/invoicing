package org.fsarmiento.invoicing.customer;

import org.fsarmiento.invoicing.entities.Customer;
import org.fsarmiento.invoicing.generics.db.GenericDao;

public interface CustomerDao<T extends Customer> extends GenericDao<T>{

}
