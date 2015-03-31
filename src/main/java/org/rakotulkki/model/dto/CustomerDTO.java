package org.rakotulkki.model.dto;

import java.math.BigDecimal;

/**
 * @author jkuittin
 */
public class CustomerDTO {

	private Long id;

	private String firstName;

	private String lastName;

	private String street;

	private String city;

	private String zip;

	private Integer uninvoicedSessions;

	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(final String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(final String zip) {
		this.zip = zip;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public Integer getUninvoicedSessions() {
		return uninvoicedSessions;
	}

	public void setUninvoicedSessions(final Integer uninvoicedSessions) {
		this.uninvoicedSessions = uninvoicedSessions;
	}
}
