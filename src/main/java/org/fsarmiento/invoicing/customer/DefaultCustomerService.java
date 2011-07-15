package org.fsarmiento.invoicing.customer;

import java.util.*;

import org.fsarmiento.invoicing.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DefaultCustomerService implements CustomerService {

    private static Logger logger = LoggerFactory
	    .getLogger(DefaultCustomerService.class);

    @Autowired
    private CustomerDao customerDao;

    public void saveCustomer(Customer customer) {
	LoggerUtil.info(logger, "Saving a new customer");
	customerDao.save(customer);
    }

    public List<Customer> listCustomers() {
	// return customerDao.listAll();
	return null;
    }

    public Customer getCustomer(String account) {
	return customerDao.getByAccount(account);
    }

    public void deleteCustomer(Customer customer) {
	customerDao.delete(customer);
    }
}
