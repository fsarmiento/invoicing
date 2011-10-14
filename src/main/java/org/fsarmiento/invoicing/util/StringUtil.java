package org.fsarmiento.invoicing.util;

/**
 * The Class StringUtil.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public final class StringUtil {

    /**
     * Checks if is null or empty.
     * 
     * @param stringToCheck
     *            the string to check
     * @return true, if is null or empty
     */
    public static boolean isNullOrEmpty(String stringToCheck) {
	if (stringToCheck == null || "".equals(stringToCheck.trim())) {
	    return true;
	}

	return false;
    }
}
