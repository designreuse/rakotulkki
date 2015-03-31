package org.rakotulkki.model.hibernate;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.rakotulkki.model.InvoiceStatus;

import javax.persistence.*;

/**
 * @author jkuittin
 */
@Entity
@Table(name = "invoice_audits")
public class InvoiceAudit {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id", columnDefinition = "int")
	private Invoice invoice;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(nullable = false)
	private DateTime created;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private InvoiceStatus status;

	@Column(name = "status_message")
	private String statusMessage;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(final DateTime created) {
		this.created = created;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(final InvoiceStatus status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(final String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
