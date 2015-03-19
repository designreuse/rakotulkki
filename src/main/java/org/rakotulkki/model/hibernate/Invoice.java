package org.rakotulkki.model.hibernate;

import org.rakotulkki.model.InvoiceStatus;

import javax.persistence.*;
import java.util.Date;

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

	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	@ManyToOne
	@JoinColumn(name = "customer_id", columnDefinition = "int")
	private Customer customer;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
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

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(final Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(final Date dueDate) {
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

}
