package org.rakotulkki.model.hibernate;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.rakotulkki.model.enums.InvoiceStatus;
import org.rakotulkki.model.enums.InvoiceType;

import javax.persistence.*;
import java.util.List;

/**
 * @author jkuittin
 */
@Entity
@Table(name = "invoices")
public class Invoice {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Long id;

	@Column(name = "invoice_number")
	private Long invoiceNumber;

	@Column(name = "customer_number")
	private String customerNumber;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "invoice_date")
	private LocalDate invoiceDate;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "due_date")
	private LocalDate dueDate;

	@ManyToOne
	@JoinColumn(name = "customer_id", columnDefinition = "int")
	private Customer customer;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, name = "street")
	private String address;

	@Column(nullable = false)
	private String zip;

	@Column(nullable = false)
	private String city;

	@Column(name = "reference_number", nullable = false)
	private String referenceNumber;

	@Column(name = "extra_text")
	private String extraText;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private InvoiceStatus status;

	@Column(name = "invoice_type")
	@Enumerated(EnumType.STRING)
	private InvoiceType invoiceType;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
	private List<InvoiceRow> rows;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "created")
	private DateTime created;

	@ManyToOne
	@JoinColumn(name = "therapist_id", columnDefinition = "int")
	private Therapist therapist;

	public Therapist getTherapist() {
		return therapist;
	}

	public void setTherapist(final Therapist therapist) {
		this.therapist = therapist;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(final Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(final String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(final LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(final LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(final String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(final String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getExtraText() {
		return extraText;
	}

	public void setExtraText(final String extraText) {
		this.extraText = extraText;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(final InvoiceStatus status) {
		this.status = status;
	}

	public InvoiceType getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(final InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	public List<InvoiceRow> getRows() {
		return rows;
	}

	public void setRows(final List<InvoiceRow> rows) {
		this.rows = rows;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(final DateTime created) {
		this.created = created;
	}
}
