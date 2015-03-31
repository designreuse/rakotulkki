package org.rakotulkki.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.rakotulkki.model.InvoiceStatus;
import org.rakotulkki.model.jackson.DateSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author jkuittin
 */
public class InvoiceDTO {

	private Long id;

	private Long invoiceNumber;

	private String customerNumber;

	@JsonSerialize(using = DateSerializer.class)
	private Date invoiceDate;

	@JsonSerialize(using = DateSerializer.class)
	private Date dueDate;

	private Long customerId;

	private String name;

	private String address;

	private String zip;

	private String city;

	private String referenceNumber;

	private String extraText;

	private InvoiceStatus status;

	private BigDecimal sumVatIncluded;

	private BigDecimal sumVatExcluded;

	private BigDecimal sumVat;

	private List<InvoiceRowDTO> rows;

	private CompanyDTO companyDTO;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(final Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(final String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(final Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(final Date dueDate) {
		this.dueDate = dueDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(final String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getExtraText() {
		return extraText;
	}

	public void setExtraText(final String extraText) {
		this.extraText = extraText;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(final InvoiceStatus status) {
		this.status = status;
	}

	public List<InvoiceRowDTO> getRows() {
		return rows;
	}

	public void setRows(final List<InvoiceRowDTO> rows) {
		this.rows = rows;
	}

	public BigDecimal getSumVatIncluded() {
		return sumVatIncluded;
	}

	public void setSumVatIncluded(final BigDecimal sumVatIncluded) {
		this.sumVatIncluded = sumVatIncluded;
	}

	public BigDecimal getSumVatExcluded() {
		return sumVatExcluded;
	}

	public void setSumVatExcluded(final BigDecimal sumVatExcluded) {
		this.sumVatExcluded = sumVatExcluded;
	}

	public BigDecimal getSumVat() {
		return sumVat;
	}

	public void setSumVat(final BigDecimal sumVat) {
		this.sumVat = sumVat;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	public void setCompanyDTO(final CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}

	public String getZipAndCity() {
		return this.zip + " " + this.city;
	}
}
