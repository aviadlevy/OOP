package oop.ex6.filescript.sections;

import oop.ex6.filescript.sections.filters.Filter;
import oop.ex6.filescript.sections.orders.Order;



public class Section {
	
	private Filter _filter;
	private Order _order;
	private int[] _warnings;
	
	/**
	 * @param filter this section filter.
	 * @param order this section order.
	 * @param warnings if Type II error happened, the line is inside the array.
	 */
	public Section(Filter filter, Order order, int[] warnings) {
		_filter = filter;
		_order = order;
		_warnings = warnings;
	}

	/**
	 * @return the _filter
	 */
	public Filter getFilter() {
		return _filter;
	}

	/**
	 * @param _filter the _filter to set
	 */
	public void setFilter(Filter _filter) {
		this._filter = _filter;
	}

	/**
	 * @return the _order
	 */
	public Order getOrder() {
		return _order;
	}

	/**
	 * @param _order the _order to set
	 */
	public void setOrder(Order _order) {
		this._order = _order;
	}

	/**
	 * @return the _warnings
	 */
	public int[] getWarnings() {
		return _warnings;
	}

	/**
	 * @param _warnings the _warnings to set
	 */
	public void setWarnings(int[] _warnings) {
		this._warnings = _warnings;
	}
}
