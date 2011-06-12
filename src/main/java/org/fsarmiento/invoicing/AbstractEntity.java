package org.fsarmiento.invoicing;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The Class AbstractEntity.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	private void setId(Long id) {
		this.id = id;
	}
}
