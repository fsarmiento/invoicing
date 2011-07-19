package org.fsarmiento.invoicing.util;

import org.fsarmiento.invoicing.address.*;

public class EntityUtil {

    public static boolean isAddressEmpty(Address address) {
	boolean emptyAddress = false;

	if (address == null) {
	    emptyAddress = true;
	} else {
	    // BeanUtils.
	}

	return emptyAddress;
    }
}
