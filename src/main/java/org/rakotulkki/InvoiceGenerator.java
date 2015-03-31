package org.rakotulkki;

import org.joda.time.LocalDate;
import org.rakotulkki.model.dto.CompanyDTO;
import org.rakotulkki.model.dto.InvoiceDTO;
import org.rakotulkki.model.dto.InvoiceRowDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jkuittin
 */
public class InvoiceGenerator {

	public static List<InvoiceDTO> generate() {
		ArrayList<InvoiceDTO> invoices = new ArrayList<InvoiceDTO>();

		invoices.add(create());
		return invoices;
	}

	private static InvoiceDTO create() {
		InvoiceDTO i = new InvoiceDTO();

		i.setName("Anna Asiakas");
		i.setAddress("Katuosoite");
		i.setCity("Kaupunki");
		i.setZip("000000");
		i.setCustomerNumber("1001");
		i.setInvoiceDate(LocalDate.now().toDate());
		i.setDueDate(LocalDate.now().toDate());
		i.setReferenceNumber("1000100101");
		i.setExtraText("Lisäteksti");
		i.setInvoiceNumber(1001L);
		i.setSumVat(new BigDecimal(0));
		i.setSumVatIncluded(new BigDecimal(100));
		i.setSumVatExcluded(new BigDecimal(100));

		CompanyDTO c = new CompanyDTO();
		c.setBusinessIdentityCode("Y-8273498273");
		c.setCity("Jyväskylä c/o Intensa Oy");
		c.setZip("40100");
		c.setInterestPercent(11);
		c.setName("Psykologi, psykoterapeutti Anna Lepistö");
		c.setTermDays(14);
		c.setStreet("Väinönkatu 36 B 40");
		c.setIban("FI47 5290 8150 0142 16");

		i.setCompanyDTO(c);

		List<InvoiceRowDTO> rows = new ArrayList<InvoiceRowDTO>();
		rows.add(createRow("Tutustumiskäynti", LocalDate.now().minusDays(14), new BigDecimal(20)));
		rows.add(createRow("Tutustumiskäynti", LocalDate.now().minusDays(10), new BigDecimal(20)));
		rows.add(createRow("Terapiakäynti", LocalDate.now().minusDays(7), new BigDecimal(30)));
		rows.add(createRow("Terapiakäynti", LocalDate.now().minusDays(1), new BigDecimal(30)));

		i.setRows(rows);

		return i;
	}

	private static InvoiceRowDTO createRow(String title, LocalDate date, BigDecimal price) {
		InvoiceRowDTO i = new InvoiceRowDTO();
		i.setTitle(title);
		i.setPrice(price);
		i.setDate(date.toDate());
		i.setVat(0);
		return i;
	}

}
