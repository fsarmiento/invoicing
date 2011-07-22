package org.fsarmiento.invoicing.shared.web;

import java.io.*;
import java.util.*;

import org.fsarmiento.invoicing.*;
import org.slf4j.*;
import org.zkoss.lang.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;
import org.zkoss.zul.event.*;

import com.googlecode.genericdao.search.*;

public abstract class AbstractPagedListWrapper<T extends AbstractEntity>
	extends ListModelList implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory
	    .getLogger(AbstractPagedListWrapper.class);

    // param. The listbox component
    protected Listbox _listBox;

    // param. The listboxes paging component
    protected Paging _paging;

    // param. Initial list if needed
    private List<T> _list;

    // param. Initial SearchResult if needed
    private SearchResult<T> _searchResult;

    // param. The SearchObject
    private HibernateSearchObject<T> _hibernateSearchObject;

    // paging start row
    private int _start;

    // count records a page have to fetch
    private int _pageSize;

    // count total records queried (without paging)
    private int _totalCount;

    // not used yet. so it's init to 'true'.
    private boolean _supportPaging = true;

    // not used yet. so it's init to
    private boolean _supportFilter = true;

    /**
     * Constructor. <br>
     * with an initial list.
     * 
     * @param listBox
     *            Overhanded listBox. <br>
     * @param paging
     *            Overhanded Paging component. <br>
     * @param initialList
     *            Overhanded List with initial data. <br>
     * @param searchObj
     *            Overhanded SearchObject. <br>
     */
    public AbstractPagedListWrapper(Listbox listBox, Paging paging,
	    List initialList, HibernateSearchObject<T> searchObj) {

	super(initialList);

	this._listBox = listBox;
	this._paging = paging;
	this._pageSize = _paging.getPageSize();
	this._hibernateSearchObject = searchObj;

	set_list(initialList);

	setListeners();
    }

    /**
     * Constructor. <br>
     * with an initial SearchResult for getting the totalRecordCount and the
     * list. <br>
     * 
     * @param listBox
     *            Overhanded listBox. <br>
     * @param paging
     *            Overhanded Paging component. <br>
     * @param searchResult
     *            Overhanded SearchResult Object. <br>
     * @param searchObj
     *            Overhanded SearchObject. <br>
     */
    public AbstractPagedListWrapper(Listbox listBox, Paging paging,
	    SearchResult searchResult, HibernateSearchObject searchObj) {

	super(searchResult.getResult());

	this._listBox = listBox;
	this._paging = paging;
	this._paging.setTotalSize(searchResult.getTotalCount());
	this._pageSize = _paging.getPageSize();
	this._searchResult = searchResult;
	this._hibernateSearchObject = searchObj;

	set_list(searchResult.getResult());
	set_totalCount(searchResult.getTotalCount());

	setListeners();
    }

    /**
     * Sets the listeners. <br>
     * <br>
     * 1. "onPaging" for the paging component. <br>
     * 2. "onSort" for all listheaders that have a sortDirection declared. <br>
     */
    private void setListeners() {

	// Add 'onPaging' listener to the paging component
	_paging.addEventListener("onPaging", new OnPagingEventListener());

	/*
	 * Add 'onSort' listeners to the used listheader components. All not
	 * used Listheaders must me declared as:
	 * listheader.setSortAscending(""); <br>
	 * listheader.setSortDescending(""); <br>
	 */
	Listhead listhead = _listBox.getListhead();
	List list = listhead.getChildren();

	for (Object object : list) {
	    if (object instanceof Listheader) {
		Listheader lheader = (Listheader) object;

		if (lheader.getSortAscending() != null
			|| lheader.getSortDescending() != null) {

		    if (logger.isDebugEnabled()) {
			logger.debug("--> : " + lheader.getId());
		    }
		    lheader.addEventListener("onSort",
			    new OnSortEventListener());
		}
	    }
	}

    }

    /**
     * "onPaging" eventlistener for the paging component. <br>
     * <br>
     * Calculates the next page by currentPage and pageSize values. <br>
     * Calls the methode for refreshing the data with the new rowStart and
     * pageSize. <br>
     */
    public final class OnPagingEventListener implements EventListener {
	@Override
	public void onEvent(Event event) throws Exception {

	    PagingEvent pe = (PagingEvent) event;
	    int pageNo = pe.getActivePage();
	    int start = pageNo * _pageSize;

	    if (logger.isDebugEnabled()) {
		logger.debug("--> : " + start + "/" + _pageSize);
	    }

	    // refresh the list
	    refreshModel(get_hibernateSearchObject(), start, _pageSize);
	}
    }

    /**
     * "onSort" eventlistener for the listheader components. <br>
     * <br>
     * Checks wich listheader is clicked and checks which orderDirection must be
     * set. <br>
     * 
     * Calls the methode for refreshing the data with the new ordering. and the
     * remembered rowStart and pageSize. <br>
     */
    public final class OnSortEventListener implements EventListener {
	@Override
	public void onEvent(Event event) throws Exception {
	    final Listheader lh = (Listheader) event.getTarget();
	    final String sortDirection = lh.getSortDirection();

	    if ("ascending".equals(sortDirection)) {
		final Comparator cmpr = lh.getSortDescending();
		if (cmpr instanceof FieldComparator) {
		    String orderBy = ((FieldComparator) cmpr).getOrderBy();
		    orderBy = orderBy.replace("DESC", "").trim();

		    // update SearchObject with orderBy
		    get_hibernateSearchObject().clearSorts();
		    get_hibernateSearchObject().addSort(orderBy, true);
		}
	    } else if ("descending".equals(sortDirection)
		    || "natural".equals(sortDirection)
		    || Strings.isBlank(sortDirection)) {
		final Comparator cmpr = lh.getSortAscending();
		if (cmpr instanceof FieldComparator) {
		    String orderBy = ((FieldComparator) cmpr).getOrderBy();
		    orderBy = orderBy.replace("ASC", "").trim();

		    // update SearchObject with orderBy
		    get_hibernateSearchObject().clearSorts();
		    get_hibernateSearchObject().addSort(orderBy, false);
		}
	    }

	    if (logger.isDebugEnabled()) {
		logger.debug("--> : " + lh.getId() + "/" + sortDirection);
		logger.debug("--> added  getSorts() : "
			+ get_hibernateSearchObject().getSorts().toString());
	    }

	    if (is_supportPagging()) {
		// refresh the list
		refreshModel(get_hibernateSearchObject(), 0, _pageSize);
		_paging.setActivePage(0);
	    }
	}
    }

    /**
     * Refreshes the list by calling the DAO methode with the modified search
     * object. <br>
     * 
     * @param so
     *            SearchObject, holds the entity and properties to search. <br>
     * @param start
     *            Row to start. <br>
     * @param pageSize
     *            Count rows to fetch. <br>
     */
    private void refreshModel(HibernateSearchObject<T> so, int start,
	    int pageSize) {

	// clear old data
	get_list().clear();

	// changed from original
	so.setFirstResult(start);
	so.setMaxResults(pageSize);

	List list = getSearchService().listBySearchObject(so);
	get_list().addAll((Collection) list);
	_listBox.setModel(new ListModelList(get_list()));

    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++ //
    // ++++++++++++++++ Setter/Getter ++++++++++++++++++ //
    // +++++++++++++++++++++++++++++++++++++++++++++++++ //

    public void set_supportPagging(boolean _supportPagging) {
	this._supportPaging = _supportPagging;
    }

    public boolean is_supportPagging() {
	return _supportPaging;
    }

    public void set_supportFilter(boolean _supportFilter) {
	this._supportFilter = _supportFilter;
    }

    public boolean is_supportFilter() {
	return _supportFilter;
    }

    public void set_hibernateSearchObject(
	    HibernateSearchObject<T> hibernateSearchObject) {
	this._hibernateSearchObject = hibernateSearchObject;
    }

    public HibernateSearchObject<T> get_hibernateSearchObject() {
	return _hibernateSearchObject;
    }

    public void set_totalCount(int _totalCount) {
	this._totalCount = _totalCount;
    }

    public int get_totalCount() {
	return _totalCount;
    }

    public void set_list(List<T> _list) {
	this._list = _list;
    }

    public List<T> get_list() {
	return _list;
    }

    public abstract SearchService<T> getSearchService();

}