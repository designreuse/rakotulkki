package org.rakotulkki.model.hibernate;

import javax.persistence.*;

/**
 * @author jkuittin
 */
@Entity
@Table(name = "therapists")
public class Therapist {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String phone;

	@Column
	private String street;

	@Column
	private String zip;

	@Column
	private String city;

	@Column
	private String iban;

	@Column(name = "term_days")
	private Integer termDays;

	@Column(name = "interest_percent")
	private Integer interestPercent;

	@Column(name = "business_identity_code")
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

	public String getZipAndCity() {
		return this.zip + " " + this.city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}
}
