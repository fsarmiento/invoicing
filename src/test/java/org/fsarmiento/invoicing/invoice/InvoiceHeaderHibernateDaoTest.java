package org.fsarmiento.invoicing.invoice;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.customer.*;
import org.fsarmiento.invoicing.exception.*;
import org.fsarmiento.invoicing.invoice.InvoiceHeader.InvoiceStatus;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.annotation.*;

public class InvoiceHeaderHibernateDaoTest extends AbstractHibernateDaoTest {

    @Autowired
    private InvoiceHeaderDao invoiceHeaderDao;

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void saveInvoiceHeader() {
	Customer invoicee = customerDao.getById(new Long(3));

	Date now = new Date();
	InvoiceHeader invoice = new InvoiceHeader();
	invoice.setComments("Test invoice on " + now);
	invoice.setCurrency(Currency.getInstance(Locale.UK));
	invoice.setInvoiceDate(now);
	invoice.setInvoicee(invoicee);
	invoice.setNotes("Test note invoice");
	invoice.setStatus(InvoiceStatus.PAID);
	invoice.setTotalExcVat(new BigDecimal(100));
	invoice.setTotalVat(new BigDecimal(20));
	invoice.setTotalIncVat(new BigDecimal(120));

	assertNull(invoice.getId());

	invoiceHeaderDao.saveOrUpdate(invoice);
	assertNotNull(invoice.getId());

	InvoiceHeader savedInvoice = invoiceHeaderDao.getById(invoice.getId());
	assertThat(savedInvoice, equalTo(invoice));
    }

    @Test
    public void saveInvoiceHeaderWithInvoiceLines() {
	Customer invoicee = customerDao.getById(new Long(3));

	Date now = new Date();
	InvoiceHeader invoice = new InvoiceHeader();
	invoice.setComments("Test invoice on " + now);
	invoice.setCurrency(Currency.getInstance(Locale.UK));
	invoice.setInvoiceDate(now);
	invoice.setInvoicee(invoicee);
	invoice.setNotes("Test note invoice");
	invoice.setStatus(InvoiceStatus.PAID);
	invoice.setTotalExcVat(new BigDecimal(100));
	invoice.setTotalVat(new BigDecimal(20));
	invoice.setTotalIncVat(new BigDecimal(120));

	assertNull(invoice.getId());

	int noOfInvLines = 3;

	for (int index = 0; index < noOfInvLines; index++) {
	    InvoiceLine invLine = new InvoiceLine();
	    invLine.setProductCode("TEST_PROD" + index);
	    invoice.addInvoiceLine(invLine);
	    assertNull(invLine.getId());
	}

	invoiceHeaderDao.saveOrUpdate(invoice);
	assertNotNull(invoice.getId());

	InvoiceHeader savedInvoice = invoiceHeaderDao.getById(invoice.getId());
	assertThat(savedInvoice, equalTo(invoice));
	assertThat(savedInvoice.getInvoiceLines().size(), equalTo(noOfInvLines));
    }

    @Test
    public void updateInvoiceHeader() {
	InvoiceHeader invoiceHeader = invoiceHeaderDao.getById(new Long(1));
	assertThat(invoiceHeader.getNotes(), equalTo("test note"));
	assertThat(invoiceHeader.getComments(), equalTo("test comments"));
	assertThat(invoiceHeader.getTotalIncVat()
		.compareTo(new BigDecimal(180)), equalTo(0));
	assertThat(invoiceHeader.getStatus(), equalTo(InvoiceStatus.HOLD));

	String newNote = "Amended note";
	String newComment = "Amended comment";
	BigDecimal newTotal = new BigDecimal(200);

	invoiceHeader.setNotes(newNote);
	invoiceHeader.setComments(newComment);
	invoiceHeader.setTotalIncVat(newTotal);
	invoiceHeader.setStatus(InvoiceStatus.APPROVED);

	invoiceHeaderDao.saveOrUpdate(invoiceHeader);

	invoiceHeader = invoiceHeaderDao.getById(new Long(1));
	assertThat(invoiceHeader.getNotes(), equalTo(newNote));
	assertThat(invoiceHeader.getComments(), equalTo(newComment));
	assertThat(invoiceHeader.getTotalIncVat().compareTo(newTotal),
		equalTo(0));
	assertThat(invoiceHeader.getStatus(), equalTo(InvoiceStatus.APPROVED));
    }

    @Test
    public void removeInvoiceLineFromInvoiceHeader() {
	InvoiceHeader invoiceHeader = invoiceHeaderDao.getById(new Long(1));
	assertThat(invoiceHeader.getInvoiceLines().size(), equalTo(2));

	InvoiceLine invLineToRemove = invoiceHeader.getInvoiceLines()
		.iterator().next();

	invoiceHeader.removeInvoiceLine(invLineToRemove);
	invoiceHeaderDao.saveOrUpdate(invoiceHeader);

	invoiceHeader = invoiceHeaderDao.getById(new Long(1));
	assertThat(invoiceHeader.getInvoiceLines().size(), equalTo(1));
    }

    @Test
    @ExpectedException(EntityNotFoundException.class)
    public void listByInvalidCustomer() {
	List<InvoiceHeader> invoiceHeaders = invoiceHeaderDao
		.listByCustomer("invalid customer");
    }

    @Test
    public void listByCustomer() {
	List<InvoiceHeader> invoiceHeaders = invoiceHeaderDao
		.listByCustomer("ACCOUNT1");
	assertThat(invoiceHeaders.size(), equalTo(2));
    }

    @Test
    @ExpectedException(EntityNotFoundException.class)
    public void delete() {
	InvoiceHeader invoiceHeader = invoiceHeaderDao.getById(new Long(1));
	assertNotNull(invoiceHeader);

	invoiceHeaderDao.delete(invoiceHeader);
	invoiceHeader = invoiceHeaderDao.getById(new Long(1));
    }

    @Override
    protected List<String> getDataSetLocations() {
	return Arrays.asList("invoice-test-data.xml");
    }
}
