package org.fsarmiento.invoicing;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.MappedSuperclass;


/**
 * The Class AbstractUserDefined.
 * 
 * @author Florencio Sarmiento
 * @since
 */
@MappedSuperclass
public class AbstractUserDefined extends AbstractEntity {

	private String userDefString1;

	private String userDefString2;

	private String userDefString3;

	private Date userDefDate1;

	private Date userDefDate2;

	private Date userDefDate3;

	private BigDecimal userDefNum1;

	private BigDecimal userDefNum2;

	private BigDecimal userDefNum3;

	/**
	 * Gets the user def string1.
	 * 
	 * @return the user def string1
	 */
	public String getUserDefString1() {
		return userDefString1;
	}

	/**
	 * Sets the user def string1.
	 * 
	 * @param userDefString1
	 *            the new user def string1
	 */
	public void setUserDefString1(String userDefString1) {
		this.userDefString1 = userDefString1;
	}

	/**
	 * Gets the user def string2.
	 * 
	 * @return the user def string2
	 */
	public String getUserDefString2() {
		return userDefString2;
	}

	/**
	 * Sets the user def string2.
	 * 
	 * @param userDefString2
	 *            the new user def string2
	 */
	public void setUserDefString2(String userDefString2) {
		this.userDefString2 = userDefString2;
	}

	/**
	 * Gets the user def string3.
	 * 
	 * @return the user def string3
	 */
	public String getUserDefString3() {
		return userDefString3;
	}

	/**
	 * Sets the user def string3.
	 * 
	 * @param userDefString3
	 *            the new user def string3
	 */
	public void setUserDefString3(String userDefString3) {
		this.userDefString3 = userDefString3;
	}

	/**
	 * Gets the user def date1.
	 * 
	 * @return the user def date1
	 */
	public Date getUserDefDate1() {
		return userDefDate1;
	}

	/**
	 * Sets the user def date1.
	 * 
	 * @param userDefDate1
	 *            the new user def date1
	 */
	public void setUserDefDate1(Date userDefDate1) {
		this.userDefDate1 = userDefDate1;
	}

	/**
	 * Gets the user def date2.
	 * 
	 * @return the user def date2
	 */
	public Date getUserDefDate2() {
		return userDefDate2;
	}

	/**
	 * Sets the user def date2.
	 * 
	 * @param userDefDate2
	 *            the new user def date2
	 */
	public void setUserDefDate2(Date userDefDate2) {
		this.userDefDate2 = userDefDate2;
	}

	/**
	 * Gets the user def date3.
	 * 
	 * @return the user def date3
	 */
	public Date getUserDefDate3() {
		return userDefDate3;
	}

	/**
	 * Sets the user def date3.
	 * 
	 * @param userDefDate3
	 *            the new user def date3
	 */
	public void setUserDefDate3(Date userDefDate3) {
		this.userDefDate3 = userDefDate3;
	}

	/**
	 * Gets the user def num1.
	 * 
	 * @return the user def num1
	 */
	public BigDecimal getUserDefNum1() {
		return userDefNum1;
	}

	/**
	 * Sets the user def num1.
	 * 
	 * @param userDefNum1
	 *            the new user def num1
	 */
	public void setUserDefNum1(BigDecimal userDefNum1) {
		this.userDefNum1 = userDefNum1;
	}

	/**
	 * Gets the user def num2.
	 * 
	 * @return the user def num2
	 */
	public BigDecimal getUserDefNum2() {
		return userDefNum2;
	}

	/**
	 * Sets the user def num2.
	 * 
	 * @param userDefNum2
	 *            the new user def num2
	 */
	public void setUserDefNum2(BigDecimal userDefNum2) {
		this.userDefNum2 = userDefNum2;
	}

	/**
	 * Gets the user def num3.
	 * 
	 * @return the user def num3
	 */
	public BigDecimal getUserDefNum3() {
		return userDefNum3;
	}

	/**
	 * Sets the user def num3.
	 * 
	 * @param userDefNum3
	 *            the new user def num3
	 */
	public void setUserDefNum3(BigDecimal userDefNum3) {
		this.userDefNum3 = userDefNum3;
	}
}
