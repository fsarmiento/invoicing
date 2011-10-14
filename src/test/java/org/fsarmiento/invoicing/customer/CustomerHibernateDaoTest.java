package org.fsarmiento.invoicing.customer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.address.*;
import org.fsarmiento.invoicing.exception.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate3.support.*;
import org.springframework.test.annotation.*;

/**
 * The Class CustomerHibernateDaoTest.
 */
public class CustomerHibernateDaoTest extends AbstractHibernateDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void saveCustomer() {
	String name = "test1";
	String account = "testAccount";

	Customer customer = new Customer();
	customer.setLastname(name);
	customer.setAccount(account);
	customer.setStatus(CustomerStatus.ACTIVE);
	assertThat(customer.getId(), nullValue());

	customerDao.save(customer);
	assertNotNull(customer.getId());
    }

    @Test
    public void updateCustomer() {
	Customer customer = customerDao.getById(new Long(1));
	assertThat(customer.getStatus(), equalTo(CustomerStatus.ACTIVE));

	customer.setStatus(CustomerStatus.ON_HOLD);
	customerDao.update(customer);

	customer = customerDao.getById(customer.getId());
	assertThat(customer.getStatus(), equalTo(CustomerStatus.ON_HOLD));
    }

    @Test
    @ExpectedException(EntityNotFoundException.class)
    public void deleteCustomer() {
	Customer customer = customerDao.getById(new Long(1));
	assertNotNull(customer);

	customerDao.delete(customer);

	customer = customerDao.getById(new Long(1));
    }

    @Test
    @ExpectedException(EntityNotFoundException.class)
    public void getInvalidCustomer() {
	customerDao.getByAccount("invalid account");
    }

    @Test
    public void getCustomerByAccount() {
	String testAccount = "ACCOUNT1";
	Customer customer = customerDao.getByAccount(testAccount);
	assertNotNull(customer);
	assertThat(customer.getAccount(), equalTo(testAccount));
    }

    @Test
    public void listCustomerByName() {
	String nameToSearch = "duplicated";

	HibernateSearchObject<Customer> searchObject = new HibernateSearchObject(
		Customer.class);
	searchObject.addFilterEqual("lastname", nameToSearch);

	List<Customer> customers = customerDao.listBySearchObject(searchObject);
	assertNotNull(customers);

	int expSize = 2;
	assertThat(customers.size(), equalTo(expSize));

	for (Customer customer : customers) {
	    assertThat(customer.getLastname(), equalTo(nameToSearch));
	}
    }

    @Test
    public void listActiveCustomers() {
	HibernateSearchObject<Customer> searchObject = new HibernateSearchObject(
		Customer.class);
	searchObject.addFilterEqual("status", CustomerStatus.ACTIVE);

	List<Customer> customers = customerDao.listBySearchObject(searchObject);
	assertNotNull(customers);

	int expSize = 3;
	assertThat(customers.size(), equalTo(expSize));

	for (Customer customer : customers) {
	    assertThat(customer.getStatus(), equalTo(CustomerStatus.ACTIVE));
	}
    }

    @Override
    protected List<String> getDataSetLocations() {
	return Arrays.asList("customer-test-data.xml");
    }
}
