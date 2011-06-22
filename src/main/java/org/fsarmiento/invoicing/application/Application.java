package org.fsarmiento.invoicing.application;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.fsarmiento.invoicing.*;
import org.fsarmiento.invoicing.customer.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

/**
 * The Class Application.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "application")
public class Application extends AbstractEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "account", referencedColumnName = "account")
    @ForeignKey(name = "application_customer_fk")
    private Customer owner;

    @Column(length = 32)
    private String name;

    @Column(length = 64)
    private String description;

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    private Set<ApplicationProduct> applicationProducts = new HashSet<ApplicationProduct>();

    /**
     * Gets the owner.
     * 
     * @return the owner
     */
    public Customer getOwner() {
	return owner;
    }

    /**
     * Sets the owner.
     * 
     * @param owner
     *            the new owner
     */
    public void setOwner(Customer owner) {
	this.owner = owner;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Sets the description.
     * 
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Gets the application products.
     * 
     * @return the application products
     */
    public Set<ApplicationProduct> getApplicationProducts() {
	return applicationProducts;
    }

    /**
     * Sets the application products.
     * 
     * @param applicationProducts
     *            the new application products
     */
    public void setApplicationProducts(
	    Set<ApplicationProduct> applicationProducts) {
	this.applicationProducts = applicationProducts;
    }

    /**
     * Adds the application product.
     * 
     * @param applicationProduct
     *            the application product
     */
    public void addApplicationProduct(ApplicationProduct applicationProduct) {
	applicationProduct.setApplication(this);
	this.applicationProducts.add(applicationProduct);
    }

    /**
     * Removes the application product.
     * 
     * @param applicationProduct
     *            the application product
     */
    public void removeApplicationProduct(ApplicationProduct applicationProduct) {
	this.applicationProducts.remove(applicationProduct);
	applicationProduct.setApplication(null);
    }

}
