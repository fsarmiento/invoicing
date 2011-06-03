package org.fsarmiento.invoicing.exception;

/**
 * The Class EntityNotFoundException.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public class EntityNotFoundException extends RuntimeException {

	private String message;

	/**
	 * Instantiates a new entity not found exception.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param field
	 *            the field
	 * @param param
	 *            the param
	 */
	@SuppressWarnings("unchecked")
	public EntityNotFoundException(Class clazz, String field, Object param) {
		setMessage(clazz, field, param);
	}

	/**
	 * Instantiates a new entity not found exception.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param field
	 *            the field
	 * @param param
	 *            the param
	 * @param cause
	 *            the cause
	 */
	@SuppressWarnings("unchecked")
	public EntityNotFoundException(Class clazz, String field, Object param,
			Exception cause) {
		super(cause);
		setMessage(clazz, field, param);
	}

	/**
	 * Sets the message.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param field
	 *            the field
	 * @param param
	 *            the param
	 */
	private void setMessage(Class clazz, String field, Object param) {
		Object tmpParam = param != null ? param : "NULL";
		message = new StringBuilder("Cannot find ").append(clazz.getName())
				.append(" for ").append(clazz.getSimpleName()).append(".")
				.append(field).append(" = ").append(tmpParam).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return message;
	}
}
