package org.rakotulkki.model.beanio;

import org.beanio.annotation.Field;
import org.beanio.builder.Align;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author jkuittin
 */
public class PaymentRecord {
	private static final String id = "3";

	private static final int ZERO = '0';

	@Field(at = 0, length = 1, literal = id, required = true)
	private Integer recordId;

	@Field(at = 1, length = 14, padding = ZERO, keepPadding = true, required = true)
	private BigInteger account;

	@Field(at = 15, length = 6, format = "yyMMdd", required = true)
	private Date creationDate;

	@Field(at = 21, length = 6, format = "yyMMdd", required = true)
	private Date paymentDate;

	@Field(at = 27, length = 16, align = Align.LEFT, literal = "", keepPadding = true,
		   required = true)
	private String recordCode;

	@Field(at = 43, length = 20, padding = ZERO, keepPadding = true, align = Align.RIGHT, required = true)
	private BigInteger referenceCode;

	@Field(at = 63, length = 12, align = Align.LEFT, literal = "", keepPadding = true,
		   required = false)
	private String payerName;

	@Field(at = 75, length = 1, literal = "1", required = true)
	private Integer currency;

	@Field(at = 76, length = 1, literal = "", keepPadding = true, required = false)
	private String nameSource;

	@Field(at = 77, length = 10, padding = ZERO, align = Align.RIGHT, required = true)
	private BigInteger amountInCents;

	@Field(at = 87, length = 1, literal = "", keepPadding = true, required = true)
	private String paymentType;

	@Field(at = 88, length = 1, literal = "", keepPadding = true, required = false)
	private String deliveryMethod;

	@Field(at = 89, length = 1, minOccurs = 0, literal = "", keepPadding = true,
		   required = false)
	private String returnCode;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(final Integer recordId) {
		this.recordId = recordId;
	}

	public BigInteger getAccount() {
		return account;
	}

	public void setAccount(BigInteger account) {
		this.account = account;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getRecordCode() {
		return recordCode;
	}

	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}

	public BigInteger getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(BigInteger referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getNameSource() {
		return nameSource;
	}

	public void setNameSource(String nameSource) {
		this.nameSource = nameSource;
	}

	public BigInteger getAmountInCents() {
		return amountInCents;
	}

	public void setAmountInCents(BigInteger amountInCents) {
		this.amountInCents = amountInCents;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
}
