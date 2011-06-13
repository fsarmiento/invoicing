package org.fsarmiento.invoicing.application;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.exception.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.annotation.*;

public class ApplicationProductHibernateDaoTest extends
		AbstractHibernateDaoTest {

	@Autowired
	private ApplicationDao applicationDao;

	@Autowired
	private ApplicationProductDao applicationProductDao;

	@Test
	public void saveApplicationProduct() {
		Application application = applicationDao.getById(new Long(3));
		assertNotNull(application);
		assertThat(application.getApplicationProducts().isEmpty(),
				equalTo(true));

		ApplicationProduct appProduct = new ApplicationProduct();
		appProduct.setProductCode("APP_PRODUCT");
		appProduct.setDescription("test description");
		appProduct.setUnitOfMeasure("EACH");
		appProduct.setUnitPrice(new BigDecimal(10));
		appProduct.setVatRate(new BigDecimal(20));
		appProduct.setApplication(application);
		assertNull(appProduct.getId());

		applicationProductDao.saveOrUpdate(appProduct);
		assertNotNull(appProduct.getId());

		ApplicationProduct savedAppProduct = applicationProductDao
				.getById(appProduct.getId());
		assertThat(savedAppProduct, equalTo(appProduct));

	}

	@Test
	public void updateApplicationProduct() {
		ApplicationProduct appProduct = applicationProductDao.getById(new Long(
				1));
		assertNotNull(appProduct);
		assertThat(appProduct.getProductCode(), equalTo("PRODUCT1"));
		assertThat(appProduct.getDescription(), equalTo("test product 1"));
		assertThat(appProduct.getUnitOfMeasure(), equalTo("EACH"));
		assertThat(appProduct.getUnitPrice(), equalTo(new BigDecimal("50.00")));
		assertThat(appProduct.getVatRate(), equalTo(new BigDecimal("20.00")));

		BigDecimal newUnitPrice = new BigDecimal("100.00");
		BigDecimal newVatRate = new BigDecimal("17.5");

		appProduct.setUnitPrice(newUnitPrice);
		appProduct.setVatRate(newVatRate);

		applicationProductDao.saveOrUpdate(appProduct);

		appProduct = applicationProductDao.getById(new Long(1));
		assertThat(appProduct.getUnitPrice(), equalTo(newUnitPrice));
		assertThat(appProduct.getVatRate(), equalTo(newVatRate));

	}

	@Test
	@ExpectedException(EntityNotFoundException.class)
	public void listByInvalidApplication() {
		List<ApplicationProduct> appProducts = applicationProductDao
				.listByApplication(new Long(-1));
	}

	@Test
	public void listByApplication() {
		List<ApplicationProduct> appProducts = applicationProductDao
				.listByApplication(new Long(1));
		assertThat(appProducts.size(), equalTo(2));
	}

	@Test
	@ExpectedException(EntityNotFoundException.class)
	public void deleteApplicationProduct() {
		ApplicationProduct appProduct = applicationProductDao.getById(new Long(
				1));
		assertNotNull(appProduct);

		applicationProductDao.delete(appProduct);
		appProduct = applicationProductDao.getById(new Long(1));
	}

	@Override
	protected List<String> getDataSetLocations() {
		return Arrays.asList("application-test-data.xml");
	}

}
