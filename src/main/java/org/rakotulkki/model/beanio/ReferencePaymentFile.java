package org.rakotulkki.model.beanio;

import org.beanio.annotation.Group;
import org.beanio.annotation.Record;

import java.util.List;

/**
 * @author jkuittin
 */
@Group
public class ReferencePaymentFile {

	/**
	 * Batch record
	 */
	@Record(name = "batchRecord", minOccurs = 1, maxOccurs = 1, order = 1)
	private BatchRecord batchRecord;

	/**
	 * List of payment records
	 */
	@Record(name = "paymentRecords", minOccurs = 0, maxOccurs = -1, order = 2)
	private List<PaymentRecord> paymentRecords;

	/**
	 * Sum record
	 */
	@Record(name = "sumRecord", minOccurs = 1, maxOccurs = 1, order = 3)
	private SumRecord sumRecord;

	public BatchRecord getBatchRecord() {
		return batchRecord;
	}

	public void setBatchRecord(final BatchRecord batchRecord) {
		this.batchRecord = batchRecord;
	}

	public List<PaymentRecord> getPaymentRecords() {
		return paymentRecords;
	}

	public void setPaymentRecords(final List<PaymentRecord> paymentRecords) {
		this.paymentRecords = paymentRecords;
	}

	public SumRecord getSumRecord() {
		return sumRecord;
	}

	public void setSumRecord(final SumRecord sumRecord) {
		this.sumRecord = sumRecord;
	}
}
