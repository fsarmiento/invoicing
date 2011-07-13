package org.fsarmiento.invoicing;

import java.net.*;
import java.util.*;

import javax.annotation.*;
import javax.sql.*;

import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.dbunit.operation.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.core.io.Resource;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.transaction.*;
import org.springframework.transaction.annotation.*;

/**
 * The Class AbstractHibernateDaoTest.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/test-main-context.xml",
	"/spring/test-hibernate-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager",
	defaultRollback = true)
@Transactional
public abstract class AbstractHibernateDaoTest {

    @Autowired
    @Qualifier("dataSourceNoForeignKeyCheck")
    private DataSource dataSource;

    @PostConstruct
    public void dataSetup() throws Exception {
	IDatabaseConnection conn = new DatabaseConnection(
		dataSource.getConnection());

	IDataSet dataSet = getDataSet();

	if (dataSet == null) {
	    return;
	}

	try {
	    DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet);
	} finally {
	    conn.close();
	}
    }

    private IDataSet getDataSet() throws Exception {

	List<String> dataSetLocations = getDataSetLocations();

	if (dataSetLocations == null || dataSetLocations.isEmpty()) {
	    return null;
	}

	List<IDataSet> dataSets = new ArrayList<IDataSet>();

	for (String dataSetLocation : dataSetLocations) {
	    URL url = null;

	    if (dataSetLocation.startsWith("/")) {
		Resource resource = new ClassPathResource(dataSetLocation);
		url = resource.getURL();

	    } else {
		url = getClass().getResource(dataSetLocation);
	    }

	    IDataSet dataSet = new FlatXmlDataSetBuilder().build(url);
	    dataSets.add(dataSet);
	}

	return new CompositeDataSet(
		(IDataSet[]) dataSets.toArray(new IDataSet[dataSets.size()]));
    }

    protected abstract List<String> getDataSetLocations();
}
