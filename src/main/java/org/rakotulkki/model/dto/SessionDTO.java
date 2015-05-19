package org.rakotulkki.model.dto;

import com.wordnik.swagger.annotations.ApiModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jkuittin
 */
@ApiModel(description = "Model fo session")
@XmlRootElement
public class SessionDTO {

	private Long id;

	private Date sessionDate;

	private Date startTime;

	private Date endTime;

	private BigDecimal price;

	private Long customerId;

	private Long invoiceRowId;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(final Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	public Long getInvoiceRowId() {
		return invoiceRowId;
	}

	public void setInvoiceRowId(final Long invoiceRowId) {
		this.invoiceRowId = invoiceRowId;
	}
}
