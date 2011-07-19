package org.fsarmiento.invoicing.customer;

import java.util.*;

import org.fsarmiento.invoicing.address.*;
import org.fsarmiento.invoicing.exception.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;

@Service
@Transactional
public class DefaultCustomerService implements CustomerService {

    private static Logger logger = LoggerFactory
	    .getLogger(DefaultCustomerService.class);

    @Autowired
    private CustomerDao customerDao;

    public void save(Customer customer) {
	customerDao.save(customer);
    }

    public void update(Customer customer) {
	customerDao.update(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> listCustomers() {
	// return customerDao.listAll();
	return null;
    }

    @Transactional(readOnly = true, noRollbackFor = EntityNotFoundException.class)
    public Customer getByAccount(String account) {
	return customerDao.getByAccount(account);
    }

    public void delete(Customer customer) {
	customerDao.delete(customer);
    }
}
