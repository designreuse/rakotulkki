package org.rakotulkki.model.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jkuittin
 */
@XmlRootElement
public class InvoiceRowDTO {

	private Long id;

	private String title;

	private BigDecimal price;

	private Date date;

	private Integer vat;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Integer getVat() {
		return vat;
	}

	public void setVat(final Integer vat) {
		this.vat = vat;
	}

}
