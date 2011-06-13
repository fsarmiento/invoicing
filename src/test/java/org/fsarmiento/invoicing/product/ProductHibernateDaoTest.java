package org.fsarmiento.invoicing.product;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.exception.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.annotation.*;

/**
 * The Class ProductHibernateDaoTest.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public class ProductHibernateDaoTest extends AbstractHibernateDaoTest {

	@Autowired
	private ProductDao productDao;

	/**
	 * Save product.
	 */
	@Test
	public void saveProduct() {
		String expProductCode = "APRODUCT";
		String expDescription = "a product description";
		String expUnitOfMeasure = "EACH";
		BigDecimal expUnitPrice = new BigDecimal("25.50");
		BigDecimal expVatRate = new BigDecimal("20.00");

		Product product = new Product();
		product.setProductCode(expProductCode);
		product.setDescription(expDescription);
		product.setUnitOfMeasure(expUnitOfMeasure);
		product.setUnitPrice(expUnitPrice);
		product.setVatRate(expVatRate);
		assertNull(product.getId());

		productDao.saveOrUpdate(product);
		assertNotNull(product.getId());

		Product actualProduct = productDao.getById(product.getId());
		assertEquals(product, actualProduct);
	}

	/**
	 * Update product.
	 */
	@Test
	public void updateProduct() {
		Product product = productDao.getById(new Long(1));
		assertThat(product.getDescription(), equalTo("test product 1"));

		String expDescription = "updated desc for product 1";
		BigDecimal expUnitPrice = new BigDecimal("35.50");

		product.setDescription(expDescription);
		product.setUnitPrice(expUnitPrice);
		productDao.saveOrUpdate(product);

		Product updatedProduct = productDao.getById(product.getId());
		assertThat(updatedProduct.getDescription(), equalTo(expDescription));
		assertThat(updatedProduct.getUnitPrice(), equalTo(expUnitPrice));
	}

	/**
	 * Delete product.
	 */
	@Test
	@ExpectedException(EntityNotFoundException.class)
	public void deleteProduct() {
		Product product = productDao.getById(new Long(1));
		assertNotNull(product);

		productDao.delete(product);

		product = productDao.getById(new Long(1));
	}

	/**
	 * Gets the by product code.
	 * 
	 * @return the by product code
	 */
	@Test
	public void getByProductCode() {
		String expProductCode = "PRODUCT3";
		Product product = productDao.getByProductCode(expProductCode);
		assertNotNull(product);
		assertThat(product.getProductCode(), equalTo(expProductCode));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fsarmiento.invoicing.entities.AbstractHibernateDaoTest#
	 * getDataSetLocations()
	 */
	@Override
	protected List<String> getDataSetLocations() {
		return Arrays.asList("product-test-data.xml");
	}
}
