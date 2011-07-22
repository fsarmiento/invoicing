package org.fsarmiento.invoicing.customer.web;

public enum CustomerEvent {

    ON_CLICK("onClick"),

    ON_SELECT("onSelect"),

    ON_DOUBLE_CLICK("onDoubleClick"),

    ON_UPDATE("onUpdate"),

    ON_SAVE("onSave"),

    ON_DELETE("onDelete");

    private String name;

    private CustomerEvent(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
}
