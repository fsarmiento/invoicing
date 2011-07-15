package org.fsarmiento.invoicing.invoice;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.math.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.exception.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.annotation.*;

public class InvoiceLineHibernateDaoTest extends AbstractHibernateDaoTest {

    @Autowired
    private InvoiceLineDao invoiceLineDao;

    @Autowired
    private InvoiceHeaderDao invoiceHeaderDao;

    @Test
    public void saveInvoiceLine() {
	InvoiceHeader invoiceHeader = invoiceHeaderDao.getById(new Long(3));
	assertNotNull(invoiceHeader);

	InvoiceLine invoiceLine = new InvoiceLine();
	invoiceLine.setProductCode("Test Product Code");
	invoiceLine.setTotalExcVat(new BigDecimal(10));
	invoiceLine.setVatValue(new BigDecimal(5));
	invoiceLine.setTotalIncVat(new BigDecimal(15));
	invoiceLine.setInvoiceHeader(invoiceHeader);
	assertNull(invoiceLine.getId());

	invoiceLineDao.save(invoiceLine);
	assertNotNull(invoiceLine.getId());

	InvoiceLine savedInvoiceLine = invoiceLineDao.getById(invoiceLine
		.getId());
	assertNotNull(savedInvoiceLine);
	assertThat(savedInvoiceLine, equalTo(invoiceLine));
    }

    @Test
    public void updateInvoiceLine() {
	InvoiceLine invoiceLine = invoiceLineDao.getById(new Long(1));
	assertThat(invoiceLine.getDescription(), equalTo("test product 1"));
	assertThat(invoiceLine.getUnitPrice().compareTo(new BigDecimal(10)),
		equalTo(0));
	assertThat(invoiceLine.getQuantity().compareTo(new BigDecimal(9)),
		equalTo(0));

	String newDescription = "Amended product 1";
	BigDecimal newUnitPrice = new BigDecimal(20);
	BigDecimal newQuantity = new BigDecimal(10);

	invoiceLine.setDescription(newDescription);
	invoiceLine.setUnitPrice(newUnitPrice);
	invoiceLine.setQuantity(newQuantity);

	invoiceLineDao.update(invoiceLine);

	invoiceLine = invoiceLineDao.getById(new Long(1));
	assertThat(invoiceLine.getDescription(), equalTo(newDescription));
	assertThat(invoiceLine.getUnitPrice().compareTo(newUnitPrice),
		equalTo(0));
	assertThat(invoiceLine.getQuantity().compareTo(newQuantity), equalTo(0));
    }

    @Test
    public void listByInvalidInvoiceHeader() {
	HibernateSearchObject<InvoiceLine> searchObject = new HibernateSearchObject(
		InvoiceLine.class);
	searchObject.addFilterEqual("invoiceHeader.id", new Long(-1));

	List<InvoiceLine> invoiceLines = invoiceLineDao
		.listBySearchObject(searchObject);
	assertThat(invoiceLines.size(), equalTo(0));
    }

    @Test
    public void listByInvoiceHeader() {
	Long invHeaderIdToSearch = new Long(1);

	HibernateSearchObject<InvoiceLine> searchObject = new HibernateSearchObject(
		InvoiceLine.class);
	searchObject.addFilterEqual("invoiceHeader.id", invHeaderIdToSearch);

	List<InvoiceLine> invoiceLines = invoiceLineDao
		.listBySearchObject(searchObject);
	assertThat(invoiceLines.size(), equalTo(2));

	for (InvoiceLine invLine : invoiceLines) {
	    InvoiceHeader header = invLine.getInvoiceHeader();
	    assertNotNull(header);
	    assertThat(header.getId(), equalTo(invHeaderIdToSearch));
	}
    }

    @Test
    @ExpectedException(EntityNotFoundException.class)
    public void delete() {
	InvoiceLine invoiceLine = invoiceLineDao.getById(new Long(1));
	assertNotNull(invoiceLine);

	invoiceLineDao.delete(invoiceLine);
	invoiceLine = invoiceLineDao.getById(new Long(1));
    }

    @Override
    protected List<String> getDataSetLocations() {
	return Arrays.asList("invoice-test-data.xml");
    }

}
