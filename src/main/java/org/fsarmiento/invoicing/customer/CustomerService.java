package org.fsarmiento.invoicing.customer;

import java.util.*;

public interface CustomerService {

    void save(Customer customer);

    void update(Customer customer);

    List<Customer> listCustomers();

    Customer getByAccount(String account);

    void delete(Customer customer);
}
