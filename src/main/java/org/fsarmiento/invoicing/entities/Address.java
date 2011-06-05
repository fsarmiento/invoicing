package org.fsarmiento.invoicing.entities;

import javax.persistence.*;


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
	private String address3;

	@Column(length = 64)
	private String city;

	@Column(length = 64)
	private String county;

	@Column(length = 8)
	private String postCode;

	@Column(length = 64)
	private String country;

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
	 * Gets the address3.
	 * 
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * Sets the address3.
	 * 
	 * @param address3
	 *            the new address3
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
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

}
