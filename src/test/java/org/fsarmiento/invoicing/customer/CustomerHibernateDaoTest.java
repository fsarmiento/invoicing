package org.fsarmiento.invoicing.customer;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.fsarmiento.invoicing.entities.*;
import org.fsarmiento.invoicing.generics.db.GenericDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CustomerHibernateDaoTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/test-main-context.xml",
		"/spring/hibernate-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CustomerHibernateDaoTest extends
		AbstractHibernateDaoTest<Customer> {

	@Autowired
	@Qualifier("customerDao")
	private CustomerDao<Customer> customerDao;

	@Test
	public void saveCustomer() {
		Customer customer = new Customer();
		customer.setName("test");
		saveEntity(customer);
	}

	@Test
	public void updateCustomer() {
		Customer customer = new Customer();
		customer.setName("test");
		saveEntity(customer);
		assertThat(customer.getName(), equalTo("test"));

		customer.setName("Changed this!");
		updateEntity(customer);

		customer = getDao().getById(customer.getId());
		assertThat(customer.getName(), equalTo("Changed this!"));
	}

	protected GenericDao<Customer> getDao() {
		return customerDao;
	}
}
