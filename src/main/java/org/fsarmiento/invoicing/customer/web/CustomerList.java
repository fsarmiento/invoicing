package org.fsarmiento.invoicing.customer.web;

import java.util.*;

import org.zkoss.zul.*;
import org.zkoss.zul.event.*;

public class CustomerList extends AbstractListModel implements ListModelExt {

    private int size;

    private boolean asc = true;

    public void sort(Comparator cmpr, boolean asc) {
	this.asc = asc;
	invalidate();
    }

    public void invalidate() {
	fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
    }

    @Override
    public Object getElementAt(int arg0) {
	// TODO Auto-generated method stub
	return null;
    }

    public int getSize() {
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }

}
