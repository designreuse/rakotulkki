package org.rakotulkki.model.dto;

import com.wordnik.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * @author jkuittin
 */
@ApiModel(description = "Model fo customer")
@XmlRootElement
public class CustomerDTO {

	private Long id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String street;

	@NotNull
	private String city;

	@NotNull
	private String zip;

	@NotNull
	private String ssn;

	private Integer uninvoicedSessions;

	private BigDecimal price;

	private BigDecimal compensationAmount;

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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(final String ssn) {
		this.ssn = ssn;
	}

	public BigDecimal getCompensationAmount() {
		return compensationAmount;
	}

	public void setCompensationAmount(final BigDecimal compensationAmount) {
		this.compensationAmount = compensationAmount;
	}
}
