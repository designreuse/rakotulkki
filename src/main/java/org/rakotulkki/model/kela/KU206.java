package org.rakotulkki.model.kela;

import java.util.List;

/**
 * @author jkuittin
 */
public class KU206 implements PdfReport {

	private final String template = "KU206_W.pdf";

	@PdfField(fieldIndexes = { "04" })
	private String providerName;

	@PdfField(fieldIndexes = { "06" })
	private String address;

	@PdfField(fieldIndexes = { "08" })
	private String sellerName;

	@PdfField(fieldIndexes = { "10", "120" })
	private String name;

	@PdfField(fieldIndexes = { "13" })
	private String iban;

	@PdfField(fieldIndexes = { "14" })
	private String bic;

	@PdfField(fieldIndexes = { "16" })
	private String referenceNumber;

	@PdfField(fieldIndexes = { "05", "09" })
	private String companyIdentificationCode;

	@PdfField(fieldIndexes = { "07" })
	private String phone;

	@PdfListField(firstIndex = "19", rowLength = "5", lastIndex = "114")
	private List<CustomerSummary> customerSummaries;

	@PdfField(fieldIndexes = { "119" })
	private String date;

	@PdfField(fieldIndexes = { "121" })
	private String totalSum;

	@PdfField(fieldIndexes = { "03" })
	private String totalInvoices;

	@Override
	public String getPdfTemplate() {
		return template;
	}

	public static class CustomerSummary {

		@PdfField(fieldIndexes = { "0" })
		private String number;

		@PdfField(fieldIndexes = { "1" })
		private String customerName;

		@PdfField(fieldIndexes = { "2" })
		private String customerSsn;

		@PdfField(fieldIndexes = { "3" })
		private String timePeriod;

		@PdfField(fieldIndexes = { "4" })
		private String sum;

		public String getNumber() {
			return number;
		}

		public void setNumber(final String number) {
			this.number = number;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(final String customerName) {
			this.customerName = customerName;
		}

		public String getCustomerSsn() {
			return customerSsn;
		}

		public void setCustomerSsn(final String customerSsn) {
			this.customerSsn = customerSsn;
		}

		public String getTimePeriod() {
			return timePeriod;
		}

		public void setTimePeriod(final String timePeriod) {
			this.timePeriod = timePeriod;
		}

		public String getSum() {
			return sum;
		}

		public void setSum(final String sum) {
			this.sum = sum;
		}
	}

	public String getTemplate() {
		return template;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(final String providerName) {
		this.providerName = providerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(final String sellerName) {
		this.sellerName = sellerName;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(final String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(final String bic) {
		this.bic = bic;
	}

	public String getCompanyIdentificationCode() {
		return companyIdentificationCode;
	}

	public void setCompanyIdentificationCode(final String companyIdentificationCode) {
		this.companyIdentificationCode = companyIdentificationCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public List<CustomerSummary> getCustomerSummaries() {
		return customerSummaries;
	}

	public void setCustomerSummaries(final List<CustomerSummary> customerSummaries) {
		this.customerSummaries = customerSummaries;
	}

	public String getDate() {
		return date;
	}

	public void setDate(final String date) {
		this.date = date;
	}

	public String getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(final String totalSum) {
		this.totalSum = totalSum;
	}

	public String getTotalInvoices() {
		return totalInvoices;
	}

	public void setTotalInvoices(final String totalInvoices) {
		this.totalInvoices = totalInvoices;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(final String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
}
