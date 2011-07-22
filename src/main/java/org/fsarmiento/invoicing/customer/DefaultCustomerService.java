package org.fsarmiento.invoicing.customer;

import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.address.*;
import org.fsarmiento.invoicing.exception.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;

import com.googlecode.genericdao.search.*;

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

    @Transactional(readOnly = true)
    public List<Customer> listBySearchObject(
	    HibernateSearchObject<Customer> searchObject) {
	return customerDao.listBySearchObject(searchObject);
    }

    @Transactional(readOnly = true)
    public SearchResult<Customer> getSearchResultBySearchObject(
	    HibernateSearchObject<Customer> searchObject) {
	return customerDao.getSearchResultBySearchObject(searchObject);
    }
}
