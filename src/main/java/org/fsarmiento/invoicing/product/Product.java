package org.fsarmiento.invoicing.product;

import javax.persistence.*;

import org.fsarmiento.invoicing.AbstractProduct;
import org.hibernate.annotations.Index;

/**
 * The Class Product.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "product")
@org.hibernate.annotations.Table(appliesTo = "product", 
		indexes = { @Index(name = "product_index", columnNames = { "productCode" }) })
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "productCode", }) })
public class Product extends AbstractProduct {
}
