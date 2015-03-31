package org.rakotulkki.model.dto;

/**
 * @author jkuittin
 */
public class CompanyDTO {

	private Long id;

	private String name;

	private String street;

	private String zip;

	private String city;

	private String iban;

	private Integer termDays;

	private Integer interestPercent;

	private String businessIdentityCode;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(final String street) {
		this.street = street;
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

	public String getIban() {
		return iban;
	}

	public void setIban(final String iban) {
		this.iban = iban;
	}

	public Integer getTermDays() {
		return termDays;
	}

	public void setTermDays(final Integer termDays) {
		this.termDays = termDays;
	}

	public Integer getInterestPercent() {
		return interestPercent;
	}

	public void setInterestPercent(final Integer interestPercent) {
		this.interestPercent = interestPercent;
	}

	public String getBusinessIdentityCode() {
		return businessIdentityCode;
	}

	public void setBusinessIdentityCode(final String businessIdentityCode) {
		this.businessIdentityCode = businessIdentityCode;
	}
}
