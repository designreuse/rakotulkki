package org.rakotulkki.services;

import org.junit.Test;
import org.rakotulkki.model.kela.KU206;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

/**
 * @author jkuittin
 */
public class KelaReportServiceTest {

	@Test
	public void test206() throws Exception {
		KU206 report = new KU206();

		report.setName("Testraportti");

		KU206.CustomerSummary summary = new KU206.CustomerSummary();
		summary.setNumber("1");
		summary.setCustomerName("Pentti Potilas");
		summary.setCustomerSsn("112356-0192A");
		summary.setSum("100.00");
		summary.setTimePeriod("1.1.2001 - 1.2.2001");

		report.setCustomerSummaries(new ArrayList<KU206.CustomerSummary>());
		report.getCustomerSummaries().add(summary);

		KelaReportService service = new KelaReportService();

		OutputStream os = new ByteArrayOutputStream();
		service.generateReport(report, os);

		assertNotNull(os);
	}

}
