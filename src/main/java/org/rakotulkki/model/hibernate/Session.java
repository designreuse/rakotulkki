package org.rakotulkki.model.hibernate;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author jkuittin
 */
@Entity
@Table(name = "sessions")
public class Session {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Long id;

	@Column
	private DateTime start;

	@Column
	private DateTime end;

	@Column
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "customer_id", columnDefinition = "int")
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public DateTime getStart() {
		return start;
	}

	public void setStart(final DateTime start) {
		this.start = start;
	}

	public DateTime getEnd() {
		return end;
	}

	public void setEnd(final DateTime end) {
		this.end = end;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}
}
