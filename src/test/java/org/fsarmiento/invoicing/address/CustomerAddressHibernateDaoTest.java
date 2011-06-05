package org.fsarmiento.invoicing.address;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.*;

import org.fsarmiento.invoicing.customer.CustomerDao;
import org.fsarmiento.invoicing.entities.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Class CustomerAddressHibernateDaoTest.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public class CustomerAddressHibernateDaoTest extends
		AbstractHibernateDaoTest<Product> {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private AddressTypeDao addressTypeDao;

	@Autowired
	private CustomerAddressDao customerAddressDao;

	/**
	 * Save customer address.
	 */
	@Test
	public void saveCustomerAddress() {
		Customer customer = customerDao.getByAccount("ACCOUNT2");
		assertNotNull(customer);

		Address address = addressDao.getById(new Long(2));
		assertNotNull(address);

		AddressType addressType = addressTypeDao.getByType("BILLING");
		assertNotNull(addressType);

		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setAddress(address);
		customerAddress.setCustomer(customer);
		customerAddress.setAddressType(addressType);
		customerAddress.setDateFrom(new Date());
		customerAddress.setLastUpdated(new Date());
		assertNull(customerAddress.getId());

		customerAddressDao.saveOrUpdate(customerAddress);
		assertNotNull(customerAddress.getId());

		CustomerAddress savedCustomerAddress = customerAddressDao
				.getById(customerAddress.getId());
		assertThat(savedCustomerAddress, equalTo(customerAddress));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fsarmiento.invoicing.entities.AbstractHibernateDaoTest#
	 * getDataSetLocations()
	 */
	@Override
	protected List<String> getDataSetLocations() {
		return Arrays.asList("address-test-data.xml",
				"customer-address-test-data.xml");
	}
}
