package org.rakotulkki.model.kela;

import java.util.List;

/**
 * @author jkuittin
 */
public class KU205 {

	@PdfField(fieldIndexes = { "02" })
	private String customerName;

	@PdfField(fieldIndexes = { "03" })
	private String customerSsn;

	@PdfField(fieldIndexes = { "04" })
	private String therapistName;

	@PdfField(fieldIndexes = { "05" })
	private String therapistCompanyIdentificationCode;

	@PdfField(fieldIndexes = { "06" })
	private String phone;

	@PdfField(fieldIndexes = { "07" })
	private String email;

	@PdfField(fieldIndexes = { "08" })
	private String iban;

	@PdfField(fieldIndexes = { "88" })
	private String sessionsTotal;

	@PdfField(fieldIndexes = { "89" })
	private String costTotal;

	@PdfField(fieldIndexes = { "93" })
	private String bic;

	@PdfListField(firstIndex = "10", rowLength = "3", lastIndex = "85")
	private List<Session> sessions;

	public static class Session {

		@PdfField(fieldIndexes = { "0" })
		private String date;

		@PdfField(fieldIndexes = { "1" })
		private String therapyType;

		@PdfField(fieldIndexes = { "2" })
		private String cost;

		public String getDate() {
			return date;
		}

		public void setDate(final String date) {
			this.date = date;
		}

		public String getTherapyType() {
			return therapyType;
		}

		public void setTherapyType(final String therapyType) {
			this.therapyType = therapyType;
		}

		public String getCost() {
			return cost;
		}

		public void setCost(final String cost) {
			this.cost = cost;
		}
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

	public String getTherapistName() {
		return therapistName;
	}

	public void setTherapistName(final String therapistName) {
		this.therapistName = therapistName;
	}

	public String getTherapistCompanyIdentificationCode() {
		return therapistCompanyIdentificationCode;
	}

	public void setTherapistCompanyIdentificationCode(final String therapistCompanyIdentificationCode) {
		this.therapistCompanyIdentificationCode = therapistCompanyIdentificationCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
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

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(final List<Session> sessions) {
		this.sessions = sessions;
	}
}
