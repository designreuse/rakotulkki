package org.rakotulkki.model.hibernate;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

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

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "session_date")
	private LocalDate sessionDate;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	@Column(name = "start_time")
	private LocalTime startTime;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	@Column(name = "end_time")
	private LocalTime endTime;

	@Column
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "customer_id", columnDefinition = "int")
	private Customer customer;

	@OneToOne(mappedBy = "session")
	private InvoiceRow invoiceRow;

	@Column(name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
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

	public LocalDate getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(final LocalDate sessionDate) {
		this.sessionDate = sessionDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(final LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(final LocalTime endTime) {
		this.endTime = endTime;
	}

	public InvoiceRow getInvoiceRow() {
		return invoiceRow;
	}

	public void setInvoiceRow(final InvoiceRow invoiceRow) {
		this.invoiceRow = invoiceRow;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
