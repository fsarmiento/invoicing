package org.fsarmiento.invoicing.customer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.fsarmiento.invoicing.entities.*;
import org.fsarmiento.invoicing.exception.EntityNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;

/**
 * The Class CustomerHibernateDaoTest.
 */
public class CustomerHibernateDaoTest extends
		AbstractHibernateDaoTest<Customer> {

	@Autowired
	private CustomerDao customerDao;

	@Test
	public void saveCustomer() {
		String name = "test1";
		String account = "testAccount";

		Customer customer = new Customer();
		customer.setName(name);
		customer.setAccount(account);
		assertThat(customer.getId(), nullValue());

		customerDao.saveOrUpdate(customer);
		assertNotNull(customer.getId());
	}

	@Test
	public void updateCustomer() {
		Customer customer = customerDao.getById(new Long(1));
		assertThat(customer.getName(), equalTo("test name1"));

		String newName = "New Name";
		customer.setName(newName);
		customerDao.saveOrUpdate(customer);

		customer = customerDao.getById(customer.getId());
		assertThat(customer.getName(), equalTo(newName));
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
		String testAccount = "test account1";
		Customer customer = customerDao.getByAccount(testAccount);
		assertNotNull(customer);
		assertThat(customer.getAccount(), equalTo(testAccount));
	}

	@Test
	public void listCustomerByName() {
		String expName = "duplicated name";
		List<Customer> customers = customerDao.listByColumnValue("name",
				expName);
		assertNotNull(customers);

		int expSize = 2;
		assertThat(customers.size(), equalTo(expSize));

		for (Customer customer : customers) {
			assertThat(customer.getName(), equalTo(expName));
		}
	}

	@Test
	public void listCustomerOnHold() {
		List<Customer> customers = customerDao.listByColumnValue("onHold",
				Boolean.TRUE);
		assertNotNull(customers);

		int expSize = 2;
		assertThat(customers.size(), equalTo(expSize));

		for (Customer customer : customers) {
			assertTrue(customer.getOnHold());
		}
	}

	@Override
	protected List<String> getDataSetLocations() {
		return Arrays.asList("customer-test-data.xml");
	}
}
