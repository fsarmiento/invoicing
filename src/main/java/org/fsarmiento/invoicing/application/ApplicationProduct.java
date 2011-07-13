package org.fsarmiento.invoicing.application;

import javax.persistence.*;

import org.fsarmiento.invoicing.*;
import org.hibernate.annotations.*;

/**
 * The Class ApplicationProduct.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@javax.persistence.Entity(name = "application_product")
@org.hibernate.annotations.Table(appliesTo = "application_product", indexes = { @Index(name = "application_product_index", columnNames = {
		"application_id", "productCode" }) })
@javax.persistence.Table(uniqueConstraints = { @UniqueConstraint(columnNames = {
		"application_id", "productCode", }) })
public class ApplicationProduct extends AbstractProduct {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@ForeignKey(name = "application_product_app_fk")
	private Application application;

	/**
	 * Gets the application.
	 * 
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * Sets the application.
	 * 
	 * @param application
	 *            the new application
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
}
