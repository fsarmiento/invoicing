package org.fsarmiento.invoicing.address;

import javax.persistence.*;

import org.fsarmiento.invoicing.AbstractEntity;

/**
 * The Class Address.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "address")
public class Address extends AbstractEntity {

    @Column(length = 64)
    private String address1;

    @Column(length = 64)
    private String address2;

    @Column(length = 64)
    private String city;

    @Column(length = 64)
    private String county;

    @Column(length = 8)
    private String postCode;

    @Column(length = 64)
    private String country;

    @Column(length = 20)
    private String telNo;

    @Column(length = 20)
    private String faxNo;

    /**
     * Gets the address1.
     * 
     * @return the address1
     */
    public String getAddress1() {
	return address1;
    }

    /**
     * Sets the address1.
     * 
     * @param address1
     *            the new address1
     */
    public void setAddress1(String address1) {
	this.address1 = address1;
    }

    /**
     * Gets the address2.
     * 
     * @return the address2
     */
    public String getAddress2() {
	return address2;
    }

    /**
     * Sets the address2.
     * 
     * @param address2
     *            the new address2
     */
    public void setAddress2(String address2) {
	this.address2 = address2;
    }

    /**
     * Gets the city.
     * 
     * @return the city
     */
    public String getCity() {
	return city;
    }

    /**
     * Sets the city.
     * 
     * @param city
     *            the new city
     */
    public void setCity(String city) {
	this.city = city;
    }

    /**
     * Gets the county.
     * 
     * @return the county
     */
    public String getCounty() {
	return county;
    }

    /**
     * Sets the county.
     * 
     * @param county
     *            the new county
     */
    public void setCounty(String county) {
	this.county = county;
    }

    /**
     * Gets the post code.
     * 
     * @return the post code
     */
    public String getPostCode() {
	return postCode;
    }

    /**
     * Sets the post code.
     * 
     * @param postCode
     *            the new post code
     */
    public void setPostCode(String postCode) {
	this.postCode = postCode;
    }

    /**
     * Gets the country.
     * 
     * @return the country
     */
    public String getCountry() {
	return country;
    }

    /**
     * Sets the country.
     * 
     * @param country
     *            the new country
     */
    public void setCountry(String country) {
	this.country = country;
    }

    /**
     * Gets the tel no.
     * 
     * @return the tel no
     */
    public String getTelNo() {
	return telNo;
    }

    /**
     * Sets the tel no.
     * 
     * @param telNo
     *            the new tel no
     */
    public void setTelNo(String telNo) {
	this.telNo = telNo;
    }

    /**
     * Gets the fax no.
     * 
     * @return the fax no
     */
    public String getFaxNo() {
	return faxNo;
    }

    /**
     * Sets the fax no.
     * 
     * @param faxNo
     *            the new fax no
     */
    public void setFaxNo(String faxNo) {
	this.faxNo = faxNo;
    }

}
