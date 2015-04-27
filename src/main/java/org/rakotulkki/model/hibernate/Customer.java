package org.rakotulkki.model.hibernate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author jkuittin
 */
@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int")
	private Long id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String street;

	@Column
	private String city;

	@Column
	private String zip;

	@Column
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "therapist_id", columnDefinition = "int")
	private Therapist therapist;

	public Therapist getTherapist() {
		return therapist;
	}

	public void setTherapist(final Therapist therapist) {
		this.therapist = therapist;
	}

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
}
