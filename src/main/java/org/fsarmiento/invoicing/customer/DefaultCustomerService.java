package org.fsarmiento.invoicing.customer;

import org.fsarmiento.invoicing.util.LoggerUtil;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DefaultCustomerService implements CustomerService {

	private static Logger logger = LoggerFactory
			.getLogger(DefaultCustomerService.class);

	@Autowired
	private CustomerDao customerDao;

	@Override
	public void saveCustomer(Customer customer) {
		LoggerUtil.info(logger, "Saving a new customer");
		customerDao.saveOrUpdate(customer);
	}
}
