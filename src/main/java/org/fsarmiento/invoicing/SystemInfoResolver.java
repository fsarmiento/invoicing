package org.fsarmiento.invoicing;

import org.zkoss.xel.*;

public class SystemInfoResolver implements VariableResolver {

    @Override
    public Object resolveVariable(String name) throws XelException {
	if ("systemName".equals(name)) {
	    return "Invoicing System";
	} else if ("systemVersion".equals(name)) {
	    return "1.0b";
	}

	return null;
    }

}
