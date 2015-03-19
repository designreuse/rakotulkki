package org.rakotulkki.model.hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jkuittin
 */
@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Long id;

	@Column(name = "archive_id")
	private String archiveId;

	@Column(name = "payment_date", nullable = false)
	private Date paymentDate;

	@Column(name = "reference_number", nullable = false)
	private String referenceNumber;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(name = "payer")
	private String payerName;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(final String archiveId) {
		this.archiveId = archiveId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(final Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(final String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(final String payerName) {
		this.payerName = payerName;
	}
}
