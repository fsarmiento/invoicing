package org.fsarmiento.invoicing.shared.web;

public enum InvoicingEventQueue {

    CUSTOMER("customerEventQueue"),

    APPLICATION("applicationEventQueue"),

    APPLICATION_PRODUCT("applicationProductEventQueue"),

    INVOICE("invoiceEventQueue");

    private String name;

    private InvoicingEventQueue(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
}
