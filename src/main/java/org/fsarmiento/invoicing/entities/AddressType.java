package org.fsarmiento.invoicing.entities;

import javax.persistence.*;

import org.hibernate.annotations.Index;

/**
 * The Class AddressType.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@Entity(name = "address_type")
public class AddressType extends AbstractEntity {

	@Column(length = 16, unique = true)
	@Index(name = "address_type_index")
	private String type;

	@Column(length = 64)
	private String description;

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
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

}
