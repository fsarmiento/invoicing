package org.fsarmiento.invoicing.customer;

import java.util.*;

import org.fsarmiento.invoicing.*;

public interface CustomerService extends SearchService<Customer> {

    void save(Customer customer);

    void update(Customer customer);

    List<Customer> listCustomers();

    Customer getByAccount(String account);

    void delete(Customer customer);
}
