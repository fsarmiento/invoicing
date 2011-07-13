package org.fsarmiento.invoicing.customer;

import java.util.*;

public interface CustomerService {

    void saveCustomer(Customer customer);

    List<Customer> listCustomers();

    Customer getCustomer(String account);

    void deleteCustomer(Customer customer);
}
