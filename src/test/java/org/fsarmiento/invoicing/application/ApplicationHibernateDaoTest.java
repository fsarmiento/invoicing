package org.fsarmiento.invoicing.application;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.customer.*;
import org.fsarmiento.invoicing.exception.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.annotation.*;

public class ApplicationHibernateDaoTest extends AbstractHibernateDaoTest {

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void saveApplication() {
	Customer customer = customerDao.getById(new Long(2));
	assertNotNull(customer);

	Application application = new Application();
	application.setName("Test App1");
	application.setDescription("Test description 1");
	application.setOwner(customer);
	assertNull(application.getId());

	applicationDao.save(application);
	assertNotNull(application.getId());

	Application savedApplication = applicationDao.getById(application
		.getId());
	assertThat(savedApplication, equalTo(application));
    }

    @Test
    public void saveApplicationWithProducts() {
	Customer customer = customerDao.getById(new Long(2));
	assertNotNull(customer);

	Application application = new Application();
	application.setName("Test App1");
	application.setDescription("Test description 1");
	application.setOwner(customer);
	assertNull(application.getId());

	for (int index = 0; index < 3; index++) {
	    ApplicationProduct appProduct = new ApplicationProduct();
	    appProduct.setProductCode("APP_PRODUCT" + index);
	    appProduct.setDescription("test description " + index);
	    appProduct.setUnitOfMeasure("EACH");
	    appProduct.setUnitPrice(new BigDecimal(10));
	    appProduct.setVatRate(new BigDecimal(20));
	    application.addApplicationProduct(appProduct);
	    assertNull(appProduct.getId());
	}

	applicationDao.save(application);
	assertNotNull(application.getId());

	Application savedApplication = applicationDao.getById(application
		.getId());
	assertThat(savedApplication, equalTo(application));
	assertThat(savedApplication.getApplicationProducts().size(), equalTo(3));
    }

    @Test
    public void updateApplication() {
	Application application = applicationDao.getById(new Long(1));
	assertThat(application.getName(), equalTo("App1"));
	assertThat(application.getDescription(),
		equalTo("Application 1 by ACCOUNT1"));
	assertThat(application.getApplicationProducts().size(), equalTo(2));

	String newName = "New App1";
	String newDescription = "New Desc for App1";
	application.setName(newName);
	application.setDescription(newDescription);

	ApplicationProduct product1 = application.getApplicationProducts()
		.iterator().next();
	application.removeApplicationProduct(product1);

	applicationDao.update(application);

	application = applicationDao.getById(new Long(1));
	assertThat(application.getName(), equalTo(newName));
	assertThat(application.getDescription(), equalTo(newDescription));
	assertThat(application.getApplicationProducts().size(), equalTo(1));
    }

    @Test
    public void listByInvalidCustomer() {
	HibernateSearchObject searchObject = new HibernateSearchObject(
		Application.class);
	searchObject.addFilterEqual("owner.account", "invalid account");

	List<Application> applications = applicationDao
		.listBySearchObject(searchObject);
	assertThat(applications.size(), equalTo(0));
    }

    @Test
    public void listByCustomer() {
	String accountToSearch = "ACCOUNT1";

	HibernateSearchObject searchObject = new HibernateSearchObject(
		Application.class);
	searchObject.addFilterEqual("owner.account", accountToSearch);

	List<Application> applications = applicationDao
		.listBySearchObject(searchObject);
	assertThat(applications.size(), equalTo(2));

	for (Application application : applications) {
	    Customer owner = application.getOwner();
	    assertNotNull(owner);
	    assertThat(owner.getAccount(), equalTo(accountToSearch));
	}
    }

    @Test
    @ExpectedException(EntityNotFoundException.class)
    public void delete() {
	Application application = applicationDao.getById(new Long(2));
	assertNotNull(application);

	applicationDao.delete(application);

	application = applicationDao.getById(new Long(2));
    }

    @Override
    protected List<String> getDataSetLocations() {
	return Arrays.asList("application-test-data.xml");
    }

}
