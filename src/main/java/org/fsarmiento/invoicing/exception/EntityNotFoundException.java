package org.fsarmiento.invoicing.exception;

/**
 *
 * @author Florencio Sarmiento
 * @since 1.0
 */
public class EntityNotFoundException extends RuntimeException {
    private String message;

    @SuppressWarnings("unchecked")
    public EntityNotFoundException(Class clazz, String field,
            Object param) {
        setMessage(clazz, field, param);
    }

    @SuppressWarnings("unchecked")
    public EntityNotFoundException(Class clazz, String field,
            Object param, Exception cause) {
        super(cause);
        setMessage(clazz, field, param);
    }

    private void setMessage(Class clazz, String field, Object param) {
        Object tmpParam = param != null ? param : "NULL";
        message = new StringBuilder("Cannot find ")
                .append(clazz.getName())
                .append(" for ")
                .append(clazz.getSimpleName())
                .append(".")
                .append(field).append(" = ")
                .append(tmpParam).toString();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
