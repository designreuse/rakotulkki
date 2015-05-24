package org.rakotulkki.model.dto;

import com.wordnik.swagger.annotations.ApiModel;
import org.rakotulkki.model.enums.SessionType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

	private SessionType sessionType;

	private Date startTime;

	private Date endTime;

	@NotNull
	@Min(0)
	private BigDecimal price;

	@NotNull
	private Long customerId;

	private Long invoiceRowId;

	public SessionDTO() {
		this.sessionType = SessionType.THERAPY;
	}

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

	public String getTitle() {
		return sessionType.getValue();
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

	public SessionType getSessionType() {
		return sessionType;
	}

	public void setSessionType(final SessionType sessionType) {
		this.sessionType = sessionType;
	}

	public SessionType[] getSessionTypes() {
		return SessionType.values();
	}
}
