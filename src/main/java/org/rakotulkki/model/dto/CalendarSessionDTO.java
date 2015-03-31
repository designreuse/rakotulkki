package org.rakotulkki.model.dto;

/**
 * @author jkuittin
 */
public class CalendarSessionDTO {

	private Long id;

	private String name;

	private Long customerId;

	private String customerName;

	private String start;

	private String end;

	private boolean recurring;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	public String getStart() {
		return start;
	}

	public void setStart(final String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(final String end) {
		this.end = end;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(final boolean recurring) {
		this.recurring = recurring;
	}

	public String getTitle() {
		if (name != null) {
			return name + " / " + customerName;
		}
		return customerName;
	}
}
