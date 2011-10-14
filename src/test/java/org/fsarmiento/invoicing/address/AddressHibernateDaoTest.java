package org.fsarmiento.invoicing.address;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.customer.*;
import org.fsarmiento.invoicing.exception.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.annotation.*;

public class AddressHibernateDaoTest extends AbstractHibernateDaoTest {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void saveAddress() {
	Address address = new Address();
	address.setAddress1("test address1");
	address.setAddress2("test address2");
	address.setCity("test city");
	address.setCounty("test county");
	address.setPostCode("AB12 3CF");
	address.setCountry("UK");
	address.setTelNo("01234567891");
	address.setType(AddressType.SHIPPING);
	assertNull(address.getId());

	String testAccount = "ACCOUNT2";
	Customer customer = customerDao.getByAccount(testAccount);
	address.setOwner(customer);

	addressDao.save(address);
	assertNotNull(address.getId());

	Address savedAddress = addressDao.getById(address.getId());
	assertThat(savedAddress, equalTo(address));
	assertThat(savedAddress.getOwner(), equalTo(customer));
    }

    @Test
    public void updateAddress() {
	Address address = addressDao.getById(new Long(1));
	assertThat(address.getAddress1(), equalTo("1 Test Road"));
	assertThat(address.getCity(), equalTo("No Bug City"));

	String newAddress1 = "2 Success Road";
	String newCity = "Bugless City";
	address.setAddress1(newAddress1);
	address.setCity(newCity);

	addressDao.update(address);

	address = addressDao.getById(new Long(1));
	assertThat(address.getAddress1(), equalTo(newAddress1));
	assertThat(address.getCity(), equalTo(newCity));
    }

    @Test
    @ExpectedException(EntityNotFoundException.class)
    public void deleteAddress() {
	Address address = addressDao.getById(new Long(1));
	assertNotNull(address);

	addressDao.delete(address);

	address = addressDao.getById(new Long(1));
    }

    @Test
    public void listAddressesInUK() {
	HibernateSearchObject<Address> searchObject = new HibernateSearchObject(
		Address.class);

	String uk = "United Kingdom";

	searchObject.addFilterEqual("country", uk);
	List<Address> addressList = addressDao.listBySearchObject(searchObject);
	assertNotNull(addressList);
	assertThat(addressList.size(), equalTo(2));

	for (Address address : addressList) {
	    assertThat(address.getCountry(), equalTo(uk));
	}
    }

    @Override
    protected List<String> getDataSetLocations() {
	return Arrays.asList("address-test-data.xml");
    }

}
