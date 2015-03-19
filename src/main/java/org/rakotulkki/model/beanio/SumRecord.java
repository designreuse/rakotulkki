package org.rakotulkki.model.beanio;

import org.beanio.annotation.Field;
import org.beanio.builder.Align;

import java.math.BigInteger;

/**
 * @author jkuittin
 */
public class SumRecord {

	private static final String id = "9";

	private static final int ZERO = '0';

	@Field(at = 0, length = 1, literal = id, required = true)
	private Integer recordId;

	@Field(at = 1, length = 6, padding = ZERO, keepPadding = true, align = Align.RIGHT, required = true)
	private BigInteger paymentCount;

	@Field(at = 6, length = 11, padding = ZERO, keepPadding = true, align = Align.RIGHT, required = true)
	private BigInteger paymentsSum;

	@Field(at = 17, length = 6, padding = ZERO, keepPadding = true, align = Align.RIGHT, required = true)
	private BigInteger correctionCount;

	@Field(at = 23, length = 11, padding = ZERO, keepPadding = true, align = Align.RIGHT, required = true)
	private BigInteger correctionsSum;

	@Field(at = 34, length = 6, padding = ZERO, keepPadding = true, align = Align.RIGHT, required = true)
	private BigInteger failureCount;

	@Field(at = 40, length = 11, padding = ZERO, keepPadding = true, align = Align.RIGHT, required = true)
	private BigInteger failuresSum;

	@Field(at = 51, length = 38, literal = "", align = Align.LEFT, keepPadding = true, required = false)
	private String reservedField;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(final Integer recordId) {
		this.recordId = recordId;
	}

	public BigInteger getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(final BigInteger paymentCount) {
		this.paymentCount = paymentCount;
	}

	public BigInteger getPaymentsSum() {
		return paymentsSum;
	}

	public void setPaymentsSum(final BigInteger paymentsSum) {
		this.paymentsSum = paymentsSum;
	}

	public BigInteger getCorrectionCount() {
		return correctionCount;
	}

	public void setCorrectionCount(final BigInteger correctionCount) {
		this.correctionCount = correctionCount;
	}

	public BigInteger getCorrectionsSum() {
		return correctionsSum;
	}

	public void setCorrectionsSum(final BigInteger correctionsSum) {
		this.correctionsSum = correctionsSum;
	}

	public BigInteger getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(final BigInteger failureCount) {
		this.failureCount = failureCount;
	}

	public BigInteger getFailuresSum() {
		return failuresSum;
	}

	public void setFailuresSum(final BigInteger failuresSum) {
		this.failuresSum = failuresSum;
	}

	public String getReservedField() {
		return reservedField;
	}

	public void setReservedField(final String reservedField) {
		this.reservedField = reservedField;
	}
}
